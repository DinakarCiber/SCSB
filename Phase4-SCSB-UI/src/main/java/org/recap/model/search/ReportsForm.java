package org.recap.model.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeshbabuk on 13/10/16.
 */
public class ReportsForm {

    private String showBy;
    private String requestType;
    private String requestFromDate;
    private String requestToDate;
    private String accessionDeaccessionFromDate;
    private String accessionDeaccessionToDate;

    private long retrievalRequestPulCount;
    private long retrievalRequestCulCount;
    private long retrievalRequestNyplCount;

    private long recallRequestPulCount;
    private long recallRequestCulCount;
    private long recallRequestNyplCount;

    private long physicalPrivatePulCount;
    private long physicalPrivateCulCount;
    private long physicalPrivateNyplCount;

    private long physicalSharedPulCount;
    private long physicalSharedCulCount;
    private long physicalSharedNyplCount;

    private long eddPrivatePulCount;
    private long eddPrivateCulCount;
    private long eddPrivateNyplCount;

    private long eddSharedOpenPulCount;
    private long eddSharedOpenCulCount;
    private long eddSharedOpenNyplCount;

    private long accessionPrivatePulCount;
    private long accessionPrivateCulCount;
    private long accessionPrivateNyplCount;
    private long accessionSharedPulCount;
    private long accessionSharedCulCount;
    private long accessionSharedNyplCount;
    private long accessionOpenPulCount;
    private long accessionOpenCulCount;
    private long accessionOpenNyplCount;

    private long deaccessionPrivatePulCount;
    private long deaccessionPrivateCulCount;
    private long deaccessionPrivateNyplCount;
    private long deaccessionSharedPulCount;
    private long deaccessionSharedCulCount;
    private long deaccessionSharedNyplCount;
    private long deaccessionOpenPulCount;
    private long deaccessionOpenCulCount;
    private long deaccessionOpenNyplCount;

    private long openPulCgdCount;
    private long openCulCgdCount;
    private long openNyplCgdCount;
    private long sharedPulCgdCount;
    private long sharedCulCgdCount;
    private long sharedNyplCgdCount;
    private long privatePulCgdCount;
    private long privateCulCgdCount;
    private long privateNyplCgdCount;

    private boolean showILBDResults=false;
    private boolean showPartners=false;
    private boolean showRequestTypeTable=false;
    private boolean showAccessionDeaccessionTable=false;
    private boolean showReportResultsText=false;
    private boolean showNoteILBD=false;
    private boolean showNotePartners=false;
    private boolean showNoteRequestType=false;

    private boolean showRetrievalTable=false;
    private boolean showRecallTable=false;
    private boolean showRequestTypeShow=false;

    private List<String> reportRequestType=new ArrayList<>();
    private List<String> owningInstitutions = new ArrayList<>();
    private List<String> collectionGroupDesignations = new ArrayList<>();
    private List<DeaccessionItemResultsRow> deaccessionItemResultsRows = new ArrayList<>();

    private boolean showDeaccessionInformationTable = false;

    private String totalRecordsCount = "0";
    private Integer pageNumber = 0;
    private Integer pageSize = 10;
    private Integer totalPageCount = 0;
    private String deaccessionOwnInst;

    //IncompleteRecordsReport
    private String incompleteRequestingInstitution;
    private Integer incompletePageNumber = 0;
    private Integer incompletePageSize = 10;
    private String incompleteTotalRecordsCount = "0";
    private Integer incompleteTotalPageCount = 0;
    private List<IncompleteReportResultsRow> incompleteReportResultsRows = new ArrayList<>();
    private List<String> incompleteShowByInst =  new ArrayList<>();
    private boolean showIncompleteResults = false;
    private String errorMessage;
    private boolean showIncompletePagination = false;
    private boolean export = false;

    private long physicalPartnerSharedPulCount;
    private long physicalPartnerSharedCulCount;
    private long physicalPartnerSharedNyplCount;
    private long eddPartnerSharedOpenPulCount;
    private long eddPartnerSharedOpenCulCount;
    private long eddPartnerSharedOpenNyplCount;
    private long eddRequestPulCount;
    private long eddRequestCulCount;
    private long eddRequestNyplCount;


    /**
     * Instantiates a new Reports form.
     */
    public ReportsForm() {
        this.getOwningInstitutions().add("NYPL");
        this.getOwningInstitutions().add("CUL");
        this.getOwningInstitutions().add("PUL");

        this.getCollectionGroupDesignations().add("Shared");
        this.getCollectionGroupDesignations().add("Private");
        this.getCollectionGroupDesignations().add("Open");
    }

    /**
     * Gets show by.
     *
     * @return the show by
     */
    public String getShowBy() {
        return showBy;
    }

    /**
     * Sets show by.
     *
     * @param showBy the show by
     */
    public void setShowBy(String showBy) {
        this.showBy = showBy;
    }

    /**
     * Gets request type.
     *
     * @return the request type
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * Sets request type.
     *
     * @param requestType the request type
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * Gets request from date.
     *
     * @return the request from date
     */
    public String getRequestFromDate() {
        return requestFromDate;
    }

    /**
     * Sets request from date.
     *
     * @param requestFromDate the request from date
     */
    public void setRequestFromDate(String requestFromDate) {
        this.requestFromDate = requestFromDate;
    }

    /**
     * Gets request to date.
     *
     * @return the request to date
     */
    public String getRequestToDate() {
        return requestToDate;
    }

    /**
     * Sets request to date.
     *
     * @param requestToDate the request to date
     */
    public void setRequestToDate(String requestToDate) {
        this.requestToDate = requestToDate;
    }

    /**
     * Gets accession deaccession from date.
     *
     * @return the accession deaccession from date
     */
    public String getAccessionDeaccessionFromDate() {
        return accessionDeaccessionFromDate;
    }

    /**
     * Sets accession deaccession from date.
     *
     * @param accessionDeaccessionFromDate the accession deaccession from date
     */
    public void setAccessionDeaccessionFromDate(String accessionDeaccessionFromDate) {
        this.accessionDeaccessionFromDate = accessionDeaccessionFromDate;
    }

    /**
     * Gets accession deaccession to date.
     *
     * @return the accession deaccession to date
     */
    public String getAccessionDeaccessionToDate() {
        return accessionDeaccessionToDate;
    }

    /**
     * Sets accession deaccession to date.
     *
     * @param accessionDeaccessionToDate the accession deaccession to date
     */
    public void setAccessionDeaccessionToDate(String accessionDeaccessionToDate) {
        this.accessionDeaccessionToDate = accessionDeaccessionToDate;
    }

    /**
     * Gets retrieval request pul count.
     *
     * @return the retrieval request pul count
     */
    public long getRetrievalRequestPulCount() {
        return retrievalRequestPulCount;
    }

    /**
     * Sets retrieval request pul count.
     *
     * @param retrievalRequestPulCount the retrieval request pul count
     */
    public void setRetrievalRequestPulCount(long  retrievalRequestPulCount) {
        this.retrievalRequestPulCount = retrievalRequestPulCount;
    }

    /**
     * Gets retrieval request cul count.
     *
     * @return the retrieval request cul count
     */
    public long  getRetrievalRequestCulCount() {
        return retrievalRequestCulCount;
    }

    /**
     * Sets retrieval request cul count.
     *
     * @param retrievalRequestCulCount the retrieval request cul count
     */
    public void setRetrievalRequestCulCount(long  retrievalRequestCulCount) {
        this.retrievalRequestCulCount = retrievalRequestCulCount;
    }

    /**
     * Gets retrieval request nypl count.
     *
     * @return the retrieval request nypl count
     */
    public long  getRetrievalRequestNyplCount() {
        return retrievalRequestNyplCount;
    }

    /**
     * Sets retrieval request nypl count.
     *
     * @param retrievalRequestNyplCount the retrieval request nypl count
     */
    public void setRetrievalRequestNyplCount(long  retrievalRequestNyplCount) {
        this.retrievalRequestNyplCount = retrievalRequestNyplCount;
    }

    /**
     * Gets recall request pul count.
     *
     * @return the recall request pul count
     */
    public long getRecallRequestPulCount() {
        return recallRequestPulCount;
    }

    /**
     * Sets recall request pul count.
     *
     * @param recallRequestPulCount the recall request pul count
     */
    public void setRecallRequestPulCount(long recallRequestPulCount) {
        this.recallRequestPulCount = recallRequestPulCount;
    }

    /**
     * Gets recall request cul count.
     *
     * @return the recall request cul count
     */
    public long getRecallRequestCulCount() {
        return recallRequestCulCount;
    }

    /**
     * Sets recall request cul count.
     *
     * @param recallRequestCulCount the recall request cul count
     */
    public void setRecallRequestCulCount(long recallRequestCulCount) {
        this.recallRequestCulCount = recallRequestCulCount;
    }

    /**
     * Gets recall request nypl count.
     *
     * @return the recall request nypl count
     */
    public long getRecallRequestNyplCount() {
        return recallRequestNyplCount;
    }

    /**
     * Sets recall request nypl count.
     *
     * @param recallRequestNyplCount the recall request nypl count
     */
    public void setRecallRequestNyplCount(long recallRequestNyplCount) {
        this.recallRequestNyplCount = recallRequestNyplCount;
    }

    /**
     * Gets physical private pul count.
     *
     * @return the physical private pul count
     */
    public long getPhysicalPrivatePulCount() {
        return physicalPrivatePulCount;
    }

    /**
     * Sets physical private pul count.
     *
     * @param physicalPrivatePulCount the physical private pul count
     */
    public void setPhysicalPrivatePulCount(long physicalPrivatePulCount) {
        this.physicalPrivatePulCount = physicalPrivatePulCount;
    }

    /**
     * Gets physical private cul count.
     *
     * @return the physical private cul count
     */
    public long getPhysicalPrivateCulCount() {
        return physicalPrivateCulCount;
    }

    /**
     * Sets physical private cul count.
     *
     * @param physicalPrivateCulCount the physical private cul count
     */
    public void setPhysicalPrivateCulCount(long physicalPrivateCulCount) {
        this.physicalPrivateCulCount = physicalPrivateCulCount;
    }

    /**
     * Gets physical private nypl count.
     *
     * @return the physical private nypl count
     */
    public long getPhysicalPrivateNyplCount() {
        return physicalPrivateNyplCount;
    }

    /**
     * Sets physical private nypl count.
     *
     * @param physicalPrivateNyplCount the physical private nypl count
     */
    public void setPhysicalPrivateNyplCount(long physicalPrivateNyplCount) {
        this.physicalPrivateNyplCount = physicalPrivateNyplCount;
    }

    /**
     * Gets physical shared pul count.
     *
     * @return the physical shared pul count
     */
    public long getPhysicalSharedPulCount() {
        return physicalSharedPulCount;
    }

    /**
     * Sets physical shared pul count.
     *
     * @param physicalSharedPulCount the physical shared pul count
     */
    public void setPhysicalSharedPulCount(long physicalSharedPulCount) {
        this.physicalSharedPulCount = physicalSharedPulCount;
    }

    /**
     * Gets physical shared cul count.
     *
     * @return the physical shared cul count
     */
    public long getPhysicalSharedCulCount() {
        return physicalSharedCulCount;
    }

    /**
     * Sets physical shared cul count.
     *
     * @param physicalSharedCulCount the physical shared cul count
     */
    public void setPhysicalSharedCulCount(long physicalSharedCulCount) {
        this.physicalSharedCulCount = physicalSharedCulCount;
    }

    /**
     * Gets physical shared nypl count.
     *
     * @return the physical shared nypl count
     */
    public long getPhysicalSharedNyplCount() {
        return physicalSharedNyplCount;
    }

    /**
     * Sets physical shared nypl count.
     *
     * @param physicalSharedNyplCount the physical shared nypl count
     */
    public void setPhysicalSharedNyplCount(long physicalSharedNyplCount) {
        this.physicalSharedNyplCount = physicalSharedNyplCount;
    }

    /**
     * Is show ilbd results boolean.
     *
     * @return the boolean
     */
    public boolean isShowILBDResults() {
        return showILBDResults;
    }

    /**
     * Sets show ilbd results.
     *
     * @param showILBDResults the show ilbd results
     */
    public void setShowILBDResults(boolean showILBDResults) {
        this.showILBDResults = showILBDResults;
    }

    /**
     * Is show partners boolean.
     *
     * @return the boolean
     */
    public boolean isShowPartners() {
        return showPartners;
    }

    /**
     * Sets show partners.
     *
     * @param showPartners the show partners
     */
    public void setShowPartners(boolean showPartners) {
        this.showPartners = showPartners;
    }

    /**
     * Gets edd private pul count.
     *
     * @return the edd private pul count
     */
    public long getEddPrivatePulCount() {
        return eddPrivatePulCount;
    }

    /**
     * Sets edd private pul count.
     *
     * @param eddPrivatePulCount the edd private pul count
     */
    public void setEddPrivatePulCount(long eddPrivatePulCount) {
        this.eddPrivatePulCount = eddPrivatePulCount;
    }

    /**
     * Gets edd private cul count.
     *
     * @return the edd private cul count
     */
    public long getEddPrivateCulCount() {
        return eddPrivateCulCount;
    }

    /**
     * Sets edd private cul count.
     *
     * @param eddPrivateCulCount the edd private cul count
     */
    public void setEddPrivateCulCount(long eddPrivateCulCount) {
        this.eddPrivateCulCount = eddPrivateCulCount;
    }

    /**
     * Gets edd private nypl count.
     *
     * @return the edd private nypl count
     */
    public long getEddPrivateNyplCount() {
        return eddPrivateNyplCount;
    }

    /**
     * Sets edd private nypl count.
     *
     * @param eddPrivateNyplCount the edd private nypl count
     */
    public void setEddPrivateNyplCount(long eddPrivateNyplCount) {
        this.eddPrivateNyplCount = eddPrivateNyplCount;
    }

    /**
     * Gets edd shared open pul count.
     *
     * @return the edd shared open pul count
     */
    public long getEddSharedOpenPulCount() {
        return eddSharedOpenPulCount;
    }

    /**
     * Sets edd shared open pul count.
     *
     * @param eddSharedOpenPulCount the edd shared open pul count
     */
    public void setEddSharedOpenPulCount(long eddSharedOpenPulCount) {
        this.eddSharedOpenPulCount = eddSharedOpenPulCount;
    }

    /**
     * Gets edd shared open cul count.
     *
     * @return the edd shared open cul count
     */
    public long getEddSharedOpenCulCount() {
        return eddSharedOpenCulCount;
    }

    /**
     * Sets edd shared open cul count.
     *
     * @param eddSharedOpenCulCount the edd shared open cul count
     */
    public void setEddSharedOpenCulCount(long eddSharedOpenCulCount) {
        this.eddSharedOpenCulCount = eddSharedOpenCulCount;
    }

    /**
     * Gets edd shared open nypl count.
     *
     * @return the edd shared open nypl count
     */
    public long getEddSharedOpenNyplCount() {
        return eddSharedOpenNyplCount;
    }

    /**
     * Sets edd shared open nypl count.
     *
     * @param eddSharedOpenNyplCount the edd shared open nypl count
     */
    public void setEddSharedOpenNyplCount(long eddSharedOpenNyplCount) {
        this.eddSharedOpenNyplCount = eddSharedOpenNyplCount;
    }

    /**
     * Is show accession deaccession table boolean.
     *
     * @return the boolean
     */
    public boolean isShowAccessionDeaccessionTable() {
        return showAccessionDeaccessionTable;
    }

    /**
     * Sets show accession deaccession table.
     *
     * @param showAccessionDeaccessionTable the show accession deaccession table
     */
    public void setShowAccessionDeaccessionTable(boolean showAccessionDeaccessionTable) {
        this.showAccessionDeaccessionTable = showAccessionDeaccessionTable;
    }

    /**
     * Is show report results text boolean.
     *
     * @return the boolean
     */
    public boolean isShowReportResultsText() {
        return showReportResultsText;
    }

    /**
     * Sets show report results text.
     *
     * @param showReportResultsText the show report results text
     */
    public void setShowReportResultsText(boolean showReportResultsText) {
        this.showReportResultsText = showReportResultsText;
    }

    /**
     * Gets accession private pul count.
     *
     * @return the accession private pul count
     */
    public long getAccessionPrivatePulCount() {
        return accessionPrivatePulCount;
    }

    /**
     * Sets accession private pul count.
     *
     * @param accessionPrivatePulCount the accession private pul count
     */
    public void setAccessionPrivatePulCount(long accessionPrivatePulCount) {
        this.accessionPrivatePulCount = accessionPrivatePulCount;
    }

    /**
     * Gets accession private cul count.
     *
     * @return the accession private cul count
     */
    public long getAccessionPrivateCulCount() {
        return accessionPrivateCulCount;
    }

    /**
     * Sets accession private cul count.
     *
     * @param accessionPrivateCulCount the accession private cul count
     */
    public void setAccessionPrivateCulCount(long accessionPrivateCulCount) {
        this.accessionPrivateCulCount = accessionPrivateCulCount;
    }

    /**
     * Gets accession private nypl count.
     *
     * @return the accession private nypl count
     */
    public long getAccessionPrivateNyplCount() {
        return accessionPrivateNyplCount;
    }

    /**
     * Sets accession private nypl count.
     *
     * @param accessionPrivateNyplCount the accession private nypl count
     */
    public void setAccessionPrivateNyplCount(long accessionPrivateNyplCount) {
        this.accessionPrivateNyplCount = accessionPrivateNyplCount;
    }

    /**
     * Gets accession shared pul count.
     *
     * @return the accession shared pul count
     */
    public long getAccessionSharedPulCount() {
        return accessionSharedPulCount;
    }

    /**
     * Sets accession shared pul count.
     *
     * @param accessionSharedPulCount the accession shared pul count
     */
    public void setAccessionSharedPulCount(long accessionSharedPulCount) {
        this.accessionSharedPulCount = accessionSharedPulCount;
    }

    /**
     * Gets accession shared cul count.
     *
     * @return the accession shared cul count
     */
    public long getAccessionSharedCulCount() {
        return accessionSharedCulCount;
    }

    /**
     * Sets accession shared cul count.
     *
     * @param accessionSharedCulCount the accession shared cul count
     */
    public void setAccessionSharedCulCount(long accessionSharedCulCount) {
        this.accessionSharedCulCount = accessionSharedCulCount;
    }

    /**
     * Gets accession shared nypl count.
     *
     * @return the accession shared nypl count
     */
    public long getAccessionSharedNyplCount() {
        return accessionSharedNyplCount;
    }

    /**
     * Sets accession shared nypl count.
     *
     * @param accessionSharedNyplCount the accession shared nypl count
     */
    public void setAccessionSharedNyplCount(long accessionSharedNyplCount) {
        this.accessionSharedNyplCount = accessionSharedNyplCount;
    }

    /**
     * Gets accession open pul count.
     *
     * @return the accession open pul count
     */
    public long getAccessionOpenPulCount() {
        return accessionOpenPulCount;
    }

    /**
     * Sets accession open pul count.
     *
     * @param accessionOpenPulCount the accession open pul count
     */
    public void setAccessionOpenPulCount(long accessionOpenPulCount) {
        this.accessionOpenPulCount = accessionOpenPulCount;
    }

    /**
     * Gets accession open cul count.
     *
     * @return the accession open cul count
     */
    public long getAccessionOpenCulCount() {
        return accessionOpenCulCount;
    }

    /**
     * Sets accession open cul count.
     *
     * @param accessionOpenCulCount the accession open cul count
     */
    public void setAccessionOpenCulCount(long accessionOpenCulCount) {
        this.accessionOpenCulCount = accessionOpenCulCount;
    }

    /**
     * Gets accession open nypl count.
     *
     * @return the accession open nypl count
     */
    public long getAccessionOpenNyplCount() {
        return accessionOpenNyplCount;
    }

    /**
     * Sets accession open nypl count.
     *
     * @param accessionOpenNyplCount the accession open nypl count
     */
    public void setAccessionOpenNyplCount(long accessionOpenNyplCount) {
        this.accessionOpenNyplCount = accessionOpenNyplCount;
    }

    /**
     * Gets deaccession private pul count.
     *
     * @return the deaccession private pul count
     */
    public long getDeaccessionPrivatePulCount() {
        return deaccessionPrivatePulCount;
    }

    /**
     * Sets deaccession private pul count.
     *
     * @param deaccessionPrivatePulCount the deaccession private pul count
     */
    public void setDeaccessionPrivatePulCount(long deaccessionPrivatePulCount) {
        this.deaccessionPrivatePulCount = deaccessionPrivatePulCount;
    }

    /**
     * Gets deaccession private cul count.
     *
     * @return the deaccession private cul count
     */
    public long getDeaccessionPrivateCulCount() {
        return deaccessionPrivateCulCount;
    }

    /**
     * Sets deaccession private cul count.
     *
     * @param deaccessionPrivateCulCount the deaccession private cul count
     */
    public void setDeaccessionPrivateCulCount(long deaccessionPrivateCulCount) {
        this.deaccessionPrivateCulCount = deaccessionPrivateCulCount;
    }

    /**
     * Gets deaccession private nypl count.
     *
     * @return the deaccession private nypl count
     */
    public long getDeaccessionPrivateNyplCount() {
        return deaccessionPrivateNyplCount;
    }

    /**
     * Sets deaccession private nypl count.
     *
     * @param deaccessionPrivateNyplCount the deaccession private nypl count
     */
    public void setDeaccessionPrivateNyplCount(long deaccessionPrivateNyplCount) {
        this.deaccessionPrivateNyplCount = deaccessionPrivateNyplCount;
    }

    /**
     * Gets deaccession shared pul count.
     *
     * @return the deaccession shared pul count
     */
    public long getDeaccessionSharedPulCount() {
        return deaccessionSharedPulCount;
    }

    /**
     * Sets deaccession shared pul count.
     *
     * @param deaccessionSharedPulCount the deaccession shared pul count
     */
    public void setDeaccessionSharedPulCount(long deaccessionSharedPulCount) {
        this.deaccessionSharedPulCount = deaccessionSharedPulCount;
    }

    /**
     * Gets deaccession shared cul count.
     *
     * @return the deaccession shared cul count
     */
    public long getDeaccessionSharedCulCount() {
        return deaccessionSharedCulCount;
    }

    /**
     * Sets deaccession shared cul count.
     *
     * @param deaccessionSharedCulCount the deaccession shared cul count
     */
    public void setDeaccessionSharedCulCount(long deaccessionSharedCulCount) {
        this.deaccessionSharedCulCount = deaccessionSharedCulCount;
    }

    /**
     * Gets deaccession shared nypl count.
     *
     * @return the deaccession shared nypl count
     */
    public long getDeaccessionSharedNyplCount() {
        return deaccessionSharedNyplCount;
    }

    /**
     * Sets deaccession shared nypl count.
     *
     * @param deaccessionSharedNyplCount the deaccession shared nypl count
     */
    public void setDeaccessionSharedNyplCount(long deaccessionSharedNyplCount) {
        this.deaccessionSharedNyplCount = deaccessionSharedNyplCount;
    }

    /**
     * Gets deaccession open pul count.
     *
     * @return the deaccession open pul count
     */
    public long getDeaccessionOpenPulCount() {
        return deaccessionOpenPulCount;
    }

    /**
     * Sets deaccession open pul count.
     *
     * @param deaccessionOpenPulCount the deaccession open pul count
     */
    public void setDeaccessionOpenPulCount(long deaccessionOpenPulCount) {
        this.deaccessionOpenPulCount = deaccessionOpenPulCount;
    }

    /**
     * Gets deaccession open cul count.
     *
     * @return the deaccession open cul count
     */
    public long getDeaccessionOpenCulCount() {
        return deaccessionOpenCulCount;
    }

    /**
     * Sets deaccession open cul count.
     *
     * @param deaccessionOpenCulCount the deaccession open cul count
     */
    public void setDeaccessionOpenCulCount(long deaccessionOpenCulCount) {
        this.deaccessionOpenCulCount = deaccessionOpenCulCount;
    }

    /**
     * Gets deaccession open nypl count.
     *
     * @return the deaccession open nypl count
     */
    public long getDeaccessionOpenNyplCount() {
        return deaccessionOpenNyplCount;
    }

    /**
     * Sets deaccession open nypl count.
     *
     * @param deaccessionOpenNyplCount the deaccession open nypl count
     */
    public void setDeaccessionOpenNyplCount(long deaccessionOpenNyplCount) {
        this.deaccessionOpenNyplCount = deaccessionOpenNyplCount;
    }

    /**
     * Gets open pul cgd count.
     *
     * @return the open pul cgd count
     */
    public long getOpenPulCgdCount() {
        return openPulCgdCount;
    }

    /**
     * Sets open pul cgd count.
     *
     * @param openPulCgdCount the open pul cgd count
     */
    public void setOpenPulCgdCount(long openPulCgdCount) {
        this.openPulCgdCount = openPulCgdCount;
    }

    /**
     * Gets open cul cgd count.
     *
     * @return the open cul cgd count
     */
    public long getOpenCulCgdCount() {
        return openCulCgdCount;
    }

    /**
     * Sets open cul cgd count.
     *
     * @param openCulCgdCount the open cul cgd count
     */
    public void setOpenCulCgdCount(long openCulCgdCount) {
        this.openCulCgdCount = openCulCgdCount;
    }

    /**
     * Gets open nypl cgd count.
     *
     * @return the open nypl cgd count
     */
    public long getOpenNyplCgdCount() {
        return openNyplCgdCount;
    }

    /**
     * Sets open nypl cgd count.
     *
     * @param openNyplCgdCount the open nypl cgd count
     */
    public void setOpenNyplCgdCount(long openNyplCgdCount) {
        this.openNyplCgdCount = openNyplCgdCount;
    }

    /**
     * Gets shared pul cgd count.
     *
     * @return the shared pul cgd count
     */
    public long getSharedPulCgdCount() {
        return sharedPulCgdCount;
    }

    /**
     * Sets shared pul cgd count.
     *
     * @param sharedPulCgdCount the shared pul cgd count
     */
    public void setSharedPulCgdCount(long sharedPulCgdCount) {
        this.sharedPulCgdCount = sharedPulCgdCount;
    }

    /**
     * Gets shared cul cgd count.
     *
     * @return the shared cul cgd count
     */
    public long getSharedCulCgdCount() {
        return sharedCulCgdCount;
    }

    /**
     * Sets shared cul cgd count.
     *
     * @param sharedCulCgdCount the shared cul cgd count
     */
    public void setSharedCulCgdCount(long sharedCulCgdCount) {
        this.sharedCulCgdCount = sharedCulCgdCount;
    }

    /**
     * Gets shared nypl cgd count.
     *
     * @return the shared nypl cgd count
     */
    public long getSharedNyplCgdCount() {
        return sharedNyplCgdCount;
    }

    /**
     * Sets shared nypl cgd count.
     *
     * @param sharedNyplCgdCount the shared nypl cgd count
     */
    public void setSharedNyplCgdCount(long sharedNyplCgdCount) {
        this.sharedNyplCgdCount = sharedNyplCgdCount;
    }

    /**
     * Gets private pul cgd count.
     *
     * @return the private pul cgd count
     */
    public long getPrivatePulCgdCount() {
        return privatePulCgdCount;
    }

    /**
     * Sets private pul cgd count.
     *
     * @param privatePulCgdCount the private pul cgd count
     */
    public void setPrivatePulCgdCount(long privatePulCgdCount) {
        this.privatePulCgdCount = privatePulCgdCount;
    }

    /**
     * Gets private cul cgd count.
     *
     * @return the private cul cgd count
     */
    public long getPrivateCulCgdCount() {
        return privateCulCgdCount;
    }

    /**
     * Sets private cul cgd count.
     *
     * @param privateCulCgdCount the private cul cgd count
     */
    public void setPrivateCulCgdCount(long privateCulCgdCount) {
        this.privateCulCgdCount = privateCulCgdCount;
    }

    /**
     * Gets private nypl cgd count.
     *
     * @return the private nypl cgd count
     */
    public long getPrivateNyplCgdCount() {
        return privateNyplCgdCount;
    }

    /**
     * Sets private nypl cgd count.
     *
     * @param privateNyplCgdCount the private nypl cgd count
     */
    public void setPrivateNyplCgdCount(long privateNyplCgdCount) {
        this.privateNyplCgdCount = privateNyplCgdCount;
    }

    /**
     * Is show request type table boolean.
     *
     * @return the boolean
     */
    public boolean isShowRequestTypeTable() {
        return showRequestTypeTable;
    }

    /**
     * Sets show request type table.
     *
     * @param showRequestTypeTable the show request type table
     */
    public void setShowRequestTypeTable(boolean showRequestTypeTable) {
        this.showRequestTypeTable = showRequestTypeTable;
    }

    /**
     * Is show note ilbd boolean.
     *
     * @return the boolean
     */
    public boolean isShowNoteILBD() {
        return showNoteILBD;
    }

    /**
     * Sets show note ilbd.
     *
     * @param showNoteILBD the show note ilbd
     */
    public void setShowNoteILBD(boolean showNoteILBD) {
        this.showNoteILBD = showNoteILBD;
    }

    /**
     * Is show note partners boolean.
     *
     * @return the boolean
     */
    public boolean isShowNotePartners() {
        return showNotePartners;
    }

    /**
     * Sets show note partners.
     *
     * @param showNotePartners the show note partners
     */
    public void setShowNotePartners(boolean showNotePartners) {
        this.showNotePartners = showNotePartners;
    }

    /**
     * Is show note request type boolean.
     *
     * @return the boolean
     */
    public boolean isShowNoteRequestType() {
        return showNoteRequestType;
    }

    /**
     * Sets show note request type.
     *
     * @param showNoteRequestType the show note request type
     */
    public void setShowNoteRequestType(boolean showNoteRequestType) {
        this.showNoteRequestType = showNoteRequestType;
    }

    /**
     * Gets report request type.
     *
     * @return the report request type
     */
    public List<String> getReportRequestType() {
        return reportRequestType;
    }

    /**
     * Sets report request type.
     *
     * @param reportRequestType the report request type
     */
    public void setReportRequestType(List<String> reportRequestType) {
        this.reportRequestType = reportRequestType;
    }

    /**
     * Is show retrieval table boolean.
     *
     * @return the boolean
     */
    public boolean isShowRetrievalTable() {
        return showRetrievalTable;
    }

    /**
     * Sets show retrieval table.
     *
     * @param showRetrievalTable the show retrieval table
     */
    public void setShowRetrievalTable(boolean showRetrievalTable) {
        this.showRetrievalTable = showRetrievalTable;
    }

    /**
     * Is show recall table boolean.
     *
     * @return the boolean
     */
    public boolean isShowRecallTable() {
        return showRecallTable;
    }

    /**
     * Sets show recall table.
     *
     * @param showRecallTable the show recall table
     */
    public void setShowRecallTable(boolean showRecallTable) {
        this.showRecallTable = showRecallTable;
    }

    /**
     * Gets owning institutions.
     *
     * @return the owning institutions
     */
    public List<String> getOwningInstitutions() {
        if (null == owningInstitutions) {
            owningInstitutions = new ArrayList<>();
        }
        return owningInstitutions;
    }

    /**
     * Sets owning institutions.
     *
     * @param owningInstitutions the owning institutions
     */
    public void setOwningInstitutions(List<String> owningInstitutions) {
        this.owningInstitutions = owningInstitutions;
    }

    /**
     * Gets collection group designations.
     *
     * @return the collection group designations
     */
    public List<String> getCollectionGroupDesignations() {
        if (null == collectionGroupDesignations) {
            collectionGroupDesignations = new ArrayList<>();
        }
        return collectionGroupDesignations;
    }

    /**
     * Sets collection group designations.
     *
     * @param collectionGroupDesignations the collection group designations
     */
    public void setCollectionGroupDesignations(List<String> collectionGroupDesignations) {
        this.collectionGroupDesignations = collectionGroupDesignations;
    }

    /**
     * Is show request type show boolean.
     *
     * @return the boolean
     */
    public boolean isShowRequestTypeShow() {
        return showRequestTypeShow;
    }

    /**
     * Sets show request type show.
     *
     * @param showRequestTypeShow the show request type show
     */
    public void setShowRequestTypeShow(boolean showRequestTypeShow) {
        this.showRequestTypeShow = showRequestTypeShow;
    }

    /**
     * Gets deaccession item results rows.
     *
     * @return the deaccession item results rows
     */
    public List<DeaccessionItemResultsRow> getDeaccessionItemResultsRows() {
        return deaccessionItemResultsRows;
    }

    /**
     * Sets deaccession item results rows.
     *
     * @param deaccessionItemResultsRows the deaccession item results rows
     */
    public void setDeaccessionItemResultsRows(List<DeaccessionItemResultsRow> deaccessionItemResultsRows) {
        this.deaccessionItemResultsRows = deaccessionItemResultsRows;
    }

    /**
     * Is show deaccession information table boolean.
     *
     * @return the boolean
     */
    public boolean isShowDeaccessionInformationTable() {
        return showDeaccessionInformationTable;
    }

    /**
     * Sets show deaccession information table.
     *
     * @param showDeaccessionInformationTable the show deaccession information table
     */
    public void setShowDeaccessionInformationTable(boolean showDeaccessionInformationTable) {
        this.showDeaccessionInformationTable = showDeaccessionInformationTable;
    }

    /**
     * Gets total records count.
     *
     * @return the total records count
     */
    public String getTotalRecordsCount() {
        return totalRecordsCount;
    }

    /**
     * Sets total records count.
     *
     * @param totalRecordsCount the total records count
     */
    public void setTotalRecordsCount(String totalRecordsCount) {
        this.totalRecordsCount = totalRecordsCount;
    }

    /**
     * Gets page number.
     *
     * @return the page number
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets page number.
     *
     * @param pageNumber the page number
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Gets page size.
     *
     * @return the page size
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Sets page size.
     *
     * @param pageSize the page size
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Gets total page count.
     *
     * @return the total page count
     */
    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    /**
     * Sets total page count.
     *
     * @param totalPageCount the total page count
     */
    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    /**
     * Gets deaccession own inst.
     *
     * @return the deaccession own inst
     */
    public String getDeaccessionOwnInst() {
        return deaccessionOwnInst;
    }

    /**
     * Sets deaccession own inst.
     *
     * @param deaccessionOwnInst the deaccession own inst
     */
    public void setDeaccessionOwnInst(String deaccessionOwnInst) {
        this.deaccessionOwnInst = deaccessionOwnInst;
    }

    /**
     * Gets incomplete requesting institution.
     *
     * @return the incomplete requesting institution
     */
    public String getIncompleteRequestingInstitution() {
        return incompleteRequestingInstitution;
    }

    /**
     * Sets incomplete requesting institution.
     *
     * @param incompleteRequestingInstitution the incomplete requesting institution
     */
    public void setIncompleteRequestingInstitution(String incompleteRequestingInstitution) {
        this.incompleteRequestingInstitution = incompleteRequestingInstitution;
    }

    /**
     * Gets incomplete page number.
     *
     * @return the incomplete page number
     */
    public Integer getIncompletePageNumber() {
        return incompletePageNumber;
    }

    /**
     * Sets incomplete page number.
     *
     * @param incompletePageNumber the incomplete page number
     */
    public void setIncompletePageNumber(Integer incompletePageNumber) {
        this.incompletePageNumber = incompletePageNumber;
    }

    /**
     * Gets incomplete page size.
     *
     * @return the incomplete page size
     */
    public Integer getIncompletePageSize() {
        return incompletePageSize;
    }

    /**
     * Sets incomplete page size.
     *
     * @param incompletePageSize the incomplete page size
     */
    public void setIncompletePageSize(Integer incompletePageSize) {
        this.incompletePageSize = incompletePageSize;
    }

    /**
     * Gets incomplete total records count.
     *
     * @return the incomplete total records count
     */
    public String getIncompleteTotalRecordsCount() {
        return incompleteTotalRecordsCount;
    }

    /**
     * Sets incomplete total records count.
     *
     * @param incompleteTotalRecordsCount the incomplete total records count
     */
    public void setIncompleteTotalRecordsCount(String incompleteTotalRecordsCount) {
        this.incompleteTotalRecordsCount = incompleteTotalRecordsCount;
    }

    /**
     * Gets incomplete total page count.
     *
     * @return the incomplete total page count
     */
    public Integer getIncompleteTotalPageCount() {
        return incompleteTotalPageCount;
    }

    /**
     * Sets incomplete total page count.
     *
     * @param incompleteTotalPageCount the incomplete total page count
     */
    public void setIncompleteTotalPageCount(Integer incompleteTotalPageCount) {
        this.incompleteTotalPageCount = incompleteTotalPageCount;
    }

    /**
     * Gets incomplete report results rows.
     *
     * @return the incomplete report results rows
     */
    public List<IncompleteReportResultsRow> getIncompleteReportResultsRows() {
        return incompleteReportResultsRows;
    }

    /**
     * Sets incomplete report results rows.
     *
     * @param incompleteReportResultsRows the incomplete report results rows
     */
    public void setIncompleteReportResultsRows(List<IncompleteReportResultsRow> incompleteReportResultsRows) {
        this.incompleteReportResultsRows = incompleteReportResultsRows;
    }

    /**
     * Gets incomplete show by inst.
     *
     * @return the incomplete show by inst
     */
    public List<String> getIncompleteShowByInst() {
        return incompleteShowByInst;
    }

    /**
     * Sets incomplete show by inst.
     *
     * @param incompleteShowByInst the incomplete show by inst
     */
    public void setIncompleteShowByInst(List<String> incompleteShowByInst) {
        this.incompleteShowByInst = incompleteShowByInst;
    }

    /**
     * Is show incomplete results boolean.
     *
     * @return the boolean
     */
    public boolean isShowIncompleteResults() {
        return showIncompleteResults;
    }

    /**
     * Sets show incomplete results.
     *
     * @param showIncompleteResults the show incomplete results
     */
    public void setShowIncompleteResults(boolean showIncompleteResults) {
        this.showIncompleteResults = showIncompleteResults;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Is show incomplete pagination boolean.
     *
     * @return the boolean
     */
    public boolean isShowIncompletePagination() {
        return showIncompletePagination;
    }

    /**
     * Sets show incomplete pagination.
     *
     * @param showIncompletePagination the show incomplete pagination
     */
    public void setShowIncompletePagination(boolean showIncompletePagination) {
        this.showIncompletePagination = showIncompletePagination;
    }

    /**
     * Is export boolean.
     *
     * @return the boolean
     */
    public boolean isExport() {
        return export;
    }

    /**
     * Sets export.
     *
     * @param export the export
     */
    public void setExport(boolean export) {
        this.export = export;
    }

    /**
     * Gets physical partner shared pul count.
     *
     * @return the physical partner shared pul count
     */
    public long getPhysicalPartnerSharedPulCount() {
        return physicalPartnerSharedPulCount;
    }

    /**
     * Sets physical partner shared pul count.
     *
     * @param physicalPartnerSharedPulCount the physical partner shared pul count
     */
    public void setPhysicalPartnerSharedPulCount(long physicalPartnerSharedPulCount) {
        this.physicalPartnerSharedPulCount = physicalPartnerSharedPulCount;
    }

    /**
     * Gets physical partner shared cul count.
     *
     * @return the physical partner shared cul count
     */
    public long getPhysicalPartnerSharedCulCount() {
        return physicalPartnerSharedCulCount;
    }

    /**
     * Sets physical partner shared cul count.
     *
     * @param physicalPartnerSharedCulCount the physical partner shared cul count
     */
    public void setPhysicalPartnerSharedCulCount(long physicalPartnerSharedCulCount) {
        this.physicalPartnerSharedCulCount = physicalPartnerSharedCulCount;
    }

    /**
     * Gets physical partner shared nypl count.
     *
     * @return the physical partner shared nypl count
     */
    public long getPhysicalPartnerSharedNyplCount() {
        return physicalPartnerSharedNyplCount;
    }

    /**
     * Sets physical partner shared nypl count.
     *
     * @param physicalPartnerSharedNyplCount the physical partner shared nypl count
     */
    public void setPhysicalPartnerSharedNyplCount(long physicalPartnerSharedNyplCount) {
        this.physicalPartnerSharedNyplCount = physicalPartnerSharedNyplCount;
    }

    /**
     * Gets edd partner shared open pul count.
     *
     * @return the edd partner shared open pul count
     */
    public long getEddPartnerSharedOpenPulCount() {
        return eddPartnerSharedOpenPulCount;
    }

    /**
     * Sets edd partner shared open pul count.
     *
     * @param eddPartnerSharedOpenPulCount the edd partner shared open pul count
     */
    public void setEddPartnerSharedOpenPulCount(long eddPartnerSharedOpenPulCount) {
        this.eddPartnerSharedOpenPulCount = eddPartnerSharedOpenPulCount;
    }

    /**
     * Gets edd partner shared open cul count.
     *
     * @return the edd partner shared open cul count
     */
    public long getEddPartnerSharedOpenCulCount() {
        return eddPartnerSharedOpenCulCount;
    }

    /**
     * Sets edd partner shared open cul count.
     *
     * @param eddPartnerSharedOpenCulCount the edd partner shared open cul count
     */
    public void setEddPartnerSharedOpenCulCount(long eddPartnerSharedOpenCulCount) {
        this.eddPartnerSharedOpenCulCount = eddPartnerSharedOpenCulCount;
    }

    /**
     * Gets edd partner shared open nypl count.
     *
     * @return the edd partner shared open nypl count
     */
    public long getEddPartnerSharedOpenNyplCount() {
        return eddPartnerSharedOpenNyplCount;
    }

    /**
     * Sets edd partner shared open nypl count.
     *
     * @param eddPartnerSharedOpenNyplCount the edd partner shared open nypl count
     */
    public void setEddPartnerSharedOpenNyplCount(long eddPartnerSharedOpenNyplCount) {
        this.eddPartnerSharedOpenNyplCount = eddPartnerSharedOpenNyplCount;
    }

    /**
     * Gets edd request pul count.
     *
     * @return the edd request pul count
     */
    public long getEddRequestPulCount() {
        return eddRequestPulCount;
    }

    /**
     * Sets edd request pul count.
     *
     * @param eddRequestPulCount the edd request pul count
     */
    public void setEddRequestPulCount(long eddRequestPulCount) {
        this.eddRequestPulCount = eddRequestPulCount;
    }

    /**
     * Gets edd request cul count.
     *
     * @return the edd request cul count
     */
    public long getEddRequestCulCount() {
        return eddRequestCulCount;
    }

    /**
     * Sets edd request cul count.
     *
     * @param eddRequestCulCount the edd request cul count
     */
    public void setEddRequestCulCount(long eddRequestCulCount) {
        this.eddRequestCulCount = eddRequestCulCount;
    }

    /**
     * Gets edd request nypl count.
     *
     * @return the edd request nypl count
     */
    public long getEddRequestNyplCount() {
        return eddRequestNyplCount;
    }

    /**
     * Sets edd request nypl count.
     *
     * @param eddRequestNyplCount the edd request nypl count
     */
    public void setEddRequestNyplCount(long eddRequestNyplCount) {
        this.eddRequestNyplCount = eddRequestNyplCount;
    }
}
