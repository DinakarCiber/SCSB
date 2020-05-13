package org.recap.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.recap.ReCAPConstants;
import org.recap.Service.RestHeaderService;
import org.recap.model.DeaccessionItemResultsRow;
import org.recap.model.ReportsRequest;
import org.recap.model.ReportsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rajeshbabuk on 13/1/17.
 */
public class ReportsRestControllerUT extends BaseControllerUT {

    @Value("${scsb.solr.client.url}")
    String scsbSolrClientUrl;

    @Mock
    private RestTemplate mockRestTemplate;

    @Mock
    private ReportsRestController reportsRestController;

    @Autowired
    private RestHeaderService restHeaderService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    public String getScsbSolrClientUrl() {
        return scsbSolrClientUrl;
    }

    public void setScsbSolrClientUrl(String scsbSolrClientUrl) {
        this.scsbSolrClientUrl = scsbSolrClientUrl;
    }

    @Test
    public void accessionDeaccessionCounts() throws Exception {
        ReportsRequest reportsRequest = new ReportsRequest();
        reportsRequest.setAccessionDeaccessionFromDate("09/27/2016");
        reportsRequest.setAccessionDeaccessionToDate("01/27/2017");
        reportsRequest.setOwningInstitutions(Arrays.asList("CUL", "PUL", "NYPL"));
        reportsRequest.setCollectionGroupDesignations(Arrays.asList("Private", "Open", "Shared"));
        reportsRequest.setPageNumber(1);
        reportsRequest.setPageSize(10);
        reportsRequest.setDeaccessionOwningInstitution("CUL");
        reportsRequest.setIncompleteRequestingInstitution("PUL");
        reportsRequest.setIncompletePageNumber(10);
        reportsRequest.setIncompletePageSize(10);
        reportsRequest.setExport(true);
        DeaccessionItemResultsRow deaccessionItemResultsRow = getDeaccessionItemResultsRow();
        ReportsResponse reportsResponse = new ReportsResponse();
        reportsResponse.setMessage(ReCAPConstants.SUCCESS);
        reportsResponse.setDeaccessionItemResultsRows(Arrays.asList(deaccessionItemResultsRow));
        ResponseEntity<ReportsResponse> responseEntity = new ResponseEntity<ReportsResponse>(reportsResponse,HttpStatus.OK);

        HttpEntity<ReportsRequest> httpEntity = new HttpEntity<>(reportsRequest, restHeaderService.getHttpHeaders());
        Mockito.when(mockRestTemplate.exchange(getScsbSolrClientUrl() + ReCAPConstants.URL_REPORTS_ACCESSION_DEACCESSION_COUNTS, HttpMethod.POST,httpEntity, ReportsResponse.class)).thenReturn(responseEntity);
        Mockito.when(reportsRestController.getRestTemplate()).thenReturn(mockRestTemplate);
        Mockito.when(reportsRestController.getRestHeaderService()).thenReturn(restHeaderService);
        Mockito.when(reportsRestController.getScsbSolrClientUrl()).thenReturn(scsbSolrClientUrl);
        Mockito.when(reportsRestController.accessionDeaccessionCounts(reportsRequest)).thenCallRealMethod();
        ReportsResponse reportsResponse1 = reportsRestController.accessionDeaccessionCounts(reportsRequest);
        assertNotNull(reportsResponse1);
        assertNotNull(deaccessionItemResultsRow.getCgd());
        assertNotNull(deaccessionItemResultsRow.getDeaccessionDate());
        assertNotNull(deaccessionItemResultsRow.getDeaccessionNotes());
        assertEquals(deaccessionItemResultsRow.getDeaccessionOwnInst(),"PUL");
        assertNotNull(deaccessionItemResultsRow.getItemBarcode());
        assertNotNull(deaccessionItemResultsRow.getItemId());
        assertNotNull(deaccessionItemResultsRow.getTitle());
        assertNotNull(reportsRequest.getAccessionDeaccessionFromDate());
        assertNotNull(reportsRequest.getAccessionDeaccessionToDate());
        assertNotNull(reportsRequest.getOwningInstitutions());
        assertNotNull(reportsRequest.getCollectionGroupDesignations());
        assertNotNull(reportsRequest.getPageNumber());
        assertNotNull(reportsRequest.getPageSize());
        assertNotNull(reportsRequest.getDeaccessionOwningInstitution());
        assertNotNull(reportsRequest.getIncompleteRequestingInstitution());
        assertNotNull(reportsRequest.getIncompletePageNumber());
        assertNotNull(reportsRequest.getIncompletePageSize());
        assertNotNull(reportsRequest.isExport());
    }

    public DeaccessionItemResultsRow getDeaccessionItemResultsRow(){
        DeaccessionItemResultsRow deaccessionItemResultsRow = new DeaccessionItemResultsRow();
        deaccessionItemResultsRow.setItemId(123);
        deaccessionItemResultsRow.setCgd("Open");
        deaccessionItemResultsRow.setDeaccessionDate(new Date().toString());
        deaccessionItemResultsRow.setDeaccessionNotes("test");
        deaccessionItemResultsRow.setTitle("test");
        deaccessionItemResultsRow.setDeaccessionOwnInst("PUL");
        deaccessionItemResultsRow.setItemBarcode("326598741256985");
        return deaccessionItemResultsRow;
    }

    @Test
    public void cgdItemCounts() throws Exception {
        ReportsRequest reportsRequest = new ReportsRequest();
        reportsRequest.setOwningInstitutions(Arrays.asList("CUL", "PUL", "NYPL"));
        reportsRequest.setCollectionGroupDesignations(Arrays.asList("Private", "Open", "Shared"));
        ReportsResponse reportsResponse = new ReportsResponse();
        reportsResponse.setMessage(ReCAPConstants.SUCCESS);
        ResponseEntity<ReportsResponse> responseEntity = new ResponseEntity<ReportsResponse>(reportsResponse,HttpStatus.OK);
        HttpEntity<ReportsRequest> httpEntity = new HttpEntity<>(reportsRequest, restHeaderService.getHttpHeaders());
        Mockito.when(mockRestTemplate.exchange(getScsbSolrClientUrl() + ReCAPConstants.URL_REPORTS_CGD_ITEM_COUNTS, HttpMethod.POST,httpEntity, ReportsResponse.class)).thenReturn(responseEntity);
        Mockito.when(reportsRestController.getRestTemplate()).thenReturn(mockRestTemplate);
        Mockito.when(reportsRestController.getRestHeaderService()).thenReturn(restHeaderService);
        Mockito.when(reportsRestController.getScsbSolrClientUrl()).thenReturn(scsbSolrClientUrl);
        Mockito.when(reportsRestController.cgdItemCounts(reportsRequest)).thenCallRealMethod();
        ReportsResponse reportsResponse1 = reportsRestController.cgdItemCounts(reportsRequest);
        assertNotNull(reportsResponse1);
    }

    @Test
    public void deaccessionResults() throws Exception {
        ReportsRequest reportsRequest = new ReportsRequest();
        reportsRequest.setAccessionDeaccessionFromDate("09/27/2016");
        reportsRequest.setAccessionDeaccessionToDate("01/27/2017");
        reportsRequest.setDeaccessionOwningInstitution("PUL");
        ReportsResponse reportsResponse = new ReportsResponse();
        reportsResponse.setMessage(ReCAPConstants.SUCCESS);
        ResponseEntity<ReportsResponse> responseEntity = new ResponseEntity<ReportsResponse>(reportsResponse,HttpStatus.OK);
        HttpEntity<ReportsRequest> httpEntity = new HttpEntity<>(reportsRequest, restHeaderService.getHttpHeaders());
        Mockito.when(mockRestTemplate.exchange(getScsbSolrClientUrl() + ReCAPConstants.URL_REPORTS_DEACCESSION_RESULTS, HttpMethod.POST,httpEntity, ReportsResponse.class)).thenReturn(responseEntity);
        Mockito.when(reportsRestController.getRestTemplate()).thenReturn(mockRestTemplate);
        Mockito.when(reportsRestController.getScsbSolrClientUrl()).thenReturn(scsbSolrClientUrl);
        Mockito.when(reportsRestController.getRestHeaderService()).thenReturn(restHeaderService);
        Mockito.when(reportsRestController.deaccessionResults(reportsRequest)).thenCallRealMethod();
        ReportsResponse reportsResponse1 = reportsRestController.deaccessionResults(reportsRequest);
        assertNotNull(reportsResponse1);
    }

}
