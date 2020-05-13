package org.recap.model;

import org.junit.Test;
import org.recap.BaseTestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hemalathas on 23/3/17.
 */
public class ReportsResponseUT extends BaseTestCase {

    @Test
    public void testReportResponse(){
        ReportsResponse reportsResponse = new ReportsResponse();
        reportsResponse.setAccessionPrivatePulCount(1);
        reportsResponse.setAccessionPrivateCulCount(1);
        reportsResponse.setAccessionPrivateNyplCount(1);
        reportsResponse.setAccessionPrivatePulCount(1);
        reportsResponse.setAccessionPrivateCulCount(1);
        reportsResponse.setAccessionPrivateNyplCount(1);
        reportsResponse.setAccessionPrivatePulCount(1);
        reportsResponse.setAccessionPrivateCulCount(1);
        reportsResponse.setAccessionPrivateNyplCount(1);
        reportsResponse.setDeaccessionPrivatePulCount(1);
        reportsResponse.setDeaccessionPrivateCulCount(1);
        reportsResponse.setDeaccessionPrivateNyplCount(1);
        reportsResponse.setDeaccessionPrivatePulCount(1);
        reportsResponse.setDeaccessionPrivateCulCount(1);
        reportsResponse.setDeaccessionPrivateNyplCount(1);
        reportsResponse.setDeaccessionPrivatePulCount(1);
        reportsResponse.setDeaccessionPrivateCulCount(1);
        reportsResponse.setDeaccessionPrivateNyplCount(1);
        reportsResponse.setOpenPulCgdCount(1);
        reportsResponse.setOpenCulCgdCount(1);
        reportsResponse.setOpenNyplCgdCount(1);
        reportsResponse.setSharedPulCgdCount(1);
        reportsResponse.setSharedCulCgdCount(1);
        reportsResponse.setSharedNyplCgdCount(1);
        reportsResponse.setPrivatePulCgdCount(1);
        reportsResponse.setPrivateCulCgdCount(1);
        reportsResponse.setPrivateNyplCgdCount(1);
        reportsResponse.setTotalRecordsCount("1");
        reportsResponse.setTotalPageCount(1);
        reportsResponse.setMessage("Success");
        reportsResponse.setIncompletePageNumber(1);
        reportsResponse.setIncompletePageSize(10);
        reportsResponse.setIncompleteTotalPageCount(1);
        reportsResponse.setIncompleteTotalRecordsCount("1");
        DeaccessionItemResultsRow deaccessionItemResultsRow = new DeaccessionItemResultsRow();
        deaccessionItemResultsRow.setItemBarcode("32145686568567");
        deaccessionItemResultsRow.setDeaccessionNotes("test");
        deaccessionItemResultsRow.setDeaccessionOwnInst("PUL");
        deaccessionItemResultsRow.setItemId(1);
        deaccessionItemResultsRow.setTitle("test");
        deaccessionItemResultsRow.setCgd("Open");
        IncompleteReportResultsRow incompleteReportResultsRow = new IncompleteReportResultsRow();
        incompleteReportResultsRow.setTitle("test");
        incompleteReportResultsRow.setAuthor("john");
        incompleteReportResultsRow.setBarcode("32145564654564");
        incompleteReportResultsRow.setCreatedDate(new Date().toString());
        incompleteReportResultsRow.setCustomerCode("PB");
        incompleteReportResultsRow.setOwningInstitution("PUL");
        reportsResponse.setDeaccessionItemResultsRows(Arrays.asList(deaccessionItemResultsRow));
        reportsResponse.setIncompleteReportResultsRows(Arrays.asList(incompleteReportResultsRow));

        assertNotNull(reportsResponse.getAccessionPrivatePulCount());
        assertNotNull(reportsResponse.getAccessionPrivateCulCount());
        assertNotNull(reportsResponse.getAccessionPrivateNyplCount());
        assertNotNull(reportsResponse.getAccessionSharedPulCount());
        assertNotNull(reportsResponse.getAccessionSharedCulCount());
        assertNotNull(reportsResponse.getAccessionSharedNyplCount());
        assertNotNull(reportsResponse.getAccessionOpenPulCount());
        assertNotNull(reportsResponse.getAccessionOpenCulCount());
        assertNotNull(reportsResponse.getAccessionOpenNyplCount());
        assertNotNull(reportsResponse.getDeaccessionPrivatePulCount());
        assertNotNull(reportsResponse.getDeaccessionPrivateCulCount());
        assertNotNull(reportsResponse.getDeaccessionPrivateNyplCount());
        assertNotNull(reportsResponse.getDeaccessionSharedPulCount());
        assertNotNull(reportsResponse.getDeaccessionSharedCulCount());
        assertNotNull(reportsResponse.getDeaccessionSharedNyplCount());
        assertNotNull(reportsResponse.getDeaccessionOpenPulCount());
        assertNotNull(reportsResponse.getDeaccessionOpenCulCount());
        assertNotNull(reportsResponse.getDeaccessionOpenNyplCount());
        assertNotNull(reportsResponse.getOpenPulCgdCount());
        assertNotNull(reportsResponse.getOpenCulCgdCount());
        assertNotNull(reportsResponse.getOpenNyplCgdCount());
        assertNotNull(reportsResponse.getSharedPulCgdCount());
        assertNotNull(reportsResponse.getSharedCulCgdCount());
        assertNotNull(reportsResponse.getSharedNyplCgdCount());
        assertNotNull(reportsResponse.getPrivatePulCgdCount());
        assertNotNull(reportsResponse.getPrivateCulCgdCount());
        assertNotNull(reportsResponse.getPrivateNyplCgdCount());
        assertNotNull(reportsResponse.getTotalRecordsCount());
        assertNotNull(reportsResponse.getTotalPageCount());
        assertNotNull(reportsResponse.getMessage());
        assertNotNull(reportsResponse.getDeaccessionItemResultsRows());
        assertNotNull(reportsResponse.getIncompleteTotalRecordsCount());
        assertNotNull(reportsResponse.getIncompleteTotalPageCount());
        assertNotNull(reportsResponse.getIncompleteReportResultsRows());
        assertNotNull(reportsResponse.getIncompletePageNumber());
        assertNotNull(reportsResponse.getIncompletePageSize());
        assertNotNull(incompleteReportResultsRow.getTitle());
        assertNotNull(incompleteReportResultsRow.getAuthor());
        assertNotNull(incompleteReportResultsRow.getCreatedDate());
        assertNotNull(incompleteReportResultsRow.getOwningInstitution());
        assertNotNull(incompleteReportResultsRow.getCustomerCode());
        assertNotNull(incompleteReportResultsRow.getBarcode());

    }



}