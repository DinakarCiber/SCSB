package org.recap.model;

import java.util.List;

/**
 * Created by sudhishk on 15/12/16.
 */
public abstract class AbstractRequestItem {

    private List<String> itemBarcodes;
    private String itemOwningInstitution=""; // PUL, CUL, NYPL

    /**
     * Gets item barcodes.
     *
     * @return the item barcodes
     */
    public List<String> getItemBarcodes() {
        return itemBarcodes;
    }

    /**
     * Sets item barcodes.
     *
     * @param itemBarcodes the item barcodes
     */
    public void setItemBarcodes(List<String> itemBarcodes) {
        this.itemBarcodes = itemBarcodes;
    }

    /**
     * Gets item owning institution.
     *
     * @return the item owning institution
     */
    public String getItemOwningInstitution() {
        return itemOwningInstitution;
    }

    /**
     * Sets item owning institution.
     *
     * @param itemOwningInstitution the item owning institution
     */
    public void setItemOwningInstitution(String itemOwningInstitution) {
        this.itemOwningInstitution = itemOwningInstitution;
    }
}
