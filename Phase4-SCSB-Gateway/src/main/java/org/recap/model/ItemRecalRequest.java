package org.recap.model;

/**
 * Created by sudhishk on 15/12/16.
 */
public class ItemRecalRequest extends AbstractRequestItem {
    private String patronIdentifier;
    private String bibId;
    private String pickupLocation;

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

    /**
     * Gets bib id.
     *
     * @return the bib id
     */
    public String getBibId() {
        return bibId;
    }

    /**
     * Sets bib id.
     *
     * @param bibId the bib id
     */
    public void setBibId(String bibId) {
        this.bibId = bibId;
    }

    /**
     * Gets pickup location.
     *
     * @return the pickup location
     */
    public String getPickupLocation() {
        return pickupLocation;
    }

    /**
     * Sets pickup location.
     *
     * @param pickupLocation the pickup location
     */
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
}
