package org.main.recap.batch.service;

import org.main.recap.RecapConstants;
import org.main.recap.model.EmailPayLoad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rajeshbabuk on 10/4/17.
 */
@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    /**
     * Gets rest template.
     *
     * @return the rest template
     */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * This method makes a rest call to solr client microservice to send an email with the job execution information.
     *
     * @param solrClientUrl  the solr client url
     * @param emailPayLoad   the email pay load
     * @return status of sending email
     */
    public String sendEmail(String solrClientUrl, EmailPayLoad emailPayLoad) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity<EmailPayLoad> httpEntity = new HttpEntity<>(emailPayLoad, headers);
        ResponseEntity<String> responseEntity = getRestTemplate().exchange(solrClientUrl + RecapConstants.BATCH_JOB_EMAIL_URL, HttpMethod.POST, httpEntity, String.class);
        return responseEntity.getBody();
    }
}
