package org.recap.controller.swagger;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.recap.ReCAPConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;

/**
 * Created by hemalathas on 10/4/17.
 */
@RestController
@RequestMapping("/purgeRestController")
public class PurgeRestController {

    private static final Logger logger = LoggerFactory.getLogger(PurgeRestController.class);

    @Value("${scsb.circ.url}")
    private String scsbCircUrl;

    /**
     * Gets scsb circ url.
     *
     * @return the scsb circ url
     */
    public String getScsbCircUrl() {
        return scsbCircUrl;
    }

    /**
     * Get rest template rest template.
     *
     * @return the rest template
     */
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * Get http entity http entity.
     *
     * @return the http entity
     */
    public HttpEntity getHttpEntity(){
        return new HttpEntity<>(getHttpHeaders());
    }

    /**
     * Gets logger.
     *
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     *This method will call scsb-circ microservice to remove patron's email address from scsb database after
     *configured day in the external properties
     * @return the response entity
     */
    @RequestMapping(value = "/purgeEmailAddress", method = RequestMethod.GET)
    @ApiOperation(value = "purgeEmailAddress",notes = "The Purge email address API as the name suggests purges all email addresses of patrons stored in SCSB database as part of request information. This API is internally used to purge emails through a scheduled job at regular intervals to remove Patron identifying information from SCSB.", nickname = "purgeEmailAddress", position = 0)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    public ResponseEntity purgeEmailAddress(){
        ResponseEntity<Map> responseEntity = null;
        Map response = null;
        try{
            HttpEntity requestEntity = getHttpEntity();
            responseEntity = getRestTemplate().exchange(getScsbCircUrl()+ ReCAPConstants.REST_URL_PURGE_EMAIL_ADDRESS, HttpMethod.GET,requestEntity,Map.class);
            response = responseEntity.getBody();
        }catch(Exception e){
            getLogger().error("Exception",e);
        }

        return new ResponseEntity(response,getHttpHeaders(),HttpStatus.OK);
    }

    /**
     * This method will call scsb-circ microservice to remove exception request in the scsb database.
     *
     * @return the response entity
     */
    @RequestMapping(value = "/purgeExceptionRequests", method = RequestMethod.GET)
    @ApiOperation(value = "purgeExceptionRequests",notes = "The Purge exception requests API as the name suggests purges all requests that have gone to an exception status in SCSB database. This API is internally used by SCSB to purge requests with status as exception through a scheduled job at regular intervals.", nickname = "purgeExceptionRequests", position = 0)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    public ResponseEntity purgeExceptionRequests() {
        Map response = null;
        try {
            HttpEntity requestEntity = getHttpEntity();
            ResponseEntity<Map> responseEntity = getRestTemplate().exchange(getScsbCircUrl() + ReCAPConstants.REST_URL_PURGE_EXCEPTION_REQUESTS, HttpMethod.GET, requestEntity, Map.class);
            response = responseEntity.getBody();
        } catch (Exception e) {
            getLogger().error("Exception", e);
        }
        return new ResponseEntity(response, getHttpHeaders(), HttpStatus.OK);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(ReCAPConstants.RESPONSE_DATE, new Date().toString());
        return responseHeaders;
    }

}
