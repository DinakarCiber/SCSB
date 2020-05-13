package org.recap.model;

/**
 * Created by rajeshbabuk on 5/4/17.
 */
public class ScheduleJobRequest {

    private Integer jobId;
    private String jobName;
    private String cronExpression;
    private String scheduleType;

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
}
