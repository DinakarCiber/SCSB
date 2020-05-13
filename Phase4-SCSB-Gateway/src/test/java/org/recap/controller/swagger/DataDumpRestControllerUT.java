package org.recap.controller.swagger;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.recap.BaseTestCase;
import org.recap.ReCAPConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by hemalathas on 17/7/17.
 */
public class DataDumpRestControllerUT extends BaseTestCase{

    @Mock
    DataDumpRestController dataDumpRestController;

    @Mock
    RestTemplate restTemplate;

    @Value("${scsb.etl.url}")
    private String scsbEtlUrl;

    @Test
    public void testDataDumpRestController(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("api_key","recap");
        HttpEntity requestEntity = new HttpEntity(headers);
        String institutionCodes = "PUL";
        String requestingInstitutionCode = "CUL";
        String fetchType = "1";
        String outputFormat = "1";
        String date = new Date().toString();
        String collectionGroupIds = "1";
        String transmissionType = "1";
        String emailToAddress = "hemalatha.s@htcindia.com";

        Map<String,String> inputMap = new HashMap<>();
        inputMap.put("institutionCodes",institutionCodes);
        inputMap.put("requestingInstitutionCode",requestingInstitutionCode);
        inputMap.put("fetchType",fetchType);
        inputMap.put("outputFormat",outputFormat);
        inputMap.put("date",date);
        inputMap.put("collectionGroupIds",collectionGroupIds);
        inputMap.put("transmissionType",transmissionType);
        inputMap.put("emailToAddress",emailToAddress);

        ResponseEntity responseEntity = new ResponseEntity(ReCAPConstants.DATADUMP_PROCESS_STARTED, HttpStatus.OK);
        Mockito.when(dataDumpRestController.getRestTemplate()).thenReturn(restTemplate);
        Mockito.when(dataDumpRestController.getScsbEtlUrl()).thenReturn(scsbEtlUrl);
        Mockito.when(restTemplate.exchange(scsbEtlUrl + "dataDump/exportDataDump/?institutionCodes={institutionCodes}&requestingInstitutionCode={requestingInstitutionCode}&fetchType={fetchType}&outputFormat={outputFormat}&date={date}&collectionGroupIds={collectionGroupIds}&transmissionType={transmissionType}&emailToAddress={emailToAddress}", HttpMethod.GET, requestEntity, String.class, inputMap)).thenReturn(responseEntity);
        Mockito.when(dataDumpRestController.exportDataDump(institutionCodes,requestingInstitutionCode,fetchType,outputFormat,date,collectionGroupIds,transmissionType,emailToAddress)).thenCallRealMethod();
        ResponseEntity responseEntity1 = dataDumpRestController.exportDataDump(institutionCodes,requestingInstitutionCode,fetchType,outputFormat,date,collectionGroupIds,transmissionType,emailToAddress);
        assertNotNull(responseEntity1);
        assertEquals(responseEntity1.getBody(),"Export process has started and we will send an email notification upon completion");
    }

    @Test
    public void checkGetterServices(){
        Mockito.when(dataDumpRestController.getRestTemplate()).thenCallRealMethod();
        Mockito.when(dataDumpRestController.getScsbEtlUrl()).thenCallRealMethod();
        assertNotEquals(dataDumpRestController.getRestTemplate(),restTemplate);
        assertNotEquals(dataDumpRestController.getScsbEtlUrl(),scsbEtlUrl);
    }

}