package org.recap.model.schedule;

import com.fasterxml.jackson.databind.deser.Deserializers;
import org.junit.Test;
import org.recap.BaseTestCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by hemalathas on 17/7/17.
 */
public class ScheduleJobRequestUT extends BaseTestCase{

    @Test
    public void testScheduleJobRequest(){
        ScheduleJobRequest scheduleJobRequest = new ScheduleJobRequest();
        scheduleJobRequest.setJobId(1);
        scheduleJobRequest.setJobName("Accession");
        scheduleJobRequest.setCronExpression("0 53 19 1/1 * ? *");
        scheduleJobRequest.setScheduleType("Test");
        assertNotNull(scheduleJobRequest.getCronExpression());
        assertNotNull(scheduleJobRequest.getJobId());
        assertNotNull(scheduleJobRequest.getJobName());
        assertNotNull(scheduleJobRequest.getScheduleType());
    }

    @Test
    public void testScheduleJobResponse(){
        ScheduleJobResponse scheduleJobResponse = new ScheduleJobResponse();
        scheduleJobResponse.setMessage("Success");
        scheduleJobResponse.setNextRunTime(new Date());
        assertNotNull(scheduleJobResponse.getMessage());
        assertNotNull(scheduleJobResponse.getNextRunTime());
    }

}