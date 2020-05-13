package org.recap.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.recap.ReCAPConstants;
import org.recap.Service.RestHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by rajeshbabuk on 3/1/17.
 */
public class UpdateCgdRestControllerUT extends BaseControllerUT {

    @Value("${scsb.solr.client.url}")
    String scsbSolrClient;

    @Mock
    private RestTemplate mockRestTemplate;

    @Mock
    private UpdateCgdRestController updateCgdRestController;

    @Mock
    private UriComponentsBuilder builder;

    @Autowired
    RestHeaderService restHeaderService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    public String getScsbSolrClientUrl() {
        return scsbSolrClient;
    }

    public void setScsbSolrClientUrl(String scsbSolrClientUrl) {
        this.scsbSolrClient = scsbSolrClient;
    }

    @Test
    public void updateCgdForItem() throws Exception {
        String itemBarcode = "3568783121445";
        String owningInstitution = "PUL";
        String oldCollectionGroupDesignation = "Shared";
        String newCollectionGroupDesignation = "Private";
        String cgdChangeNotes = "Notes";
        String username = "guest";
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(ReCAPConstants.SUCCESS,HttpStatus.OK);
        updateCgdRestController.setScsbSolrClientUrl(getScsbSolrClientUrl());
        HttpEntity requestEntity = new HttpEntity<>(restHeaderService.getHttpHeaders());
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(scsbSolrClient + ReCAPConstants.URL_UPDATE_CGD)
                .queryParam(ReCAPConstants.CGD_UPDATE_ITEM_BARCODE, itemBarcode)
                .queryParam(ReCAPConstants.OWNING_INSTITUTION, owningInstitution)
                .queryParam(ReCAPConstants.OLD_CGD, oldCollectionGroupDesignation)
                .queryParam(ReCAPConstants.NEW_CGD, newCollectionGroupDesignation)
                .queryParam(ReCAPConstants.CGD_CHANGE_NOTES, cgdChangeNotes)
                .queryParam(ReCAPConstants.USER_NAME, username);
        Mockito.when(mockRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, requestEntity, String.class)).thenReturn(responseEntity);
        Mockito.when(updateCgdRestController.getRestTemplate()).thenReturn(mockRestTemplate);
        Mockito.when(updateCgdRestController.getScsbSolrClientUrl()).thenReturn(scsbSolrClient);
        Mockito.when(updateCgdRestController.getRestHeaderService()).thenReturn(restHeaderService);
        Mockito.when(updateCgdRestController.updateCgdForItem(itemBarcode,owningInstitution,oldCollectionGroupDesignation,newCollectionGroupDesignation,cgdChangeNotes, username)).thenCallRealMethod();
        String response = updateCgdRestController.updateCgdForItem(itemBarcode,owningInstitution,oldCollectionGroupDesignation,newCollectionGroupDesignation,cgdChangeNotes, username);
        assertNotNull(response);
        assertEquals(response,ReCAPConstants.SUCCESS);
    }

}
