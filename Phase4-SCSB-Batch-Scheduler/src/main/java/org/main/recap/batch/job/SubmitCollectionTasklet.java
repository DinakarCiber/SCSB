package org.main.recap.batch.job;

import org.apache.camel.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.main.recap.RecapConstants;
import org.main.recap.batch.service.UpdateJobDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * Created by harikrishnanv on 19/6/17.
 */
public class SubmitCollectionTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(SubmitCollectionTasklet.class);

    @Value("${scsb.circ.url}")
    String scsbCircUrl;

    @Value("${scsb.solr.client.url}")
    String solrClientUrl;

    @Autowired
    private UpdateJobDetailsService updateJobDetailsService;

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ProducerTemplate producerTemplate;

    /**
     * This method starts the execution of the submit collection job.
     * @param contribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.info("Executing submit collection");
        StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext executionContext = jobExecution.getExecutionContext();
        try {
            long jobInstanceId = jobExecution.getJobInstance().getInstanceId();
            String jobName = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getJobName();
            Date createdDate = chunkContext.getStepContext().getStepExecution().getJobExecution().getCreateTime();
            String jobNameParam = (String) jobExecution.getExecutionContext().get(RecapConstants.JOB_NAME);
            logger.info("Job Parameter in Submit Collection Tasklet : {}", jobNameParam);
            if (!jobName.equalsIgnoreCase(jobNameParam)) {
                updateJobDetailsService.updateJob(solrClientUrl, jobName, createdDate, jobInstanceId);
            }
            updateJobDetailsService.updateJob(solrClientUrl, jobName, createdDate, jobInstanceId);

            producerTemplate.sendBody(RecapConstants.SUBMIT_COLLECTION_JOB_INITIATE_QUEUE, String.valueOf(jobExecution.getId()));
            Endpoint endpoint = camelContext.getEndpoint(RecapConstants.SUBMIT_COLLECTION_JOB_COMPLETION_OUTGOING_QUEUE);
            PollingConsumer consumer = endpoint.createPollingConsumer();
            Exchange exchange = consumer.receive();
            String resultStatus = (String) exchange.getIn().getBody();
            if (StringUtils.isNotBlank(resultStatus)) {
                String[] resultSplitMessage = resultStatus.split("\\|");
                if (!resultSplitMessage[0].equalsIgnoreCase(RecapConstants.JOB_ID + ":" + jobExecution.getId())) {
                    producerTemplate.sendBody(RecapConstants.SUBMIT_COLLECTION_JOB_COMPLETION_OUTGOING_QUEUE, resultStatus);
                    resultStatus = RecapConstants.FAILURE + " - " + RecapConstants.FAILURE_QUEUE_MESSAGE;
                } else {
                    resultStatus = resultSplitMessage[1];
                }
            }
            logger.info("Job Id : {} Submit Collection Job Result Status : {}", jobExecution.getId(), resultStatus);

            if (!StringUtils.containsIgnoreCase(resultStatus, RecapConstants.SUCCESS)) {
                executionContext.put(RecapConstants.JOB_STATUS, RecapConstants.FAILURE);
                executionContext.put(RecapConstants.JOB_STATUS_MESSAGE, RecapConstants.SUBMIT_COLLECTION_STATUS_NAME + " " + resultStatus);
                stepExecution.setExitStatus(new ExitStatus(RecapConstants.FAILURE,  RecapConstants.SUBMIT_COLLECTION_STATUS_NAME + " " + resultStatus));
            } else {
                executionContext.put(RecapConstants.JOB_STATUS, RecapConstants.SUCCESS);
                executionContext.put(RecapConstants.JOB_STATUS_MESSAGE,  RecapConstants.SUBMIT_COLLECTION_STATUS_NAME + " " + resultStatus);
                stepExecution.setExitStatus(new ExitStatus(RecapConstants.SUCCESS,  RecapConstants.SUBMIT_COLLECTION_STATUS_NAME + " " + resultStatus));
            }
        } catch (Exception ex) {
            logger.error(RecapConstants.LOG_ERROR, ExceptionUtils.getMessage(ex));
            executionContext.put(RecapConstants.JOB_STATUS, RecapConstants.FAILURE);
            executionContext.put(RecapConstants.JOB_STATUS_MESSAGE, RecapConstants.SUBMIT_COLLECTION_STATUS_NAME + " " + ExceptionUtils.getMessage(ex));
            stepExecution.setExitStatus(new ExitStatus(RecapConstants.FAILURE, ExceptionUtils.getFullStackTrace(ex)));
        }
        return RepeatStatus.FINISHED;
    }
}
