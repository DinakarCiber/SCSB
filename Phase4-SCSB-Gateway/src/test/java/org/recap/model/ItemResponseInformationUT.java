package org.recap.model;

import org.junit.Test;
import org.recap.BaseTestCase;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hemalathas on 24/3/17.
 */
public class ItemResponseInformationUT extends BaseTestCase{

    @Test
    public void testItemResponseInformation(){
        ItemResponseInformation itemResponseInformation = new ItemResponseInformation();
        itemResponseInformation.setPatronBarcode("453454564");
        itemResponseInformation.setItemBarcodes(Arrays.asList("4534"));
        itemResponseInformation.setRequestType("Recall");
        itemResponseInformation.setDeliveryLocation("PB");
        itemResponseInformation.setRequestingInstitution("CUL");
        itemResponseInformation.setBibliographicId("1");
        itemResponseInformation.setExpirationDate(new Date().toString());
        itemResponseInformation.setScreenMessage("test");
        itemResponseInformation.setSuccess(true);
        itemResponseInformation.setEmailAddress("hemalatha.s@htcindia.com");
        itemResponseInformation.setTitleIdentifier("test");

        assertNotNull(itemResponseInformation.getPatronBarcode());
        assertNotNull(itemResponseInformation.getBibliographicId());
        assertNotNull(itemResponseInformation.getItemBarcodes());
        assertNotNull(itemResponseInformation.getRequestType());
        assertNotNull(itemResponseInformation.getDeliveryLocation());
        assertNotNull(itemResponseInformation.getRequestingInstitution());
        assertNotNull(itemResponseInformation.getExpirationDate());
        assertNotNull(itemResponseInformation.getScreenMessage());
        assertNotNull(itemResponseInformation.isSuccess());
        assertNotNull(itemResponseInformation.getEmailAddress());
        assertNotNull(itemResponseInformation.getTitleIdentifier());
    }



}