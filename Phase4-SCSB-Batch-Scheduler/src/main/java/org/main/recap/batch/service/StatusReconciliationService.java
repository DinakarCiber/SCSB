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
 * Created by hemalathas on 1/6/17.
 */
@Service
public class StatusReconciliationService {

    private static final Logger logger = LoggerFactory.getLogger(StatusReconciliationService.class);

    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public String statusReconcilation(String scsbCircUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity<JobEntity> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = getRestTemplate().exchange(scsbCircUrl + RecapConstants.STATUS_RECOCILATION_URL, HttpMethod.GET, httpEntity, String.class);
        return responseEntity.getBody();
    }

}
