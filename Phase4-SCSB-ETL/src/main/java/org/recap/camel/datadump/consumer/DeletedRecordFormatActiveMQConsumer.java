package org.recap.camel.datadump.consumer;

import com.google.common.collect.Lists;
import org.apache.camel.Exchange;
import org.apache.camel.FluentProducerTemplate;
import org.apache.camel.builder.DefaultFluentProducerTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.recap.RecapConstants;
import org.recap.util.datadump.DataExportHeaderUtil;
import org.recap.camel.datadump.callable.DeletedRecordPreparerCallable;
import org.recap.model.export.DeletedRecord;
import org.recap.model.jpa.BibliographicEntity;
import org.recap.service.formatter.datadump.DeletedJsonFormatterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by peris on 11/1/16.
 */
public class DeletedRecordFormatActiveMQConsumer {
    private static final Logger logger = LoggerFactory.getLogger(DeletedRecordFormatActiveMQConsumer.class);

    /**
     * The Deleted json formatter service.
     */
    DeletedJsonFormatterService deletedJsonFormatterService;

    private ExecutorService executorService;
    private DataExportHeaderUtil dataExportHeaderUtil;

    /**
     * Instantiates a new Deleted record format active mq consumer.
     *
     * @param deletedJsonFormatterService the deleted json formatter service
     */
    public DeletedRecordFormatActiveMQConsumer(DeletedJsonFormatterService deletedJsonFormatterService) {
        this.deletedJsonFormatterService = deletedJsonFormatterService;
    }

    /**
     * This method is invoked by the route to prepare deleted record list for data export.
     *
     * @param exchange the exchange
     * @throws Exception the exception
     */
    public void processRecords(Exchange exchange) throws Exception {
        FluentProducerTemplate fluentProducerTemplate = new DefaultFluentProducerTemplate(exchange.getContext());

        List<DeletedRecord> deletedRecordList = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        List<BibliographicEntity> bibliographicEntities = (List<BibliographicEntity>) exchange.getIn().getBody();

        List<Callable<DeletedRecord>> callables = new ArrayList<>();

        List<List<BibliographicEntity>> partitionList = Lists.partition(bibliographicEntities, 1000);

        for (Iterator<List<BibliographicEntity>> iterator = partitionList.iterator(); iterator.hasNext(); ) {
            List<BibliographicEntity> bibliographicEntityList = iterator.next();

            DeletedRecordPreparerCallable scsbRecordPreparerCallable =
                    new DeletedRecordPreparerCallable(bibliographicEntityList, deletedJsonFormatterService);

            callables.add(scsbRecordPreparerCallable);
        }

        List<Future<DeletedRecord>> futureList = getExecutorService().invokeAll(callables);
        futureList.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        logger.error(e.getMessage());
                        throw new RuntimeException(e);
                    }
                });
        List<Integer> itemExportedCountList = new ArrayList<>();
        List failures = new ArrayList();
        for (Future future : futureList) {
            Map<String, Object> results = (Map<String, Object>) future.get();
            Collection<? extends DeletedRecord> successRecords = (Collection<? extends DeletedRecord>) results.get(RecapConstants.SUCCESS);
            if (CollectionUtils.isNotEmpty(successRecords)) {
                deletedRecordList.addAll(successRecords);
            }
            Collection failureRecords = (Collection) results.get(RecapConstants.FAILURE);
            if (CollectionUtils.isNotEmpty(failureRecords)) {
                failures.addAll(failureRecords);
            }
            Integer itemCount = (Integer) results.get(RecapConstants.ITEM_EXPORTED_COUNT);
            if (itemCount !=0 && itemCount != null){
                itemExportedCountList.add(itemCount);
            }
        }
        Integer itemExportedCount = 0;
        for (Integer itemCount : itemExportedCountList) {
            itemExportedCount = itemExportedCount + itemCount;
        }
        String batchHeaders = (String) exchange.getIn().getHeader(RecapConstants.BATCH_HEADERS);
        String requestId = getDataExportHeaderUtil().getValueFor(batchHeaders, "requestId");
        if(CollectionUtils.isNotEmpty(failures)) {
            processFailures(exchange, failures, batchHeaders, requestId);
        }

        long endTime = System.currentTimeMillis();

        logger.info("Time taken to prepare {} deleted records :  {} seconds " ,bibliographicEntities.size() , (endTime - startTime) / 1000 );

        fluentProducerTemplate
                .to(RecapConstants.DELETED_JSON_RECORD_FOR_DATA_EXPORT_Q)
                .withBody(deletedRecordList)
                .withHeader(RecapConstants.BATCH_HEADERS, exchange.getIn().getHeader(RecapConstants.BATCH_HEADERS))
                .withHeader(RecapConstants.EXPORT_FORMAT, exchange.getIn().getHeader(RecapConstants.EXPORT_FORMAT))
                .withHeader(RecapConstants.TRANSMISSION_TYPE, exchange.getIn().getHeader(RecapConstants.TRANSMISSION_TYPE))
                .withHeader(RecapConstants.ITEM_EXPORTED_COUNT,itemExportedCount);
        fluentProducerTemplate.send();
    }

    /**
     * Process the failure records for deleted records export.
     * @param exchange
     * @param failures
     * @param batchHeaders
     * @param requestId
     */
    private void processFailures(Exchange exchange, List failures, String batchHeaders, String requestId) {
        HashMap values = new HashMap();
        values.put(RecapConstants.REQUESTING_INST_CODE, getDataExportHeaderUtil().getValueFor(batchHeaders, RecapConstants.REQUESTING_INST_CODE));
        values.put(RecapConstants.INSTITUTION_CODES, getDataExportHeaderUtil().getValueFor(batchHeaders, RecapConstants.INSTITUTION_CODES));
        values.put(RecapConstants.FETCH_TYPE, getDataExportHeaderUtil().getValueFor(batchHeaders, RecapConstants.FETCH_TYPE));
        values.put(RecapConstants.COLLECTION_GROUP_IDS, getDataExportHeaderUtil().getValueFor(batchHeaders, RecapConstants.COLLECTION_GROUP_IDS));
        values.put(RecapConstants.TRANSMISSION_TYPE, getDataExportHeaderUtil().getValueFor(batchHeaders, RecapConstants.TRANSMISSION_TYPE));
        values.put(RecapConstants.EXPORT_FROM_DATE, getDataExportHeaderUtil().getValueFor(batchHeaders, RecapConstants.EXPORT_FROM_DATE));
        values.put(RecapConstants.EXPORT_FORMAT, getDataExportHeaderUtil().getValueFor(batchHeaders, RecapConstants.EXPORT_FORMAT));
        values.put(RecapConstants.TO_EMAIL_ID, getDataExportHeaderUtil().getValueFor(batchHeaders, RecapConstants.TO_EMAIL_ID));
        values.put(RecapConstants.NUM_RECORDS, String.valueOf(failures.size()));
        values.put(RecapConstants.FAILURE_CAUSE, failures.get(0));
        values.put(RecapConstants.FAILED_BIBS, RecapConstants.FAILED_BIBS);
        values.put(RecapConstants.BATCH_EXPORT, RecapConstants.BATCH_EXPORT_FAILURE);
        values.put(RecapConstants.REQUEST_ID, requestId);

        FluentProducerTemplate fluentProducerTemplate = new DefaultFluentProducerTemplate(exchange.getContext());
        fluentProducerTemplate
                .to(RecapConstants.DATADUMP_FAILURE_REPORT_Q)
                .withBody(values)
                .withHeader(RecapConstants.BATCH_HEADERS, exchange.getIn().getHeader(RecapConstants.BATCH_HEADERS))
                .withHeader(RecapConstants.EXPORT_FORMAT, exchange.getIn().getHeader(RecapConstants.EXPORT_FORMAT))
                .withHeader(RecapConstants.TRANSMISSION_TYPE, exchange.getIn().getHeader(RecapConstants.TRANSMISSION_TYPE));
        fluentProducerTemplate.send();
    }

    /**
     * Gets data export header util.
     *
     * @return the data export header util
     */
    public DataExportHeaderUtil getDataExportHeaderUtil() {
        if (null == dataExportHeaderUtil) {
            dataExportHeaderUtil = new DataExportHeaderUtil();
        }
        return dataExportHeaderUtil;
    }

    /**
     * Sets data export header util.
     *
     * @param dataExportHeaderUtil the data export header util
     */
    public void setDataExportHeaderUtil(DataExportHeaderUtil dataExportHeaderUtil) {
        this.dataExportHeaderUtil = dataExportHeaderUtil;
    }


    /**
     * Gets executor service.
     *
     * @return the executor service
     */
    public ExecutorService getExecutorService() {
        if (null == executorService) {
            executorService = Executors.newFixedThreadPool(500);
        }
        if (executorService.isShutdown()) {
            executorService = Executors.newFixedThreadPool(500);
        }
        return executorService;
    }

}

