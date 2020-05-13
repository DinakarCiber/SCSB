package org.main.recap.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.main.recap.RecapConstants;
import org.main.recap.model.ScheduleJobRequest;
import org.main.recap.quartz.SchedulerService;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rajeshbabuk on 19/4/17.
 */
public class ScheduleJobsControllerUT extends BaseControllerUT {

    @Mock
    ScheduleJobsController scheduleJobsController;

    @Mock
    SchedulerService schedulerService;

    @Test
    public void testScheduleJob() throws Exception {
        String jobName = RecapConstants.PURGE_EXCEPTION_REQUESTS;
        String cronExpression = "0/10 * * * * ? *";
        ScheduleJobRequest scheduleJobRequest = new ScheduleJobRequest();
        scheduleJobRequest.setJobName(jobName);
        scheduleJobRequest.setScheduleType(RecapConstants.SCHEDULE);
        scheduleJobRequest.setCronExpression(cronExpression);

        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult mvcResult = this.mockMvc.perform(post("/scheduleService/scheduleJob")
                .headers(getHttpHeaders())
                .contentType(contentType)
                .content(objectMapper.writeValueAsString(scheduleJobRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        assertNotNull(result);
    }
}
