package org.main.recap.batch.service;

import org.junit.Test;
import org.main.recap.BaseTestCase;
import org.main.recap.RecapConstants;
import org.main.recap.model.batch.SolrIndexRequest;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rajeshbabuk on 19/4/17.
 */
public class MatchingAlgorithmServiceUT extends BaseTestCase {

    @Value("${scsb.solr.client.url}")
    String solrClientUrl;

    @Mock
    RestTemplate restTemplate;

    @Mock
    MatchingAlgorithmService matchingAlgorithmService;

    @Test
    public void testMatchingAlgorithmService() {
        SolrIndexRequest solrIndexRequest = new SolrIndexRequest();
        solrIndexRequest.setProcessType(RecapConstants.ONGOING_MATCHING_ALGORITHM_JOB);
        Date createdDate = new Date();
        solrIndexRequest.setCreatedDate(createdDate);

        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity<SolrIndexRequest> httpEntity = new HttpEntity<>(solrIndexRequest, headers);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(RecapConstants.SUCCESS, HttpStatus.OK);
        Mockito.when(matchingAlgorithmService.getRestTemplate()).thenReturn(restTemplate);
        Mockito.when(matchingAlgorithmService.getSolrIndexRequest(createdDate)).thenReturn(solrIndexRequest);
        Mockito.when(matchingAlgorithmService.getRestTemplate().exchange(solrClientUrl + RecapConstants.MATCHING_ALGORITHM_URL, HttpMethod.POST, httpEntity, String.class)).thenReturn(responseEntity);
        Mockito.when(matchingAlgorithmService.initiateMatchingAlgorithm(solrClientUrl, createdDate)).thenCallRealMethod();
        String status = matchingAlgorithmService.initiateMatchingAlgorithm(solrClientUrl, createdDate);
        assertNotNull(status);
        assertEquals(status, RecapConstants.SUCCESS);
    }
}
