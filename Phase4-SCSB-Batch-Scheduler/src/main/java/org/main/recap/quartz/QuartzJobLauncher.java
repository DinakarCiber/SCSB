package org.main.recap.quartz;

import org.main.recap.RecapConstants;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by rajeshbabuk on 28/3/17.
 */
public class QuartzJobLauncher extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(QuartzJobLauncher.class);

    private String jobName;
    private JobLauncher jobLauncher;
    private JobLocator jobLocator;

    /**
     * Gets job name.
     *
     * @return the job name
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * Sets job name.
     *
     * @param jobName the job name
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * Gets job launcher.
     *
     * @return the job launcher
     */
    public JobLauncher getJobLauncher() {
        return jobLauncher;
    }

    /**
     * Sets job launcher.
     *
     * @param jobLauncher the job launcher
     */
    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    /**
     * Gets job locator.
     *
     * @return the job locator
     */
    public JobLocator getJobLocator() {
        return jobLocator;
    }

    /**
     * Sets job locator.
     *
     * @param jobLocator the job locator
     */
    public void setJobLocator(JobLocator jobLocator) {
        this.jobLocator = jobLocator;
    }

    /**
     * Quartz scheduler calls this method on each scheduled run of a job.
     * It identifies the spring batch configured job using the job name and holds the job execution details.
     * @param context
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            Job job = jobLocator.getJob(jobName);
            JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
            logger.info("{}_{} was completed successfully. Status : {}", job.getName(), jobExecution.getId(), jobExecution.getStatus());
        } catch (Exception exception) {
            logger.error(RecapConstants.LOG_ERROR, exception);
        }
    }
}
