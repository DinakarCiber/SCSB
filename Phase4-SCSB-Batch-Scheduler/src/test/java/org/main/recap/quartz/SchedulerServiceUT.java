package org.main.recap.quartz;

import org.junit.Test;
import org.main.recap.BaseTestCase;
import org.main.recap.RecapConstants;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by rajeshbabuk on 19/4/17.
 */
public class SchedulerServiceUT extends BaseTestCase {

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    Scheduler scheduler;

    @Test
    public void testScheduleJobInvalidCron() throws Exception {
        String jobName = RecapConstants.PURGE_EXCEPTION_REQUESTS;
        String cronExpression = "1 2 3 4 5";
        String message = schedulerService.scheduleJob(jobName, cronExpression);
        assertNotNull(message);
        assertEquals(message, RecapConstants.ERROR_INVALID_CRON_EXPRESSION);
    }

    @Test
    public void testScheduleJobRunEveryFiveSeconds() throws Exception {
        String jobName = RecapConstants.PURGE_EXCEPTION_REQUESTS;
        TriggerKey triggerKey = new TriggerKey(jobName + RecapConstants.TRIGGER_SUFFIX);
        String message = schedulerService.scheduleJob(jobName, "0/5 * * * * ? *");
        assertNotNull(message);
        boolean isTriggerExistsAfterSchedule = scheduler.checkExists(triggerKey);
        assertTrue(isTriggerExistsAfterSchedule);
        Thread.sleep(10000);
        Trigger trigger = scheduler.getTrigger(triggerKey);
        boolean mayFireAgain = trigger.mayFireAgain();
        Date previousFireTime = trigger.getPreviousFireTime();
        Date nextFireTime = trigger.getNextFireTime();
        assertEquals(trigger.getJobKey().getName(), jobName);
        assertTrue(mayFireAgain);
        assertNotNull(previousFireTime);
        assertNotNull(nextFireTime);
    }

    @Test
    public void testRescheduleJobInvalidCron() throws Exception {
        String jobName = RecapConstants.PURGE_EXCEPTION_REQUESTS;
        String cronExpression = "1 2 3 4 5";
        String message = schedulerService.rescheduleJob(jobName, cronExpression);
        assertNotNull(message);
        assertEquals(message, RecapConstants.ERROR_INVALID_CRON_EXPRESSION);
    }

    @Test
    public void testRescheduleJobForUnscheduledJob() throws Exception {
        String jobName = RecapConstants.PURGE_EXCEPTION_REQUESTS;
        String cronExpression = "0/10 * * * * ? *";
        String message = schedulerService.rescheduleJob(jobName, cronExpression);
        assertNotNull(message);
        assertEquals(message, RecapConstants.ERROR_JOB_FAILED_RESCHEDULING);
    }

    @Test
    public void testRescheduleJobRunEveryFiveSecondsForScheduledJob() throws Exception {
        String jobName = RecapConstants.PURGE_EXCEPTION_REQUESTS;
        TriggerKey triggerKey = new TriggerKey(jobName + RecapConstants.TRIGGER_SUFFIX);
        String scheduleMessage = schedulerService.scheduleJob(jobName, "0/5 * * * * ? *");
        assertNotNull(scheduleMessage);
        boolean isTriggerExistsAfterSchedule = scheduler.checkExists(triggerKey);
        assertTrue(isTriggerExistsAfterSchedule);
        String cronExpression = "0/10 * * * * ? *";
        String rescheduleMessage = schedulerService.rescheduleJob(jobName, cronExpression);
        assertNotNull(rescheduleMessage);
        assertEquals(rescheduleMessage, RecapConstants.JOB_SUCCESS_RESCHEDULING);
        boolean isTriggerExistsAfterReschedule = scheduler.checkExists(triggerKey);
        assertTrue(isTriggerExistsAfterReschedule);
        Thread.sleep(10000);
        Trigger trigger = scheduler.getTrigger(triggerKey);
        boolean mayFireAgain = trigger.mayFireAgain();
        Date previousFireTime = trigger.getPreviousFireTime();
        Date nextFireTime = trigger.getNextFireTime();
        assertEquals(trigger.getJobKey().getName(), jobName);
        assertTrue(mayFireAgain);
        assertNotNull(previousFireTime);
        assertNotNull(nextFireTime);
    }

    @Test
    public void testUnscheduleJob() throws Exception {
        String jobName = RecapConstants.PURGE_EXCEPTION_REQUESTS;
        TriggerKey triggerKey = new TriggerKey(jobName + RecapConstants.TRIGGER_SUFFIX);
        String scheduleMessage = schedulerService.scheduleJob(jobName, "0/5 * * * * ? *");
        assertNotNull(scheduleMessage);
        boolean isTriggerExistsAfterSchedule = scheduler.checkExists(triggerKey);
        assertTrue(isTriggerExistsAfterSchedule);
        String message = schedulerService.unscheduleJob(jobName);
        assertNotNull(message);
        assertEquals(message, RecapConstants.JOB_SUCCESS_UNSCHEDULING);
        boolean isTriggerExistsAfterUnschedule = scheduler.checkExists(triggerKey);
        assertFalse(isTriggerExistsAfterUnschedule);
    }
}
