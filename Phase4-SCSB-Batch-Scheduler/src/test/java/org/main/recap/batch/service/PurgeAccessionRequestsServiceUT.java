package org.main.recap.batch.service;

import org.junit.Test;
import org.main.recap.BaseTestCase;
import org.main.recap.RecapConstants;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rajeshbabuk on 22/5/17.
 */
public class PurgeAccessionRequestsServiceUT extends BaseTestCase {

    @Value("${scsb.circ.url}")
    String scsbCircUrl;

    @Mock
    RestTemplate restTemplate;

    @Mock
    PurgeAccessionRequestsService purgeAccessionRequestsService;

    @Test
    public void testPurgeAccessionRequestsService() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity httpEntity = new HttpEntity<>(headers);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put(RecapConstants.STATUS, RecapConstants.SUCCESS);
        ResponseEntity<Map> responseEntity = new ResponseEntity<>(resultMap, HttpStatus.OK);
        Mockito.when(purgeAccessionRequestsService.getRestTemplate()).thenReturn(restTemplate);
        Mockito.when(purgeAccessionRequestsService.getRestTemplate().exchange(scsbCircUrl + RecapConstants.PURGE_ACCESSION_REQUEST_URL, HttpMethod.GET, httpEntity, Map.class)).thenReturn(responseEntity);
        Mockito.when(purgeAccessionRequestsService.purgeAccessionRequests(scsbCircUrl)).thenCallRealMethod();
        resultMap = purgeAccessionRequestsService.purgeAccessionRequests(scsbCircUrl);
        assertNotNull(resultMap);
        assertEquals(resultMap.get(RecapConstants.STATUS), RecapConstants.SUCCESS);
    }
}
