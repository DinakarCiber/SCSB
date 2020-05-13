package org.recap.controller;

import org.recap.ReCAPConstants;
import org.recap.Service.RestHeaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akulak on 27/2/18 .
 */
@RestController
@RequestMapping("/deAccessionService")
public class DeAccessionRestController {

    private static final Logger logger = LoggerFactory.getLogger(DeAccessionRestController.class);

    @Value("${scsb.circ.url}")
    private String scsbCircUrl;

    @Autowired
    private RestHeaderService restHeaderService;

    /**
     * Gets rest template.
     *
     * @return the rest template
     */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * Gets scsb circ url.
     *
     * @return the scsb circ url
     */
    public String getScsbCircUrl() {
        return scsbCircUrl;
    }

    /**
     * Get rest header service rest header service.
     *
     * @return the rest header service
     */
    public RestHeaderService getRestHeaderService(){
        return restHeaderService;
    }

    /**
     * This method will call scsb-circ microservice to deaccession an item in SCSB.
     *
     * @param deAccessionRequest the de accession request
     * @return the map
     */
    @RequestMapping(value = "/deaccession", method = RequestMethod.POST)
    public Map<String, String> deAccession(@RequestBody String deAccessionRequest) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            HttpEntity<String> requestEntity = new HttpEntity<>(deAccessionRequest, getRestHeaderService().getHttpHeaders());
            resultMap = getRestTemplate().postForObject(getScsbCircUrl() + "/sharedCollection/deAccession", requestEntity, Map.class);
        } catch (Exception ex) {
            logger.error(ReCAPConstants.LOG_ERROR,ex);
        }
        return resultMap;
    }
}
