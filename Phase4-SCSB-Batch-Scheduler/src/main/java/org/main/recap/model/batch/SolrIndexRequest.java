package org.main.recap.model.batch;

import java.util.Date;

/**
 * Created by rajeshbabuk on 3/4/17.
 */
public class SolrIndexRequest {
    private String processType;
    private Date createdDate;

    /**
     * Gets process type.
     *
     * @return the process type
     */
    public String getProcessType() {
        return processType;
    }

    /**
     * Sets process type.
     *
     * @param processType the process type
     */
    public void setProcessType(String processType) {
        this.processType = processType;
    }

    /**
     * Gets created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets created date.
     *
     * @param createdDate the created date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
