package org.main.recap.batch.service;

import org.main.recap.RecapConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by angelind on 17/5/17.
 */
@Service
public class AccessionService {

    private static final Logger logger = LoggerFactory.getLogger(AccessionService.class);

    /**
     * Gets rest template.
     *
     * @return the rest template
     */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * This method makes a rest call to solr client microservice to initiate the accession process.
     *
     * @param solrClientUrl     the solr client url
     * @return status of the accession process
     */
    public String processAccession(String solrClientUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity<Date> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = getRestTemplate().exchange(solrClientUrl + RecapConstants.ACCESSION_URL, HttpMethod.GET, httpEntity, String.class);
        return responseEntity.getBody();
    }

}
