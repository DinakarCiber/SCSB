package org.main.recap.controller;

import org.apache.commons.lang.StringUtils;
import org.main.recap.RecapConstants;
import org.main.recap.model.ScheduleJobRequest;
import org.main.recap.model.ScheduleJobResponse;
import org.main.recap.quartz.SchedulerService;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by rajeshbabuk on 5/4/17.
 */
@RestController
@RequestMapping("/scheduleService")
public class ScheduleJobsController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleJobsController.class);

    @Autowired
    private SchedulerService schedulerService;

    /**
     * This method is exposed as scheduler service for other microservices to schedule or reschedule or unschedule a job,
     *
     * @param scheduleJobRequest the schedule job request
     * @return the schedule job response with the status message and next execution time.
     */
    @RequestMapping(value = "/scheduleJob", method = RequestMethod.POST)
    public ScheduleJobResponse scheduleJob(@RequestBody ScheduleJobRequest scheduleJobRequest) {
        ScheduleJobResponse scheduleJobResponse = new ScheduleJobResponse();
        String message = null;
        try {
            if (RecapConstants.SCHEDULE.equals(scheduleJobRequest.getScheduleType())) {
                message = schedulerService.scheduleJob(scheduleJobRequest.getJobName(), scheduleJobRequest.getCronExpression());
            } else if (RecapConstants.RESCHEDULE.equals(scheduleJobRequest.getScheduleType())) {
                message = schedulerService.rescheduleJob(scheduleJobRequest.getJobName(), scheduleJobRequest.getCronExpression());
            } else if (RecapConstants.UNSCHEDULE.equals(scheduleJobRequest.getScheduleType())) {
                message = schedulerService.unscheduleJob(scheduleJobRequest.getJobName());
            }
            if (StringUtils.containsIgnoreCase(message, RecapConstants.SUCCESS) && !RecapConstants.UNSCHEDULE.equals(scheduleJobRequest.getScheduleType())) {
                CronExpression cronExpression = new CronExpression(scheduleJobRequest.getCronExpression());
                scheduleJobResponse.setNextRunTime(cronExpression.getNextValidTimeAfter(new Date()));
            }
        } catch (Exception exception) {
            logger.error(RecapConstants.LOG_ERROR, exception);
            message = exception.getMessage();
        }
        scheduleJobResponse.setMessage(message);
        return scheduleJobResponse;
    }
}
