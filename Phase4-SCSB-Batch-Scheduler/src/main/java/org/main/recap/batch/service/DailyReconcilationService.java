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

/**
 * Created by akulak on 10/5/17.
 */

@Service
public class DailyReconcilationService {

    private static final Logger logger = LoggerFactory.getLogger(DailyReconcilationService.class);

    /**
     * Gets rest template.
     *
     * @return the rest template
     */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * This method makes a rest call to scsb circ microservice to initiate the daily reconciliation process.
     *
     * @param solrCircUrl    the scsb circ url
     * @return status of the daily reconciliation process
     */
    public String dailyReconcilation(String solrCircUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = getRestTemplate().exchange(solrCircUrl + RecapConstants.DAILY_RECONCILATION_URL, HttpMethod.POST, httpEntity, String.class);
        return responseEntity.getBody();
    }
}
