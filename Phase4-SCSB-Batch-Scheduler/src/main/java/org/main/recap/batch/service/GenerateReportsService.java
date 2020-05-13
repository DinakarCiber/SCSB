package org.main.recap.batch.service;

import org.main.recap.util.JobDataParameterUtil;
import org.main.recap.RecapConstants;
import org.main.recap.model.batch.SolrIndexRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by angelind on 2/5/17.
 */
@Service
public class GenerateReportsService {

    private static final Logger logger = LoggerFactory.getLogger(GenerateReportsService.class);

    @Autowired
    JobDataParameterUtil jobDataParameterUtil;

    /**
     * Gets rest template.
     *
     * @return the rest template
     */
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * This method makes a rest call to solr client microservice to generate report for given process type.
     *
     * @param solrClientUrl     the solr client url
     * @param reportCreatedDate the report created date
     * @param jobName           the job name
     * @return status of the generated report
     */
    public String generateReport(String solrClientUrl, Date reportCreatedDate, String jobName) {
        SolrIndexRequest solrIndexRequest = getSolrIndexRequest(reportCreatedDate, jobName);
        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity<SolrIndexRequest> httpEntity = new HttpEntity<>(solrIndexRequest, headers);
        ResponseEntity<String> responseEntity = getRestTemplate().exchange(solrClientUrl + RecapConstants.GENERATE_REPORT_URL, HttpMethod.POST, httpEntity, String.class);
        return responseEntity.getBody();
    }

    /**
     * Gets solr index request.
     *
     * @param reportCreatedDate the report created date
     * @param reportType        the report type
     * @return the solr index request
     */
    public SolrIndexRequest getSolrIndexRequest(Date reportCreatedDate, String reportType) {
        SolrIndexRequest solrIndexRequest = new SolrIndexRequest();
        solrIndexRequest.setProcessType(reportType);
        solrIndexRequest.setCreatedDate(jobDataParameterUtil.getFromDate(reportCreatedDate));
        return solrIndexRequest;
    }


}
