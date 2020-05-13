package org.main.recap.batch.service;

import org.junit.Test;
import org.main.recap.BaseTestCase;
import org.main.recap.RecapConstants;
import org.main.recap.model.EmailPayLoad;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rajeshbabuk on 19/4/17.
 */
public class EmailServiceUT extends BaseTestCase {

    @Value("${scsb.solr.client.url}")
    String solrClientUrl;

    @Mock
    RestTemplate restTemplate;

    @Mock
    EmailService emailService;

    @Test
    public void testEmailService() {
        EmailPayLoad emailPayLoad = new EmailPayLoad();
        emailPayLoad.setJobName(RecapConstants.PURGE_EXCEPTION_REQUESTS);
        emailPayLoad.setStartDate(new Date());
        emailPayLoad.setStatus(RecapConstants.SUCCESS);

        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity<EmailPayLoad> httpEntity = new HttpEntity<>(emailPayLoad, headers);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(RecapConstants.SUCCESS, HttpStatus.OK);
        Mockito.when(emailService.getRestTemplate()).thenReturn(restTemplate);
        Mockito.when(emailService.getRestTemplate().exchange(solrClientUrl + RecapConstants.BATCH_JOB_EMAIL_URL, HttpMethod.POST, httpEntity, String.class)).thenReturn(responseEntity);
        Mockito.when(emailService.sendEmail(solrClientUrl, emailPayLoad)).thenCallRealMethod();
        String status = emailService.sendEmail(solrClientUrl, emailPayLoad);
        assertNotNull(status);
        assertEquals(status, RecapConstants.SUCCESS);
    }
}
