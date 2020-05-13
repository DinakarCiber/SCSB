package org.main.recap.model;

import java.util.Date;

/**
 * Created by rajeshbabuk on 5/4/17.
 */
public class ScheduleJobResponse {

    private String message;
    private Date nextRunTime;

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
     * Gets next run time.
     *
     * @return the next run time
     */
    public Date getNextRunTime() {
        return nextRunTime;
    }

    /**
     * Sets next run time.
     *
     * @param nextRunTime the next run time
     */
    public void setNextRunTime(Date nextRunTime) {
        this.nextRunTime = nextRunTime;
    }
}
