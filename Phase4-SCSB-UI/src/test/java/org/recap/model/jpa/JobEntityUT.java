package org.recap.model.jpa;

import org.junit.Test;
import org.recap.BaseTestCase;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by hemalathas on 17/7/17.
 */
public class JobEntityUT extends BaseTestCase{

    @Test
    public void testJobEntity(){
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobId(1);
        jobEntity.setJobName("Test");
        jobEntity.setCronExpression("0 53 19 1/1 * ? *");
        jobEntity.setJobDescription("Test");
        jobEntity.setLastExecutedTime(new Date());
        jobEntity.setStatus("Success");
        jobEntity.setNextRunTime(new Date());
        assertNotNull(jobEntity.getNextRunTime());
        assertNotNull(jobEntity.getCronExpression());
        assertNotNull(jobEntity.getJobDescription());
        assertNotNull(jobEntity.getJobId());
        assertNotNull(jobEntity.getJobName());
        assertNotNull(jobEntity.getLastExecutedTime());
        assertNotNull(jobEntity.getStatus());
    }

}