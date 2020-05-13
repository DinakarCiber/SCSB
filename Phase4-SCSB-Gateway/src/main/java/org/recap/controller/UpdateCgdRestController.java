package org.recap.controller;

import org.recap.ReCAPConstants;
import org.recap.Service.RestHeaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by rajeshbabuk on 3/1/17.
 */
@RestController
@RequestMapping("/updateCgdService")
public class UpdateCgdRestController {

    private static final Logger logger = LoggerFactory.getLogger(UpdateCgdRestController.class);

    @Value("${scsb.solr.client.url}")
    private String scsbSolrClient;


    @Autowired
    RestHeaderService restHeaderService;

    public RestHeaderService getRestHeaderService(){
        return restHeaderService;
    }

    /**
     * Gets rest template.
     *
     * @return the rest template
     */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * Gets scsb solr client url.
     *
     * @return the scsb solr client url
     */
    public String getScsbSolrClientUrl() {
        return scsbSolrClient;
    }

    /**
     * Sets scsb solr client url.
     *
     * @param scsbSolrClientUrl the scsb solr client url
     */
    public void setScsbSolrClientUrl(String scsbSolrClientUrl) {
        this.scsbSolrClient = scsbSolrClientUrl;
    }

    /**
     * This method will call scsb-solr-client microservice to update CGD for an item in scsb database and scsb solr.
     *
     * @param itemBarcode                   the item barcode
     * @param owningInstitution             the owning institution
     * @param oldCollectionGroupDesignation the old collection group designation
     * @param newCollectionGroupDesignation the new collection group designation
     * @param cgdChangeNotes                the cgd change notes
     * @return the string
     */
    @RequestMapping(value="/updateCgd", method = RequestMethod.GET)
    public String updateCgdForItem(@RequestParam String itemBarcode, @RequestParam String owningInstitution, @RequestParam String oldCollectionGroupDesignation, @RequestParam String newCollectionGroupDesignation, @RequestParam String cgdChangeNotes, @RequestParam String userName) {
        String statusResponse = null;
        try {
            HttpEntity requestEntity = new HttpEntity<>(getRestHeaderService().getHttpHeaders());

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getScsbSolrClientUrl() + ReCAPConstants.URL_UPDATE_CGD)
                    .queryParam(ReCAPConstants.CGD_UPDATE_ITEM_BARCODE, itemBarcode)
                    .queryParam(ReCAPConstants.OWNING_INSTITUTION, owningInstitution)
                    .queryParam(ReCAPConstants.OLD_CGD, oldCollectionGroupDesignation)
                    .queryParam(ReCAPConstants.NEW_CGD, newCollectionGroupDesignation)
                    .queryParam(ReCAPConstants.CGD_CHANGE_NOTES, cgdChangeNotes)
                    .queryParam(ReCAPConstants.USER_NAME, userName);

            ResponseEntity<String> responseEntity = getRestTemplate().exchange(builder.build().encode().toUri(), HttpMethod.GET, requestEntity, String.class);
            statusResponse = responseEntity.getBody();
        } catch (Exception e) {
            logger.error(ReCAPConstants.LOG_ERROR,e);
            statusResponse = ReCAPConstants.FAILURE + "-" + e.getMessage();
        }
        return statusResponse;
    }
}
