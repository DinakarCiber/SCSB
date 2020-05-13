package org.main.recap.batch.service;

import org.main.recap.RecapConstants;
import org.main.recap.model.batch.SolrIndexRequest;
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
 * Created by rajeshbabuk on 3/4/17.
 */
@Service
public class MatchingAlgorithmService {

    private static final Logger logger = LoggerFactory.getLogger(MatchingAlgorithmService.class);

    /**
     * Gets rest template.
     *
     * @return the rest template
     */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * This method makes a rest call to solr client microservice to initiate the matching algorithm process.
     *
     * @param solrClientUrl  the solr client url
     * @param createdDate    the created date
     * @return status of the matching algorithm process.
     */
    public String initiateMatchingAlgorithm(String solrClientUrl, Date createdDate) {
        SolrIndexRequest solrIndexRequest = getSolrIndexRequest(createdDate);
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity<SolrIndexRequest> httpEntity = new HttpEntity<>(solrIndexRequest, headers);
        ResponseEntity<String> responseEntity = getRestTemplate().exchange(solrClientUrl + RecapConstants.MATCHING_ALGORITHM_URL, HttpMethod.POST, httpEntity, String.class);
        return responseEntity.getBody();
    }

    /**
     * Gets solr index request.
     *
     * @param createdDate the created date
     * @return the solr index request
     */
    public SolrIndexRequest getSolrIndexRequest(Date createdDate) {
        SolrIndexRequest solrIndexRequest = new SolrIndexRequest();
        solrIndexRequest.setProcessType(RecapConstants.ONGOING_MATCHING_ALGORITHM_JOB);
        solrIndexRequest.setCreatedDate(createdDate);
        return solrIndexRequest;
    }
}
