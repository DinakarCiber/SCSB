package org.recap.model;

import java.util.List;

/**
 * Created by akulak on 3/3/17.
 */
public class ItemAvailabityStatusRequest {

    private List<String> Barcodes;

    /**
     * Gets barcodes.
     *
     * @return the barcodes
     */
    public List<String> getBarcodes() {
        return Barcodes;
    }

    /**
     * Sets barcodes.
     *
     * @param barcodes the barcodes
     */
    public void setBarcodes(List<String> barcodes) {
        Barcodes = barcodes;
    }
}
