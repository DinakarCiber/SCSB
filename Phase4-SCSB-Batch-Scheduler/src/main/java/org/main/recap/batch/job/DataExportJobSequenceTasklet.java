package org.main.recap.batch.job;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.main.recap.RecapConstants;
import org.main.recap.batch.service.DataExportJobSequenceService;
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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rajeshbabuk on 10/7/17.
 */
public class DataExportJobSequenceTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(DataExportJobSequenceTasklet.class);

    @Value("${scsb.solr.client.url}")
    String solrClientUrl;

    @Value("${scsb.etl.url}")
    String scsbEtlUrl;

    @Autowired
    private DataExportJobSequenceService dataExportJobSequenceService;

    @Autowired
    private UpdateJobDetailsService updateJobDetailsService;

    /**
     * This method starts the execution of incremental and delete data export.
     * @param contribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.info("Executing DataExportJobSequenceTasklet");
        StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext executionContext = jobExecution.getExecutionContext();
        try {
            String exportStringDate = jobExecution.getJobParameters().getString(RecapConstants.FROM_DATE);
            long jobInstanceId = jobExecution.getJobInstance().getInstanceId();
            String jobName = jobExecution.getJobInstance().getJobName();
            Date createdDate = jobExecution.getCreateTime();
            String jobNameParam = (String) jobExecution.getExecutionContext().get(RecapConstants.JOB_NAME);
            logger.info("Job Parameter in Data Export Job Sequence Tasklet : {}", jobNameParam);
            if (!jobName.equalsIgnoreCase(jobNameParam)) {
                updateJobDetailsService.updateJob(solrClientUrl, jobName, createdDate, jobInstanceId);
            }
            String resultStatus = dataExportJobSequenceService.dataExportJobSequence(scsbEtlUrl, createdDate, exportStringDate);
            logger.info("Incremental and delete data export status : {}", resultStatus);
            if (StringUtils.containsIgnoreCase(RecapConstants.FAIL, resultStatus)) {
                executionContext.put(RecapConstants.JOB_STATUS, RecapConstants.FAILURE);
                executionContext.put(RecapConstants.JOB_STATUS_MESSAGE, RecapConstants.DATA_EXPORT_STATUS_NAME + " " + resultStatus);
                stepExecution.setExitStatus(new ExitStatus(RecapConstants.FAILURE, RecapConstants.DATA_EXPORT_STATUS_NAME + " " + resultStatus));
            } else {
                executionContext.put(RecapConstants.JOB_STATUS, RecapConstants.SUCCESS);
                executionContext.put(RecapConstants.JOB_STATUS_MESSAGE, RecapConstants.DATA_EXPORT_STATUS_NAME + " " + resultStatus);
                stepExecution.setExitStatus(new ExitStatus(RecapConstants.SUCCESS, RecapConstants.DATA_EXPORT_STATUS_NAME + " " + resultStatus));
            }
        } catch (Exception ex) {
            logger.error(RecapConstants.LOG_ERROR, ExceptionUtils.getMessage(ex));
            executionContext.put(RecapConstants.JOB_STATUS, RecapConstants.FAILURE);
            executionContext.put(RecapConstants.JOB_STATUS_MESSAGE, RecapConstants.DATA_EXPORT_STATUS_NAME + " " + ExceptionUtils.getMessage(ex));
            stepExecution.setExitStatus(new ExitStatus(RecapConstants.FAILURE, ExceptionUtils.getFullStackTrace(ex)));
        }
        return RepeatStatus.FINISHED;
    }
}
