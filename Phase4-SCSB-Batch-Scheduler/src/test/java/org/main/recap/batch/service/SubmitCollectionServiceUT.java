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
 * Created by harikrishnanv on 22/6/17.
 */
public class SubmitCollectionServiceUT extends BaseTestCase {

    @Value("${scsb.circ.url}")
    String scsbCircUrl;

    @Mock
    RestTemplate restTemplate;

    @Mock
    SubmitCollectionService submitCollectionService;

    @Test
    public void submitCollectionTest() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(RecapConstants.SUCCESS, HttpStatus.OK);
        Mockito.when(submitCollectionService.getRestTemplate()).thenReturn(restTemplate);
        Mockito.when(submitCollectionService.getRestTemplate().exchange(scsbCircUrl + RecapConstants.SUBMIT_COLLECTION_URL, HttpMethod.POST, httpEntity, String.class)).thenReturn(responseEntity);
        Mockito.when(submitCollectionService.submitCollection(scsbCircUrl)).thenCallRealMethod();
        String status = submitCollectionService.submitCollection(scsbCircUrl);
        assertNotNull(status);
        assertEquals(status, RecapConstants.SUCCESS);
    }

}