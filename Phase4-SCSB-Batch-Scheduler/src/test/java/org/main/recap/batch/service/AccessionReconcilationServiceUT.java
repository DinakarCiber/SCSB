package org.main.recap.batch.service;

import org.junit.Test;
import org.main.recap.BaseTestCase;
import org.main.recap.RecapConstants;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by akulak on 25/5/17.
 */
public class AccessionReconcilationServiceUT extends BaseTestCase{

    @Value("${scsb.circ.url}")
    String scsbCircUrl;

    @Mock
    RestTemplate restTemplate;

    @Mock
    AccessionReconcilationService accessionReconcilationService;

    @Test
    public void testAccessionReconcilationService() throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(RecapConstants.SUCCESS, HttpStatus.OK);
        Mockito.when(accessionReconcilationService.getRestTemplate()).thenReturn(restTemplate);
        Mockito.when(accessionReconcilationService.getRestTemplate().exchange(scsbCircUrl + RecapConstants.ACCESSION_RECOCILATION_URL, HttpMethod.POST, httpEntity, String.class)).thenReturn(responseEntity);
        Mockito.when(accessionReconcilationService.accessionReconcilation(scsbCircUrl)).thenCallRealMethod();
        String status = accessionReconcilationService.accessionReconcilation(scsbCircUrl);
        assertNotNull(status);
        assertEquals(status, RecapConstants.SUCCESS);
    }
}
