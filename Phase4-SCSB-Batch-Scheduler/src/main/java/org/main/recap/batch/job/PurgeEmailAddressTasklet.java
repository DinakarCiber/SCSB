package org.main.recap.batch.job;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.main.recap.RecapConstants;
import org.main.recap.batch.service.PurgeEmailAddressService;
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
import java.util.Map;

/**
 * Created by rajeshbabuk on 28/3/17.
 */
public class PurgeEmailAddressTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(PurgeEmailAddressTasklet.class);

    @Value("${scsb.solr.client.url}")
    String solrClientUrl;

    @Value("${scsb.circ.url}")
    String scsbCircUrl;

    @Autowired
    private PurgeEmailAddressService purgeEmailAddressService;

    @Autowired
    private UpdateJobDetailsService updateJobDetailsService;

    /**
     * This method starts the execution of purging email addresses job.
     *
     * @param contribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.info("Executing PurgeEmailAddressTasklet");
        StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext executionContext = jobExecution.getExecutionContext();
        try {
            long jobInstanceId = jobExecution.getJobInstance().getInstanceId();
            String jobName = jobExecution.getJobInstance().getJobName();
            Date createdDate = jobExecution.getCreateTime();
            updateJobDetailsService.updateJob(solrClientUrl, jobName, createdDate, jobInstanceId);
            Map<String, String> resultMap = purgeEmailAddressService.purgeEmailAddress(scsbCircUrl);
            String status = resultMap.get(RecapConstants.STATUS);
            String message = RecapConstants.PURGE_EDD_REQUEST + ":" + resultMap.get(RecapConstants.PURGE_EDD_REQUEST)
                    + ", " + RecapConstants.PURGE_PHYSICAL_REQUEST + ":" + resultMap.get(RecapConstants.PURGE_PHYSICAL_REQUEST);
            logger.info("Purge Email Addresses status : {}", status);
            logger.info("Purge Email Addresses status message : {}", message);
            executionContext.put(RecapConstants.JOB_STATUS, status);
            executionContext.put(RecapConstants.JOB_STATUS_MESSAGE, message);
            stepExecution.setExitStatus(new ExitStatus(status, message));
        } catch (Exception ex) {
            logger.error(RecapConstants.LOG_ERROR, ExceptionUtils.getMessage(ex));
            executionContext.put(RecapConstants.JOB_STATUS, RecapConstants.FAILURE);
            executionContext.put(RecapConstants.JOB_STATUS_MESSAGE, ExceptionUtils.getMessage(ex));
            stepExecution.setExitStatus(new ExitStatus(RecapConstants.FAILURE, ExceptionUtils.getFullStackTrace(ex)));
        }
        return RepeatStatus.FINISHED;
    }
}
