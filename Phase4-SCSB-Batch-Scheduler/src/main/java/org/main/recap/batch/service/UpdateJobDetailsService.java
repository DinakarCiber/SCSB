package org.main.recap.batch.service;

import org.apache.commons.lang.StringUtils;
import org.main.recap.RecapConstants;
import org.main.recap.jpa.JobDetailsRepository;
import org.main.recap.model.jpa.JobEntity;
import org.quartz.CronExpression;
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
 * Created by rajeshbabuk on 12/4/17.
 */
@Service
public class UpdateJobDetailsService {

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
     * This method makes a rest call to solr client microservice to update the job with next execution time.
     *
     * @param solrClientUrl    the solr client url
     * @param jobName          the job name
     * @param lastExecutedTime the last executed time
     * @return status of updating the job
     */
    public String updateJob(String solrClientUrl, String jobName, Date lastExecutedTime, Long jobInstanceId) throws Exception {
        JobEntity jobEntity = getJobDetailsRepository().findByJobName(jobName);
        jobEntity.setLastExecutedTime(lastExecutedTime);
        jobEntity.setJobInstanceId(jobInstanceId.intValue());
        if (StringUtils.isNotBlank(jobEntity.getCronExpression())) {
            CronExpression cronExpression = new CronExpression(jobEntity.getCronExpression());
            jobEntity.setNextRunTime(cronExpression.getNextValidTimeAfter(lastExecutedTime));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
        HttpEntity<JobEntity> httpEntity = new HttpEntity<>(jobEntity, headers);

        ResponseEntity<String> responseEntity = getRestTemplate().exchange(solrClientUrl + RecapConstants.UPDATE_JOB_URL, HttpMethod.POST, httpEntity, String.class);
        return responseEntity.getBody();
    }
}
