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

import static org.junit.Assert.*;

/**
 * Created by angelind on 29/5/17.
 */
public class GenerateReportsServiceUT extends BaseTestCase{

    @Value("${scsb.solr.client.url}")
    String solrClientUrl;

    @Mock
    RestTemplate restTemplate;

    @Mock
    GenerateReportsService generateReportsService;

    @Test
    public void generateReport() throws Exception {
        SolrIndexRequest solrIndexRequest = new SolrIndexRequest();
        solrIndexRequest.setProcessType(RecapConstants.GENERATE_ACCESSION_REPORT);
        Date createdDate = new Date();
        solrIndexRequest.setCreatedDate(createdDate);

        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity<SolrIndexRequest> httpEntity = new HttpEntity<>(solrIndexRequest, headers);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(RecapConstants.SUCCESS, HttpStatus.OK);
        Mockito.when(generateReportsService.getRestTemplate()).thenReturn(restTemplate);
        Mockito.when(generateReportsService.getSolrIndexRequest(createdDate, RecapConstants.GENERATE_ACCESSION_REPORT)).thenReturn(solrIndexRequest);
        Mockito.when(generateReportsService.getRestTemplate().exchange(solrClientUrl + RecapConstants.GENERATE_REPORT_URL, HttpMethod.POST, httpEntity, String.class)).thenReturn(responseEntity);
        Mockito.when(generateReportsService.generateReport(solrClientUrl, createdDate, RecapConstants.GENERATE_ACCESSION_REPORT)).thenCallRealMethod();
        String status = generateReportsService.generateReport(solrClientUrl, createdDate, RecapConstants.GENERATE_ACCESSION_REPORT);
        assertNotNull(status);
        assertEquals(status, RecapConstants.SUCCESS);
    }

}