package org.main.recap.jpa;

import org.junit.Test;
import org.main.recap.BaseTestCase;
import org.main.recap.RecapConstants;
import org.main.recap.model.jpa.JobEntity;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rajeshbabuk on 19/4/17.
 */
public class JobDetailsRepositoryUT extends BaseTestCase {

    @Autowired
    JobDetailsRepository jobDetailsRepository;

    @Test
    public void testSaveJob() throws Exception {
        String jobName = "Test_Job_Name";
        JobEntity jobEntity = new JobEntity();
        jobEntity.setJobName(jobName);

        JobEntity savedJobEntity = jobDetailsRepository.save(jobEntity);
        assertNotNull(savedJobEntity);
        assertNotNull(savedJobEntity.getJobName());
        assertEquals(savedJobEntity.getJobName(), jobName);
    }

    @Test
    public void testFindJob() throws Exception {
        JobEntity byJobName = jobDetailsRepository.findByJobName(RecapConstants.PURGE_EXCEPTION_REQUESTS);
        assertNotNull(byJobName);
        assertNotNull(byJobName.getJobName());
        assertEquals(byJobName.getJobName(), RecapConstants.PURGE_EXCEPTION_REQUESTS);
    }
}
