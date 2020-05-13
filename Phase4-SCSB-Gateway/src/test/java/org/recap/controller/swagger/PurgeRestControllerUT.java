package org.recap.controller.swagger;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.recap.BaseTestCase;
import org.recap.ReCAPConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by hemalathas on 10/4/17.
 */
public class PurgeRestControllerUT extends BaseTestCase{

    private static final Logger logger = LoggerFactory.getLogger(PurgeRestController.class);

    @Mock
    PurgeRestController purgeRestController;

    @Value("${scsb.circ.url}")
    String scsbCircUrl;

    public String getScsbCircUrl() {
        return scsbCircUrl;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    @Mock
    HttpEntity mockedHttpEntity;

    @Mock
    RestTemplate restTemplate;

    @Test
    public void testPurgeEmailAddress(){
        Mockito.when(purgeRestController.getRestTemplate()).thenReturn(restTemplate);
        Mockito.when(purgeRestController.getScsbCircUrl()).thenReturn(scsbCircUrl);
        Mockito.when(purgeRestController.getLogger()).thenReturn(logger);
        HttpEntity requestEntity = new HttpEntity<>(getHttpHeaders());
        Mockito.when(purgeRestController.getHttpEntity()).thenReturn(requestEntity);
        Map<String,Integer> map = new HashMap<>();
        map.put("physicalRequest",1);
        map.put("eddRequest",1);
        ResponseEntity responseEntity1 = new ResponseEntity(map, HttpStatus.OK);
        Mockito.when(purgeRestController.getRestTemplate().exchange(getScsbCircUrl()+ ReCAPConstants.REST_URL_PURGE_EMAIL_ADDRESS, HttpMethod.GET,requestEntity,Map.class)).thenReturn(responseEntity1);
        Mockito.when(purgeRestController.purgeEmailAddress()).thenCallRealMethod();
        ResponseEntity responseEntity = purgeRestController.purgeEmailAddress();
        assertNotNull(responseEntity);
    }

    @Test
    public void testPurgeExceptionRequests() {
        Mockito.when(purgeRestController.getRestTemplate()).thenReturn(restTemplate);
        Mockito.when(purgeRestController.getScsbCircUrl()).thenReturn(scsbCircUrl);
        Mockito.when(purgeRestController.getLogger()).thenReturn(logger);
        HttpEntity requestEntity = new HttpEntity<>(getHttpHeaders());
        Mockito.when(purgeRestController.getHttpEntity()).thenReturn(requestEntity);
        Map responseMap = new HashMap();
        responseMap.put(ReCAPConstants.STATUS, ReCAPConstants.SUCCESS);
        ResponseEntity<Map> responseEntity1 = new ResponseEntity(responseMap, HttpStatus.OK);
        Mockito.when(purgeRestController.getRestTemplate().exchange(getScsbCircUrl() + ReCAPConstants.REST_URL_PURGE_EMAIL_ADDRESS, HttpMethod.GET, requestEntity, Map.class)).thenReturn(responseEntity1);
        Mockito.when(purgeRestController.purgeExceptionRequests()).thenCallRealMethod();
        ResponseEntity responseEntity = purgeRestController.purgeExceptionRequests();
        assertNotNull(responseEntity);
    }

    @Test
    public void testGetterServices(){
        Mockito.when(purgeRestController.getRestTemplate()).thenCallRealMethod();
        Mockito.when(purgeRestController.getScsbCircUrl()).thenCallRealMethod();
        Mockito.when(purgeRestController.getLogger()).thenCallRealMethod();
        Mockito.when(purgeRestController.getHttpEntity()).thenCallRealMethod();
        assertNotEquals(purgeRestController.getRestTemplate(),restTemplate);
        assertNotEquals(purgeRestController.getScsbCircUrl(),scsbCircUrl);
        assertNotEquals(purgeRestController.getHttpEntity(),mockedHttpEntity);
    }


    private HttpHeaders getHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(ReCAPConstants.RESPONSE_DATE, new Date().toString());
        return responseHeaders;
    }



}