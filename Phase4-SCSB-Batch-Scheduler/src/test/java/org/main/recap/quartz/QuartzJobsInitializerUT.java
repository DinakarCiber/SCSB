package org.main.recap.quartz;

import org.junit.Test;
import org.main.recap.BaseTestCase;
import org.main.recap.RecapConstants;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

/**
 * Created by rajeshbabuk on 20/4/17.
 */
public class QuartzJobsInitializerUT extends BaseTestCase {

    @Autowired
    QuartzJobsInitializer quartzJobsInitializer;

    @Autowired
    Scheduler scheduler;

    @Test
    public void testInitializeJobs() throws Exception {
        quartzJobsInitializer.initializeJobs();
        JobKey purgeExceptionRequestsJobKey = new JobKey(RecapConstants.PURGE_EXCEPTION_REQUESTS);
        JobKey purgeEmailAddressJobKey = new JobKey(RecapConstants.PURGE_EMAIL_ADDRESS);
        JobKey matchingAlgorithmJobKey = new JobKey(RecapConstants.ONGOING_MATCHING_ALGORITHM);
        JobKey dailyReconciliationJobKey = new JobKey(RecapConstants.DAILY_LAS_TRANSACTION_RECONCILIATION);
        JobKey generateAccessionReportJobKey = new JobKey(RecapConstants.GENERATE_ACCESSION_REPORT);
        JobKey accessionJobKey = new JobKey(RecapConstants.ACCESSION);
        JobKey runJobSequentiallyJobKey = new JobKey(RecapConstants.ACCESSION_MATCHING_JOBS_SEQUENCE);
        JobKey purgeAccessionRequestsJobKey = new JobKey(RecapConstants.PURGE_ACCESSION_REQUESTS);
        boolean isPurgeExceptionRequestsJobKeyExists = scheduler.checkExists(purgeExceptionRequestsJobKey);
        boolean isPurgeEmailAddressJobKeyExists = scheduler.checkExists(purgeEmailAddressJobKey);
        boolean isMatchingAlgorithmJobKeyExists = scheduler.checkExists(matchingAlgorithmJobKey);
        boolean isDailyReconciliationJobKeyExists = scheduler.checkExists(dailyReconciliationJobKey);
        boolean isGenerateAccessionReportJobKeyExists = scheduler.checkExists(generateAccessionReportJobKey);
        boolean isAccessionJobKeyExists = scheduler.checkExists(accessionJobKey);
        boolean isRunJobSequentiallyJobKeyExists = scheduler.checkExists(runJobSequentiallyJobKey);
        boolean isPurgeAccessionRequestsJobKeyExists = scheduler.checkExists(purgeAccessionRequestsJobKey);
        assertTrue(isPurgeExceptionRequestsJobKeyExists);
        assertTrue(isPurgeEmailAddressJobKeyExists);
        assertTrue(isMatchingAlgorithmJobKeyExists);
        assertTrue(isDailyReconciliationJobKeyExists);
        assertTrue(isGenerateAccessionReportJobKeyExists);
        assertTrue(isAccessionJobKeyExists);
        assertTrue(isRunJobSequentiallyJobKeyExists);
        assertTrue(isPurgeAccessionRequestsJobKeyExists);
    }
}
