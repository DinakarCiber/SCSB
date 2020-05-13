package org.main.recap.batch.service;

import org.main.recap.RecapConstants;
import org.main.recap.jpa.JobDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rajeshbabuk on 12/4/17.
 */
@Service
public class ReportDeletedRecordsService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JobDetailsRepository jobDetailsRepository;

    /**
     * Gets rest template.
     *
     * @return the rest template
     */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * Gets job details repository.
     *
     * @return the job details repository
     */
    public JobDetailsRepository getJobDetailsRepository() {
        return jobDetailsRepository;
    }

    /**
     * This method makes a rest call to scsb-circ microservice to report about the deleted records, in important transaction tables in SCSB.
     *
     * @param scsbCircUrl
     * @return
     */
    public String reportDeletedRecords(String scsbCircUrl){
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity httpEntity = new HttpEntity<>(headers);
        logger.info(scsbCircUrl + RecapConstants.REPORT_DELETED_RECORDS_URL);
        ResponseEntity<String> responseEntity = getRestTemplate().exchange(scsbCircUrl + RecapConstants.REPORT_DELETED_RECORDS_URL, HttpMethod.GET, httpEntity, String.class);
        return responseEntity.getBody();
    }
}
