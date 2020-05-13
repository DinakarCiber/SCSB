package org.main.recap.batch.job;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.main.recap.RecapConstants;
import org.main.recap.batch.service.GenerateReportsService;
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
 * Created by angelind on 2/5/17.
 */
public class AccessionReportsTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(AccessionReportsTasklet.class);

    @Value("${scsb.solr.client.url}")
    private String solrClientUrl;

    @Autowired
    private UpdateJobDetailsService updateJobDetailsService;

    @Autowired
    private GenerateReportsService generateReportsService;

    /**
     * This method starts the execution of the accession reports job.
     * @param contribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.info("Executing AccessionReportsTasklet");
        StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext executionContext = jobExecution.getExecutionContext();
        try {
            String fromDate = jobExecution.getJobParameters().getString(RecapConstants.FROM_DATE);
            Date createdDate;
            if (StringUtils.isNotBlank(fromDate)) {
                SimpleDateFormat dateFormatter = new SimpleDateFormat(RecapConstants.FROM_DATE_FORMAT);
                createdDate = dateFormatter.parse(fromDate);
            } else {
                createdDate = jobExecution.getCreateTime();
            }
            long jobInstanceId = jobExecution.getJobInstance().getInstanceId();
            String jobName = jobExecution.getJobInstance().getJobName();
            String jobNameParam = (String) jobExecution.getExecutionContext().get(RecapConstants.JOB_NAME);
            logger.info("Job Parameter in Accession Reports Tasklet : {}", jobNameParam);
            if (!jobName.equalsIgnoreCase(jobNameParam)) {
                updateJobDetailsService.updateJob(solrClientUrl, jobName, jobExecution.getCreateTime(), jobInstanceId);
            }
            String resultStatus = generateReportsService.generateReport(solrClientUrl, createdDate, RecapConstants.GENERATE_ACCESSION_REPORT_JOB);
            logger.info("Accession Report status : {}", resultStatus);
            if (!StringUtils.containsIgnoreCase(resultStatus, RecapConstants.SUCCESS)) {
                executionContext.put(RecapConstants.JOB_STATUS, RecapConstants.FAILURE);
                executionContext.put(RecapConstants.JOB_STATUS_MESSAGE, RecapConstants.ACCESSION_REPORT_STATUS_NAME + " " + resultStatus);
                stepExecution.setExitStatus(new ExitStatus(RecapConstants.FAILURE, RecapConstants.ACCESSION_REPORT_STATUS_NAME + " " + resultStatus));
            } else {
                executionContext.put(RecapConstants.JOB_STATUS, RecapConstants.SUCCESS);
                executionContext.put(RecapConstants.JOB_STATUS_MESSAGE, resultStatus);
                stepExecution.setExitStatus(new ExitStatus(RecapConstants.SUCCESS, RecapConstants.ACCESSION_REPORT_STATUS_NAME + " " + resultStatus));
            }
        } catch (Exception ex) {
            logger.error(RecapConstants.LOG_ERROR, ExceptionUtils.getMessage(ex));
            executionContext.put(RecapConstants.JOB_STATUS, RecapConstants.FAILURE);
            executionContext.put(RecapConstants.JOB_STATUS_MESSAGE, RecapConstants.ACCESSION_REPORT_STATUS_NAME + " " + ExceptionUtils.getMessage(ex));
            stepExecution.setExitStatus(new ExitStatus(RecapConstants.FAILURE, ExceptionUtils.getFullStackTrace(ex)));
        }
        return RepeatStatus.FINISHED;
    }
}
