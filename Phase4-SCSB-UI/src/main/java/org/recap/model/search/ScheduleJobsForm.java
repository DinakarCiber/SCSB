package org.recap.model.search;

import org.recap.model.jpa.JobEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 4/4/17.
 */
public class ScheduleJobsForm {

    private Integer jobId;
    private String jobName;
    private String jobDescription;
    private String cronExpression;
    private String scheduleType;
    private String message;
    private String errorMessage;
    private List<JobEntity> jobEntities = new ArrayList<>();

    /**
     * Gets job id.
     *
     * @return the job id
     */
    public Integer getJobId() {
        return jobId;
    }

    /**
     * Sets job id.
     *
     * @param jobId the job id
     */
    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    /**
     * Gets job name.
     *
     * @return the job name
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * Sets job name.
     *
     * @param jobName the job name
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * Gets job description.
     *
     * @return the job description
     */
    public String getJobDescription() {
        return jobDescription;
    }

    /**
     * Sets job description.
     *
     * @param jobDescription the job description
     */
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    /**
     * Gets cron expression.
     *
     * @return the cron expression
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * Sets cron expression.
     *
     * @param cronExpression the cron expression
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    /**
     * Gets schedule type.
     *
     * @return the schedule type
     */
    public String getScheduleType() {
        return scheduleType;
    }

    /**
     * Sets schedule type.
     *
     * @param scheduleType the schedule type
     */
    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets job entities.
     *
     * @return the job entities
     */
    public List<JobEntity> getJobEntities() {
        return jobEntities;
    }

    /**
     * Sets job entities.
     *
     * @param jobEntities the job entities
     */
    public void setJobEntities(List<JobEntity> jobEntities) {
        this.jobEntities = jobEntities;
    }
}
