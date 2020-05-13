package org.recap.model;

/**
 * Created by sudhishk on 15/12/16.
 */
public class ItemCheckOutRequest extends AbstractRequestItem {

    private String patronIdentifier;

    /**
     * Gets patron identifier.
     *
     * @return the patron identifier
     */
    public String getPatronIdentifier() {
        return patronIdentifier;
    }

    /**
     * Sets patron identifier.
     *
     * @param patronIdentifier the patron identifier
     */
    public void setPatronIdentifier(String patronIdentifier) {
        this.patronIdentifier = patronIdentifier;
    }



    
}
