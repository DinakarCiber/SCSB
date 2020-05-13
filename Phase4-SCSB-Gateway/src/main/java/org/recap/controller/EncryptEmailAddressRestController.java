package org.recap.controller;

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

/**
 * Created by akulak on 21/9/17.
 */
@RestController
@RequestMapping("/encryptEmailAddressService")
public class EncryptEmailAddressRestController {

    private static final Logger logger = LoggerFactory.getLogger(EncryptEmailAddressRestController.class);

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

    private HttpHeaders getHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(ReCAPConstants.RESPONSE_DATE, new Date().toString());
        return responseHeaders;
    }

    @RequestMapping(value = "/encryptEmailAddress", method = RequestMethod.GET)
    public ResponseEntity encryptEmailAddress() {
        String response = "";
        try {
            HttpEntity requestEntity = getHttpEntity();
            ResponseEntity<String> exchange = getRestTemplate().exchange(getScsbCircUrl() + "/encryptEmailAddress/startEncryptEmailAddress", HttpMethod.GET, requestEntity, String.class);
            response  = exchange.getBody();
        } catch (Exception e) {
            getLogger().error("Exception", e);
        }
        return new ResponseEntity(response, getHttpHeaders(), HttpStatus.OK);
    }
}
