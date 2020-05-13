package org.main.recap.batch.service;

import org.junit.Test;
import org.main.recap.BaseTestCase;
import org.main.recap.RecapConstants;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

/**
 * Created by hemalathas on 25/7/17.
 */
public class RequestInitialLoadServiceUT extends BaseTestCase{

    @Value("${scsb.circ.url}")
    String solrCircUrl;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RequestInitialLoadService requestInitialLoadService;

    @Test
    public void testRequestInitialLoadService(){
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(RecapConstants.SUCCESS, HttpStatus.OK);
        Mockito.when(requestInitialLoadService.getRestTemplate()).thenReturn(restTemplate);
        Mockito.when(requestInitialLoadService.getRestTemplate().exchange(solrCircUrl +RecapConstants.REQUEST_DATA_LOAD_URL, HttpMethod.POST, httpEntity, String.class)).thenReturn(responseEntity);
        Mockito.when(requestInitialLoadService.requestInitialLoad(solrCircUrl)).thenCallRealMethod();
        String status = requestInitialLoadService.requestInitialLoad(solrCircUrl);
        assertNotNull(status);
        assertEquals(status, RecapConstants.SUCCESS);
    }

}