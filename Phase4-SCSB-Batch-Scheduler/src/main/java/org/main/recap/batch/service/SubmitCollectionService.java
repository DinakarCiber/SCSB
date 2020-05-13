package org.main.recap.batch.service;

import org.main.recap.RecapConstants;
import org.main.recap.model.jpa.JobEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by harikrishnanv on 19/6/17.
 */
@Service
public class SubmitCollectionService {

    private static final Logger logger = LoggerFactory.getLogger(SubmitCollectionService.class);

    /**
     * Gets rest template.
     *
     * @return the rest template
     */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * This method makes a rest call to scsb circ microservice to initiate the submit collection process.
     *
     * @param scsbCircUrl    the scsb circ url
     * @return status of the submit collection process.
     */
    public String submitCollection(String scsbCircUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity<JobEntity> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = getRestTemplate().exchange(scsbCircUrl + RecapConstants.SUBMIT_COLLECTION_URL, HttpMethod.POST, httpEntity, String.class);
        return responseEntity.getBody();
    }
}
