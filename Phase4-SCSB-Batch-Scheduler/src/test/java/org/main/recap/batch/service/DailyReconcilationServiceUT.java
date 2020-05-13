package org.main.recap.batch.service;

import org.junit.Test;
import org.main.recap.BaseTestCase;
import org.main.recap.RecapConstants;
import org.main.recap.model.batch.SolrIndexRequest;
import org.main.recap.model.jpa.JobEntity;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by akulak on 10/5/17.
 */
public class DailyReconcilationServiceUT extends BaseTestCase {

    @Value("${scsb.circ.url}")
    String solrCircUrl;

    @Mock
    RestTemplate restTemplate;

    @Mock
    private DailyReconcilationService dailyReconcilationService;

    @Test
    public void testDailyReconcilationService() throws Exception{
        String jobName  = "DailyReconcilation";
        Date lastExecutedTime = new Date();
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity<SolrIndexRequest> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(RecapConstants.SUCCESS, HttpStatus.OK);
        Mockito.when(dailyReconcilationService.getRestTemplate()).thenReturn(restTemplate);
        Mockito.when(dailyReconcilationService.getRestTemplate().exchange(solrCircUrl +RecapConstants.DAILY_RECONCILATION_URL, HttpMethod.POST, httpEntity, String.class)).thenReturn(responseEntity);
        Mockito.when(dailyReconcilationService.dailyReconcilation(solrCircUrl)).thenCallRealMethod();
        String status = dailyReconcilationService.dailyReconcilation(solrCircUrl);
        assertNotNull(status);
        assertEquals(status, RecapConstants.SUCCESS);
    }
}
