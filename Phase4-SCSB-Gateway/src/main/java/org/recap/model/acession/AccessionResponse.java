package org.recap.model.acession;

/**
 * Created by harikrishnanv on 16/6/17.
 */
public class AccessionResponse {

    private String itemBarcode;

    private String message;

    /**
     * This method gets item barcode.
     *
     * @return the item barcode
     */
    public String getItemBarcode() {
        return itemBarcode;
    }

    /**
     * This method sets item barcode.
     *
     * @param itemBarcode the item barcode
     */
    public void setItemBarcode(String itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    /**
     * This method gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
