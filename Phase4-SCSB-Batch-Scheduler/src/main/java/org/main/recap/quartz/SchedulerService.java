package org.main.recap.quartz;

import org.main.recap.RecapConstants;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.quartz.CronExpression.isValidExpression;

/**
 * Created by rajeshbabuk on 3/4/17.
 */
@Service
public class SchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobLocator jobLocator;

    /**
     * This method is used to schedule a job with the given job name and add a trigger with the given cron expression.
     *
     * @param jobName        the job name
     * @param cronExpression the cron expression
     * @return the string
     */
    public String scheduleJob(String jobName, String cronExpression) {
        try {
            boolean validCronExpression = isValidExpression(cronExpression);
            if (!validCronExpression) {
                return RecapConstants.ERROR_INVALID_CRON_EXPRESSION;
            } else {
                JobKey jobKey = new JobKey(jobName);
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                JobDetailImpl jobDetailImpl = new JobDetailImpl();
                if (null == jobDetail) {
                    jobDetailImpl.setName(jobName);
                    jobDetailImpl.setJobClass(QuartzJobLauncher.class);
                    JobDataMap jobDataMap = new JobDataMap();
                    jobDataMap.put(RecapConstants.JOB_NAME, jobName);
                    jobDataMap.put(RecapConstants.JOB_LAUNCHER, jobLauncher);
                    jobDataMap.put(RecapConstants.JOB_LOCATOR, jobLocator);
                    jobDetailImpl.setJobDataMap(jobDataMap);
                }
                CronTriggerImpl trigger = new CronTriggerImpl();
                trigger.setName(jobName + RecapConstants.TRIGGER_SUFFIX);
                trigger.setJobKey(jobKey);
                trigger.setCronExpression(cronExpression);
                if (null != jobDetail) {
                    scheduler.scheduleJob(trigger);
                } else {
                    scheduler.scheduleJob(jobDetailImpl, trigger);
                }
            }
        } catch (Exception ex) {
            logger.error(RecapConstants.LOG_ERROR, ex);
            return RecapConstants.ERROR_JOB_FAILED_SCHEDULING;
        }
        return RecapConstants.JOB_SUCCESS_SCHEDULING;
    }

    /**
     * This method is used to reschedule a job with the given job name and modify the trigger with the given cron expression.
     *
     * @param jobName        the job name
     * @param cronExpression the cron expression
     * @return the string
     */
    public String rescheduleJob(String jobName, String cronExpression) {
        try {
            boolean validCronExpression = isValidExpression(cronExpression);
            if (!validCronExpression) {
                return RecapConstants.ERROR_INVALID_CRON_EXPRESSION;
            } else {
                TriggerKey triggerKey = new TriggerKey(jobName + RecapConstants.TRIGGER_SUFFIX);
                CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
                trigger.setCronExpression(cronExpression);
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception ex) {
            logger.error(RecapConstants.LOG_ERROR, ex);
            return RecapConstants.ERROR_JOB_FAILED_RESCHEDULING;
        }
        return RecapConstants.JOB_SUCCESS_RESCHEDULING;
    }

    /**
     * This method is used to unschedule the job with the given job name.
     *
     * @param jobName the job name
     * @return the string
     */
    public String unscheduleJob(String jobName) {
        try {
            TriggerKey triggerKey = new TriggerKey(jobName + RecapConstants.TRIGGER_SUFFIX);
            scheduler.unscheduleJob(triggerKey);
        } catch (Exception ex) {
            logger.error(RecapConstants.LOG_ERROR, ex);
            return RecapConstants.ERROR_JOB_FAILED_UNSCHEDULING;
        }
        return RecapConstants.JOB_SUCCESS_UNSCHEDULING;
    }

}
