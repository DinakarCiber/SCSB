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

import java.util.Map;

/**
 * Created by rajeshbabuk on 18/4/17.
 */

@Service
public class PurgeExceptionRequestsService {

    private static final Logger logger = LoggerFactory.getLogger(PurgeExceptionRequestsService.class);

    /**
     * Gets rest template.
     *
     * @return the rest template
     */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * This method makes a rest call to scsb circ microservice to initiate the process of purging exception requests.
     *
     * @param scsbCircUrl    the scsb circ url
     * @return status of purging exception requests process
     */
    public Map<String, String> purgeExceptionRequests(String scsbCircUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Map> responseEntity = getRestTemplate().exchange(scsbCircUrl + RecapConstants.PURGE_EXCEPTION_REQUEST_URL, HttpMethod.GET, httpEntity, Map.class);
        return responseEntity.getBody();
    }
}
