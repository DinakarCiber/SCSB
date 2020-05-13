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
import org.springframework.web.client.RestTemplate;

/**
 * Created by angelind on 14/9/17.
 */
@Service
public class CheckAndNotifyPendingRequestService {

    private static final Logger logger = LoggerFactory.getLogger(CheckAndNotifyPendingRequestService.class);

    /**
     * Gets rest template.
     *
     * @return the rest template
     */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * Check pending msges in queue string.
     *
     * @param scsbCircUrl the scsb circ url
     * @return the string
     */
    public String checkPendingMsgesInQueue(String scsbCircUrl) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<JobEntity> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = getRestTemplate().exchange(scsbCircUrl + RecapConstants.NOTIFY_IF_PENDING_REQUEST, HttpMethod.POST, httpEntity, String.class);
        return responseEntity.getBody();
    }

}
