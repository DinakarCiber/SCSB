package org.recap.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.recap.ReCAPConstants;
import org.recap.Service.RestHeaderService;
import org.recap.model.ScheduleJobRequest;
import org.recap.model.ScheduleJobResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;

/**
 * Created by rajeshbabuk on 20/4/17.
 */
public class ScheduleJobsControllerUT extends BaseControllerUT {

    @Value("${scsb.batch.schedule.url}")
    String scsbScheduleUrl;

    @Mock
    private RestTemplate mockRestTemplate;

    @Mock
    private ScheduleJobsController scheduleJobsController;

    @Autowired
    RestHeaderService restHeaderService;

    public String getScsbScheduleUrl() {
        return scsbScheduleUrl;
    }

    public void setScsbScheduleUrl(String scsbScheduleUrl) {
        this.scsbScheduleUrl = scsbScheduleUrl;
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testScheduleJob() throws Exception {
        ScheduleJobRequest scheduleJobRequest = new ScheduleJobRequest();
        scheduleJobRequest.setJobName(ReCAPConstants.PURGE_EXCEPTION_REQUESTS);
        scheduleJobRequest.setScheduleType(ReCAPConstants.SCHEDULE);

        ScheduleJobResponse scheduleJobResponse = new ScheduleJobResponse();
        scheduleJobResponse.setMessage("Scheduled");
        ResponseEntity<ScheduleJobResponse> responseEntity = new ResponseEntity<>(scheduleJobResponse, HttpStatus.OK);
        HttpEntity<ScheduleJobRequest> httpEntity = new HttpEntity<>(scheduleJobRequest, restHeaderService.getHttpHeaders());
        Mockito.when(mockRestTemplate.exchange(getScsbScheduleUrl() + ReCAPConstants.URL_SCHEDULE_JOBS, HttpMethod.POST, httpEntity, ScheduleJobResponse.class)).thenReturn(responseEntity);
        Mockito.when(scheduleJobsController.getRestTemplate()).thenReturn(mockRestTemplate);
        Mockito.when(scheduleJobsController.getScsbScheduleUrl()).thenReturn(scsbScheduleUrl);
        Mockito.when(scheduleJobsController.getRestHeaderService()).thenReturn(restHeaderService);
        Mockito.when(scheduleJobsController.scheduleJob(scheduleJobRequest)).thenCallRealMethod();
        ScheduleJobResponse scheduleJobResponse1 = scheduleJobsController.scheduleJob(scheduleJobRequest);
        assertNotNull(scheduleJobResponse1);
    }

}
