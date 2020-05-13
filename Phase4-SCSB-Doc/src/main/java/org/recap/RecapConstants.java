package org.recap;

/**
 * Created by SheikS on 6/20/2016.
 */
public final class RecapConstants {
    public static final String PATH_SEPARATOR = "/";
    public static final String PROCESSSED_RECORDS = "processedRecords";

    public static final String ALL = "*";
    public static final String DOCTYPE = "DocType";
    public static final String BIB = "Bib";
    public static final String ITEM = "Item";
    public static final String HOLDINGS = "Holdings";
    public static final String HOLDINGS_ID = "HoldingsId";
    public static final String HOLDING_ID = "HoldingId";
    public static final String ITEM_ID = "ItemId";
    public static final String SEARCH = "search";
    public static final String PRIVATE = "Private";
    public static final String BIB_OWNING_INSTITUTION = "BibOwningInstitution";
    public static final String HOLDINGS_OWNING_INSTITUTION = "HoldingsOwningInstitution";
    public static final String ITEM_OWNING_INSTITUTION = "ItemOwningInstitution";
    public static final String OWNING_INSTITUTION = "OwningInstitution";
    public static final String COLLECTION_GROUP_DESIGNATION = "CollectionGroupDesignation";
    public static final String AVAILABILITY = "Availability_search";
    public static final String TITLE_SEARCH = "Title_search";
    public static final String AUTHOR_SEARCH = "Author_search";
    public static final String PUBLISHER = "Publisher";
    public static final String TITLE_STARTS_WITH= "TitleStartsWith";
    public static final String TITLE_SORT= "Title_sort";
    public static final String BARCODE = "Barcode";
    public static final String CALL_NUMBER = "CallNumber_search";
    public static final String NOTES = "Notes";
    public static final String LEADER_MATERIAL_TYPE = "LeaderMaterialType";
    public static final String MONOGRAPH = "Monograph";
    public static final String MONOGRAPHIC_SET = "MonographicSet";
    public static final String SERIAL = "Serial";
    public static final String OTHER = "Other";
    public static final String AND = "AND";
    public static final String OR = "OR";
    public static final String FALSE = "false";
    public static final String TRUE = "true";
    public static final String ALL_DIACRITICS = "all_diacritics";
    public static final String ALL_FIELDS = "_text_";
    public static final String IS_DELETED_BIB = "IsDeletedBib";
    public static final String IS_DELETED_HOLDINGS = "IsDeletedHoldings";
    public static final String IS_DELETED_ITEM = "IsDeletedItem";
    public static final String PUBLICATION_DATE = "PublicationDate";
    public static final String CGD_CHANGE_LOG = "CGDChangeLog";
    public static final String CGD_CHANGE_LOG_SHARED_TO_PRIVATE = "SharedToPrivate";
    public static final String CGD_CHANGE_LOG_OPEN_TO_PRIVATE = "OpenToPrivate";
    public static final String TITLE_SUBFIELD_A = "Title_subfield_a";
    public static final String ROOT = "_root_";

    public static final String USE_RESTRICTION = "UseRestriction_search";
    public static final String USE_RESTRICTION_DISPLAY = "UseRestriction_display";
    public static final String NO_RESTRICTIONS = "No Restrictions";
    public static final String IN_LIBRARY_USE = "In Library Use";
    public static final String SUPERVISED_USE = "Supervised Use";

    public static final String COLLECTION_GROUP_CODE = "CollectionGroupCode";
    public static final String STATUS = "Status";
    public static final String REASON_FOR_FAILURE = "ReasonForFailure";
    public static final String REASON_FOR_FAILURE_BIB = "ReasonForFailureBib";
    public static final String REASON_FOR_FAILURE_HOLDING = "ReasonForFailureHolding";
    public static final String REASON_FOR_FAILURE_ITEM = "ReasonForFailureItem";

    public static final String INCREMENTAL_DATE_FORMAT = "dd-MM-yyyy hh:mm";

    //Camel Queues Constants
    public static final String CSV_SOLR_EXCEPTION_REPORT_Q = "scsbactivemq:queue:csvSolrExceptionReportQ";
    public static final String FTP_SOLR_EXCEPTION_REPORT_Q = "scsbactivemq:queue:ftpSolrExceptionReportQ";
    public static final String REPORT_Q= "scsbactivemq:queue:reportQ";
    public static final String SOLR_QUEUE = "scsbactivemq:queue:solrQ";
    public static final String FS_SUBMIT_COLLECTION_REJECTION_REPORT_Q = "scsbactivemq:queue:fsSubmitCollectionRejectionReportQ";
    public static final String FTP_SUBMIT_COLLECTION_REJECTION_REPORT_Q = "scsbactivemq:queue:ftpSubmitCollectionRejectionReportQ";
    public static final String FS_SUBMIT_COLLECTION_EXCEPTION_REPORT_Q = "scsbactivemq:queue:fsSubmitCollectionExceptionReportQ";
    public static final String FTP_SUBMIT_COLLECTION_EXCEPTION_REPORT_Q = "scsbactivemq:queue:ftpSubmitCollectionExceptionReportQ";
    public static final String FTP_SUBMIT_COLLECTION_SUMMARY_REPORT_Q = "scsbactivemq:queue:ftpSubmitCollectionSummaryReportQ";
    public static final String FS_SUBMIT_COLLECTION_SUMMARY_REPORT_Q = "scsbactivemq:queue:fsSubmitCollectionSummaryReportQ";
    public static final String FTP_SUBMIT_COLLECTION_SUCCESS_REPORT_Q = "scsbactivemq:queue:ftpSubmitCollectionSuccessReportQ";
    public static final String FS_SUBMIT_COLLECTION_SUCCESS_REPORT_Q = "scsbactivemq:queue:fsSubmitCollectionSuccessReportQ";
    public static final String FTP_SUBMIT_COLLECTION_FAILURE_REPORT_Q = "scsbactivemq:queue:ftpSubmitCollectionFailureReportQ";
    public static final String FS_SUBMIT_COLLECTION_FAILURE_REPORT_Q = "scsbactivemq:queue:fsSubmitCollectionFailureReportQ";
    public static final String FTP_ONGOING_ACCESSON_REPORT_Q = "scsbactivemq:queue:ftpOngoingAccessionReportQ";
    public static final String FS_ONGOING_ACCESSION_REPORT_Q = "scsbactivemq:queue:fsOngoingAccessionReportQ";
    public static final String FTP_SUBMIT_COLLECTION_REPORT_Q = "scsbactivemq:queue:ftpSubmitCollectionReportQ";
    public static final String FS_DE_ACCESSION_SUMMARY_REPORT_Q = "scsbactivemq:queue:fsDeAccessionSummaryReportQ";
    public static final String FTP_DE_ACCESSION_SUMMARY_REPORT_Q = "scsbactivemq:queue:ftpDeAccessionSummaryReportQ";
    public static final String FS_ACCESSION_SUMMARY_REPORT_Q = "scsbactivemq:queue:fsAccessionSummaryReportQ";
    public static final String FTP_ACCESSION_SUMMARY_REPORT_Q = "scsbactivemq:queue:ftpAccessionSummaryReportQ";
    public static final String EMAIL_Q = "scsbactivemq:queue:solrClientEmailQ";
    public static final String FTP_SERIAL_MVM_REPORT_Q = "scsbactivemq:queue:ftpSerialMvmReportsQ";
    public static final String FTP_MATCHING_SUMMARY_REPORT_Q = "scsbactivemq:queue:ftpMatchingSummaryReportQ";

    //Camel Route Id Constants
    public static final String CSV_SOLR_EXCEPTION_REPORT_ROUTE_ID = "csvSolrExceptionReportRoute";
    public static final String FTP_SOLR_EXCEPTION_REPORT_ROUTE_ID = "ftpSolrExceptionReportRoute";
    public static final String REPORT_ROUTE_ID = "reportQRoute";
    public static final String FS_SUBMIT_COLLECTION_REJECTION_REPORT_ID = "fsSubmitCollectionRejectionReport";
    public static final String FTP_SUBMIT_COLLECTION_REJECTION_REPORT_ID = "ftpSubmitCollectionRejectionReport";
    public static final String FS_SUBMIT_COLLECTION_EXCEPTION_REPORT_ID = "fsSubmitCollectionExceptionReport";
    public static final String FTP_SUBMIT_COLLECTION_EXCEPTION_REPORT_ID = "ftpSubmitCollectionExceptionReport";
    public static final String FTP_SUBMIT_COLLECTION_SUMMARY_REPORT_ID = "ftpSubmitCollectionSummaryReport";
    public static final String FS_SUBMIT_COLLECTION_SUMMARY_REPORT_ID = "fsSubmitCollectionSummaryReport";
    public static final String FTP_SUBMIT_COLLECTION_SUCCESS_REPORT_ID = "ftpSubmitCollectionSuccessReport";
    public static final String FS_SUBMIT_COLLECTION_SUCCESS_REPORT_ID = "fsSubmitCollectionSuccessReport";
    public static final String FTP_SUBMIT_COLLECTION_FAILURE_REPORT_ID = "ftpSubmitCollectionFailureReport";
    public static final String FS_SUBMIT_COLLECTION_FAILURE_REPORT_ID = "fsSubmitCollectionFailureReport";
    public static final String FTP_SUBMIT_COLLECTION_REPORT_ID = "ftpSubmitCollectionReportRoute";
    public static final String EMAIL_ROUTE_ID = "solrClientEmailQ";
    public static final String FTP_TITLE_EXCEPTION_REPORT_ROUTE_ID = "ftpTitleExceptionReportsRoute";
    public static final String FTP_SERIAL_MVM_REPORT_ROUTE_ID = "ftpSerialMvmReportsRoute";
    public static final String FTP_MATCHING_SUMMARY_REPORT_ROUTE_ID = "ftpMatchingSummaryReportRoute";

    public static final String MATCHING_ALGO_FULL_FILE_NAME = "Matching_Algo_Phase1";
    public static final String SUMMARY_REPORT_FILE_NAME = "MatchingCGDSummaryReport";
    public static final String MATCHING_SUMMARY_MONOGRAPH = "MatchingMonographCGDSummary";
    public static final String MATCHING_SUMMARY_SERIAL = "MatchingSerialCGDSummary";
    public static final String MATCHING_SUMMARY_MVM = "MatchingMVMCGDSummary";
    public static final String TITLE_EXCEPTION_REPORT = "TitleExceptionReport";
    public static final String MATCHING_SUMMARY_REPORT = "MatchingSummaryReport";
    public static final String MATCHING_SERIAL_MVM_REPORT = "MatchingSerialMvmReport";
    public static final String UNDER_SCORE = "_";
    public static final String CSV_EXTENSION = ".csv";
    public static final String MATCHING_BIB_IDS = "matchingBibIds";
    public static final String MATCHING_REPORTS_SEND_EMAIL = "sendEmailForMatchingReports";
    public static final String ACCESSION_REPORTS_SEND_EMAIL = "sendEmailForAccessionReports";

    public static final String REPORT_FILE_NAME = "fileName";
    public static final String DATE_FORMAT_FOR_FILE_NAME = "yyyyMMdd_HHmmss";
    public static final String DATE_FORMAT_FOR_REPORT_FILE_NAME = "ddMMMyyyyHHmmss";

    public static final String VOLUME_PART_YEAR = "VolumePartYear";
    public static final String INSTITUTION_ID = "InstitutionId";
    public static final String OCLC = "Oclc";
    public static final String ISBN = "Isbn";
    public static final String ISSN = "Issn";
    public static final String LCCN = "Lccn";
    public static final String USE_RESTRICTIONS = "UseRestrictions";
    public static final String SUMMARY_HOLDINGS = "SummaryHoldings";
    public static final String MATERIAL_TYPE = "MaterialType";
    public static final String INITIAL_MATCHING_OPERATION_TYPE = "InitialMatchingAlgorithm";
    public static final String ONGOING_MATCHING_OPERATION_TYPE = "OngoingMatchingAlgorithm";

    public static final String MATCH_POINT_FIELD_OCLC = "OCLCNumber";
    public static final String MATCH_POINT_FIELD_ISBN = "ISBN";
    public static final String MATCH_POINT_FIELD_ISSN = "ISSN";
    public static final String MATCH_POINT_FIELD_LCCN = "LCCN";
    public static final String MATCHING_PENDING_BIBS = "PendingBibMatches";
    public static final String ALL_INST = "ALL";

    public static final String OCLC_TAG = "035";
    public static final String ISBN_TAG = "020";
    public static final String ISSN_TAG = "022";
    public static final String LCCN_TAG = "010";

    //Report Types For Matching Algorithm
    public static final String SINGLE_MATCH = "SingleMatch";
    public static final String MULTI_MATCH = "MultiMatch";

    //Transmission Types
    public static final String FILE_SYSTEM = "FileSystem";
    public static final String FTP = "FTP";

    public static final String NOT_AVAILABLE_CGD = "NA";
    public static final String SHARED_CGD = "Shared";
    public static final String OCLC_CRITERIA = "OCLC";

    public static final String ISBN_CRITERIA = "ISBN";
    public static final String ISSN_CRITERIA = "ISSN";
    public static final String LCCN_CRITERIA = "LCCN";

    public static final String OCLC_NUMBER = "OCLCNumber";

    public static final String BIB_COUNT = "bibCount";
    public static final String ITEM_COUNT = "itemCount";
    public static final String BIB_ITEM_COUNT = "bibItemCount";

    public static final String EXCEPTION_MSG = "ExceptionMessage";

    public static final String ONGOING_MATCHING_ALGORITHM = "OngoingMatchingAlgorithm";
    public static final String MATERIAL_TYPE_EXCEPTION = "MaterialTypeException";
    public static final String TITLE_EXCEPTION_TYPE = "TitleException";
    public static final String CRITERIA_VALUES = "CriteriaValues";
    public static final String MATCH_POINT = "MatchPoint";

    //Error Message
    public static final String RECORD_NOT_AVAILABLE = "Database may be empty or bib table does not contain this record";
    public static final String SERVER_ERROR_MSG = "Server is down for maintenance. Please try again later.";
    public static final String EMPTY_FACET_ERROR_MSG = "Check facets. At least one Bib Facet and one Item Facet must be checked to get results.";
    public static final String ACCESS_RESTRICTED="User is not permitted to access this record";
    public static final String INVALID_BARCODE_LENGTH="Barcode length should not exceed 45 characters";

    //Search Response Types
    public static final String SEARCH_SUCCESS_RESPONSE = "SuccessResponse";
    public static final String SEARCH_ERROR_RESPONSE = "ErrorResponse";


    public static final String SOLR_CORE = "solrCore";

    public static final String SOLR_INDEX_FAILURE_REPORT = "Solr_Index_Failure_Report";
    public static final String SOLR_INDEX_EXCEPTION = "SolrIndexException";
    public static final String OWNING_INST_BIB_ID = "OwningInstitutionBibId";
    public static final String BIB_ID = "BibId";
    public static final String NA = "NA";

    //Collection
    public static final String UPDATE_CGD = "Update CGD";
    public static final String DEACCESSION = "Deaccession";
    public static final String TEMPLATE = "template";
    public static final String COLLECTION = "collection";
    public static final String REQUEST = "request";
    public static final String GUEST = "guest";
    public static final String ITEM_BARCODES = "itemBarcodes";
    public static final String API_KEY = "api_key";
    public static final String RECAP = "recap";
    public static final String AVAILABLE = "Available";
    public static final String NOT_AVAILABLE = "Not Available";
    public static final String PERMANENT_WITHDRAWAL_DIRECT = "Permanent Withdrawal Direct (PWD)";
    public static final String PERMANENT_WITHDRAWAL_INDIRECT = "Permanent Withdrawal Indirect (PWI)";

    public static final String DEACCESSION_URL = "sharedCollection/deAccession";

    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Failure";
    public static final String NO_RESULTS_FOUND = "No results found.";
    public static final String BARCODES_NOT_FOUND = "Barcode(s) not found";
    public static final String BARCODE_LIMIT_ERROR = "A maximum of only 10 items can be retrieved. Ignored barcode(s)";
    public static final String CGD_UPDATE_SUCCESSFUL = "The CGD has been successfully updated.";
    public static final String CGD_UPDATE_FAILED = "Updating CGD failed";
    public static final String DEACCESSION_SUCCESSFUL = "The item has been successfully deaccessioned.";
    public static final String DEACCESSION_FAILED = "Deaccessioning the item failed";
    public static final int BARCODE_LIMIT = 10;
    public static final String ACCESSION_SUCCESS = "One or more records were successfully accessioned.";
    public static final String ACCESSION_DUMMY_RECORD = "Dummy record created";

    //Request
    public static final String REQUEST_ID = "requestId";

    public static final String SEARCH_RESULT_ERROR_NO_RECORDS_FOUND="No search results found. Please refine search conditions.";
    public static final String SEARCH_RESULT_ERROR_INVALID_CHARACTERS="No search results found. Search conditions, has invalid characters (double quotes [\"],open parenthesis [(], backslash [\\], curly braces[{}] and caret [^). Please refine search conditions.";

    public static final String CUSTOMER_CODE_DOESNOT_EXIST = "Customer Code doesn't exist in SCSB database.";
    public static final String ITEM_BARCODE_EMPTY = "Item Barcode is Blank.";
    public static final String CUSTOMER_CODE_EMPTY = "Customer Code is Blank.";
    public static final String OWNING_INST_EMPTY = "Owning Institution is Blank.";

    public static final String COLUMBIA = "CUL";
    public static final String PRINCETON = "PUL";
    public static final String UNKNOWN_INSTITUTION = "UN";
    public static final String NYPL = "NYPL";
    public static final String SCSB = "SCSB";

    public static final String OWNING_INSTITUTION_BIB_ID = "OwningInstitutionBibId";
    public static final String TITLE = "Title";
    public static final String OWNING_INSTITUTION_HOLDINGS_ID = "OwningInstitutionHoldingsId";
    public static final String OWNING_INSTITUTION_ITEM_ID = "OwningInstitutionItemId";
    public static final String LOCAL_ITEM_ID = "LocalItemId";
    public static final String ITEM_BARCODE = "ItemBarcode";
    public static final String CUSTOMER_CODE = "CustomerCode";
    public static final String CREATE_DATE_ITEM = "CreateDateItem";
    public static final String BIB_CREATED_DATE = "BibCreatedDate";
    public static final String BIB_LAST_UPDATED_DATE = "BibLastUpdatedDate";
    public static final String LAST_UPDATED_DATE_ITEM = "LastUpdatedDateItem";
    public static final String ERROR_DESCRIPTION = "ErrorDescription";
    public static final String DATE = "Date";
    public static final String ITEM_BARCDE_DOESNOT_EXIST = "Item Barcode doesn't exist in SCSB database.";
    public static final String BIB_ITEM_DOESNOT_EXIST = "Bib Id doesn't exist in SCSB database.";

    public static final String REQUESTED_ITEM_DEACCESSIONED = "The requested item has already been deaccessioned.";
    public static final String DATE_OF_DEACCESSION = "DateOfDeAccession";
    public static final String DATE_OF_ACCESSION = "DateOfAccession";

    public static final String FS_DE_ACCESSION_SUMMARY_REPORT_ID = "fsDeAccessionSummaryReportQ";
    public static final String FTP_DE_ACCESSION_SUMMARY_REPORT_ID = "ftpDeAccessionSummaryReportQ";

    public static final String FS_ACCESSION_SUMMARY_REPORT_ID = "fsAccessionSummaryReportQ";
    public static final String FTP_ACCESSION_SUMMARY_REPORT_ID = "ftpAccessionSummaryReportQ";
    public static final String FS_ONGOING_ACCESSION_REPORT_ID = "fsOngoingAccessionReportQ";
    public static final String FTP_ONGOING_ACCESSION_REPORT_ID = "ftpOngingAccessionReportQ";
    public static final String DEACCESION_DATE_FORMAT_FOR_FILE_NAME = "ddMMMyyyyHHmmss";
    public static final String ACCESSION_SUMMARY_REPORT = "Accession_Summary_Report";
    public static final String DEACCESSION_SUMMARY_REPORT = "DeAccession_Summary_Report";
    public static final String DEACCESSION_REPORT = "DeAccession_Report";
    public static final String ACCESSION_REPORT = "Accession_Report";
    public static final String TRANSFER_REPORT = "Transfer_Report";
    public static final String SUCCESS_BIB_COUNT = "successBibCount";
    public static final String FAILED_BIB_COUNT = "failedBibCount";
    public static final String SUCCESS_ITEM_COUNT = "successItemCount";
    public static final String FAILED_ITEM_COUNT = "failedItemCount";
    public static final String EXIST_BIB_COUNT = "exitsBibCount";
    public static final String REASON_FOR_BIB_FAILURE = "reasonForFailureBib";
    public static final String REASON_FOR_ITEM_FAILURE = "reasonForFailureItem";
    public static final String BIB_SUCCESS_COUNT = "SuccessBibCount";
    public static final String ITEM_SUCCESS_COUNT = "SuccessItemCount";
    public static final String BIB_FAILURE_COUNT = "FailedBibCount";
    public static final String ITEM_FAILURE_COUNT = "FailedItemCount";
    public static final String NUMBER_OF_BIB_MATCHES = "NoOfBibMatches";
    public static final String FAILURE_BIB_REASON = "ReasonForFailureBib";
    public static final String FAILURE_ITEM_REASON = "ReasonForFailureItem";
    public static final String ITEMBARCODE = "itemBarcode";

    public static final String SUBMIT_COLLECTION_REPORT = "Submit_Coll";
    public static final String SUBMIT_COLLECTION_REJECTION_REPORT = "Submit_Collection_Rejection_Report";
    public static final String SUBMIT_COLLECTION_EXCEPTION_REPORT = "Submit_Collection_Exception_Report";
    public static final String SUBMIT_COLLECTION_SUCCESS_REPORT = "Submit_Collection_Success_Report";
    public static final String SUBMIT_COLLECTION_FAILURE_REPORT = "SubmitCollection_Failure_Report";
    public static final String SUBMIT_COLLECTION_SUMMARY_REPORT = "SubmitCollectionSummary";
    public static final String FAIL = "Fail";
    public static final String REJECTION = "Rejection";
    public static final String SC_EXCEPTION = "Exception";
    public static final String SUBMIT_COLLECTION_ITEM_BARCODE= "ItemBarcode";
    public static final String SUBMIT_COLLECTION_CUSTOMER_CODE= "CustomerCode";
    public static final String ITEM_BARCODE_ALREADY_ACCESSIONED_MSG = "Unavailable barcode from partner is already accessioned";
    public static final String ACCESSION = "accession";
    public static final String REACCESSION = "re-accession";
    public static final String ITEM_ISDELETED_TRUE_TO_FALSE = "Item isdeleted true to false";
    public static final String DUMMYCALLNUMBER = "dummycallnumber";
    public static final String COMPLETE_STATUS = "Complete";
    public static final String INCOMPLETE_STATUS = "Incomplete";
    public static final String BIBLIOGRAPHICENTITY = "bibliographicEntity";
    public static final String REPORTENTITIES = "reportEntities";
    public static final String DUMMY_CALL_NUMBER_TYPE = "dummycallnumbertype";
    public static final String ONGOING_MATCHING_ALGORITHM_JOB = "ongoingMatchingAlgorithmJob";
    public static final String POPULATE_DATA_FOR_DATA_DUMP_JOB = "populateDataForDataDumpJob";

    //solr
    public static final String DATE_FORMAT_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String UTC_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String UTC = "UTC";
    public static final String SOLR_DATE_RANGE_TO_NOW = " TO NOW";
    public static final String BIB_ID_LIST = "BibIdList";
    public static final String BIB_ID_RANGE = "BibIdRange";
    public static final String DATE_RANGE = "DateRange";
    public static final String BIB_ID_RANGE_FROM = "BibIdRangeFrom";
    public static final String BIB_ID_RANGE_TO = "BibIdRangeTo";
    public static final String DATE_RANGE_FROM = "DateRangeFrom";
    public static final String DATE_RANGE_TO = "DateRangeTo";

    //Reports
    public static final String SIMPLE_DATE_FORMAT_REPORTS = "MM/dd/yyyy";
    public static final String REPORTS = "reports";
    public static final String REPORTS_REQUEST = "request";
    public static final String REPORTS_IL_BD = "IL_BD";
    public static final String REPORTS_PARTNERS = "Partners";
    public static final String REPORTS_REQUEST_TYPE = "RequestType";
    public static final String REPORTS_ACCESSION_DEACCESSION = "Accession/Deaccesion";
    public static final String REPORTS_DEACCESSION = "Deaccession";
    public static final String REPORTS_RETRIEVAL = "Retrieval";
    public static final String REPORTS_RECALL = "Recall";
    public static final String REPORTS_OPEN = "Open";
    public static final String REPORTS_SHARED = "Shared";
    public static final String REPORTS_PRIVATE = "Private";
    public static final String RETRIEVAL = "RETRIEVAL";
    public static final String RECALL = "RECALL";
    public static final String EDD = "EDD";
    public static final String BORROW_DIRECT = "BORROW DIRECT";
    public static final String TRANSMISSION_TYPE = "TransmissionType";
    public static final String REPORT_TYPE = "ReportType";
    public static final String JOB_PARAM_DATA_FILE_NAME = "FileName";
    public static final String DATE_FORMAT_FOR_REPORTS = "yyyyMMdd_HHmmss";


    public static final String  BIBITEM_LASTUPDATED_DATE = "BibItemLastUpdatedDate";
    public static final String  ITEM_LASTUPDATED_DATE = "ItemLastUpdatedDate";
    public static final String  ITEM_CREATED_DATE = "ItemCreatedDate";
    public static final String  BIB_LASTUPDATED_DATE = "BibLastUpdatedDate";

    public static final String CGD_UPDATE_ITEM_BARCODE = "itemBarcode";
    public static final String OWNING_INST = "owningInstitution";
    public static final String OLD_CGD = "oldCollectionGroupDesignation";
    public static final String NEW_CGD = "newCollectionGroupDesignation";
    public static final String CGD_CHANGE_NOTES = "cgdChangeNotes";
    public static final String USER_NAME = "userName";
    public static final String TO = "To";
    public static final String ITEM_CATALOGING_STATUS = "ItemCatalogingStatus";
    public static final String BIB_CATALOGING_STATUS = "BibCatalogingStatus";
    public static final String MESSAGE = "Message";
    public static final String ONGOING_ACCESSION_REPORT = "Ongoing_Accession_Report";
    public static final String ERROR = "error->";
    public static final String EXCEPTION = "exception->";
    public static final String ITEM_BARCODE_NOT_FOUND = "Item Barcode not found";
    public static final String MARC_FORMAT_PARSER_ERROR = "Unable to parse input";
    public static final String INVALID_MARC_XML_ERROR_MSG = "Unable to parse input, xml is having invalid marc tag";
    public static final String SERVICE_UNAVAILABLE =" Service is unavailable.";
    public static final String VERIFY_ONGOING_ACCESSION_REPORT_MSG = "Please verify ongoing accession report";
    public static final String DUMMY_BIB_CONTENT_XML = "dummybibcontent.xml";
    public static final String DUMMY_HOLDING_CONTENT_XML = "dummyholdingcontent.xml";
    public static final String ONGOING_ACCESSION_LIMIT_EXCEED_MESSAGE = "Input limit exceeded, maximum allowed input limit is ";
    public static final String HYPHEN = " - ";
    public static final String ITEM_ALREADY_ACCESSIONED = "Item already accessioned - Existing item details : ";
    public static final String INVALID_BOUNDWITH_RECORD = "Bound-with item having invalid data";
    public static final String FAILED = "Failed";

    public static final String STATUS_DONE="Status  : Done";
    public static final String TOTAL_TIME_TAKEN="Total Time Taken ";
    public static final String STATUS_FAILED="Status : Failed";
    public static final String PUL_MATCHING_COUNT="pulMatchingCount";
    public static final String CUL_MATCHING_COUNT="culMatchingCount";
    public static final String NYPL_MATCHING_COUNT="nyplMatchingCount";
    public static final String GENERATED_REPORT_FILE_NAME="The Generated Report File Name : ";
    public static final String TOTAL_TIME_TAKEN_TO_GENERATE_FILE_NAME="Total time taken to generate File : ";
    public static final String ACCESSION_FAILURE_REPORT="Accession_Failure_Report";
    public static final String TOTAL_BIB_ID_PARTITION_LIST="Total Bib Id partition List : {}";
    public static final String NON_MONOGRAPH_RECORD_NUMS="NonMonographRecordNums";
    public static final String EXCEPTION_RECORD_NUMS="ExceptionRecordNums";

    //Logger
    public static final String LOG_ERROR="error-->";
    public static final String SCSB_PERSISTENCE_SERVICE_IS_UNAVAILABLE = "Scsb Persistence Service is Unavailable.";
    public static final String CGD_NA="NA";
    public static final String SUBMIT_COLLECTION_SUMMARY = "SubmitCollectionSummary";

    public static final String NUMBER_PATTERN="[^0-9]";

    public static final String SFTP = "sftp://";
    public static final String AT = "@";
    public static final String FILE = "file:";
    public static final String DELETE_FILE_OPTION = "?delete=true";
    public static final String PRIVATE_KEY_FILE = "?privateKeyFile=";
    public static final String KNOWN_HOST_FILE = "&knownHostsFile=";
    public static final String FILE_NAME = "fileName";
    public static final String SUBMIT_COLLECTION_REPORT_SFTP_OPTIONS = "&fileName=${in.header.fileName}&fileExist=append";
    public static final String ITEM_LAST_UPDATED_DATE = "ItemLastUpdatedDate";

    //deaccession report
    public static final String BIB_DOC_TYPE = "DocType:Bib";
    public static final String SOLR_BIB_ID = "BibId:";
    public static final String IS_DELETED_BIB_TRUE = "IsDeletedBib:true";
    public static final String TITLE_DISPLAY = "Title_display";

    public static final String EMAIL_FOR = "emailFor";
    public static final String UPDATECGD = "updateCgd";
    public static final String BATCHJOB = "batchJob";
    public static final String PURGE_EXCEPTION_REQUESTS = "PurgeExceptionRequests";
    public static final String PENDING = "pending";
    public static final String ACCESSION_SAVE_SUCCESS_STATUS = "The accession request is successfully processed.";
    public static final String ACCESSION_SAVE_FAILURE_STATUS = "Failed to process accession request.";
    public static final String MATCHING_REPORTS = "matchingReports";
    public static final String ACCESSION_REPORTS = "accessionReports";
    public static final String MATCHING_ALGORITHM_REPORTS = "MatchingAlgorithm Reports";
    public static final String ACCESSION_BATCH_COMPLETE = "Accession Batch Complete";
    public static final String INSTITUTION_NAME = "institutionName";

    public static final String MIXED_STATUS = "MixedStatus";

    public static final String DOC_TYPE_ITEM = "DocType:Item";
    public static final String ITEM_STATUS_INCOMPLETE = "ItemCatalogingStatus:Incomplete";
    public static final String IS_DELETED_ITEM_FALSE = "IsDeletedItem:false";
    public static final String ITEM_BIB_ID = "ItemBibId";
    public static final String AUTHOR_DISPLAY = "Author_display";

    public static final String SUBMIT_COLLECTION = "SubmitCollectionReport";
    public static final String OWN_INST_BIB_ID = " OwningInstBibId-";
    public static final String OWN_INST_HOLDING_ID = " OwningInstHoldingId-";
    public static final String OWN_INST_ITEM_ID = " OwningInstItemId-";
    public static final String OWN_INST_BIBID_LIST = "owningInstBibIdList";
    public static final String OWN_INSTITUTION_ID = "institutionId";


    public static final String BULK_ACCESSION_SUMMARY = "BULK_ACCESSION_SUMMARY";
    public static final String ACCESSION_SUMMARY = "ACCESSION_SUMMARY";
    public static final String ACCESSION_JOB_FAILURE = "Exception occurred in SCSB Ongoing Accession Job";

    public static class SERVICE_PATH {
        public static final String CHECKIN_ITEM = "requestItem/checkinItem";
        public static final String REFILE_ITEM_IN_ILS = "requestItem/refileItemInILS";
        public static final String REPLACE_REQUEST = "requestItem/replaceRequest";
    }

    public static final String SUCCESS_INCOMPLETE_RECORD = "Success - Incomplete record";
    public static final String INCOMPLETE_RESPONSE = "incompleteResponse";

    public static final String ACCESSION_NO_PENDING_REQUESTS = "No pending requests to process accession.";

    public static class TRANSFER {
        public static final String TRANSFER = "transfer";
        public static final String INSTITUION_EMPTY = "Institution is empty";
        public static final String UNKNOWN_INSTITUTION = "Unknow institution";
        public static final String SOURCE_DESTINATION_ITEM_IDS_NOT_MATCHING = "Source and Destination item ids are not matching";
        public static final String SOURCE_DESTINATION_HOLDINGS_IDS_NOT_MATCHING = "Source and Destination holdings ids are not matching";
        public static final String SOURCE_EMPTY = "Source is empty";
        public static final String DESTINATION_EMPTY = "Destination is empty";
        public static final String SOURCE_OWN_INST_BIB_ID_EMPTY = "Source owning institution bib id is empty";
        public static final String SOURCE_OWN_INST_ITEM_ID_EMPTY = "Source owning institution item id is empty";
        public static final String DEST_OWN_INST_BIB_ID_EMPTY = "Destination owning institution bib id is empty";
        public static final String SOURCE_OWN_INST_HOLDINGS_ID_EMPTY = "Source owning institution holdings id is empty";
        public static final String DEST_OWN_INST_HOLDINGS_ID_EMPTY = "Destination owning institution holdings id is empty";
        public static final String DEST_OWN_INST_ITEM_ID_EMPTY = "Destination owning institution item id is empty";
        public static final String SOURCE_BIB_NOT_EXIST = "Source bib does not exist";
        public static final String SOURCE_HOLDING_NOT_UNDER_SOURCE_BIB = "Source holdings is not under source bib";
        public static final String SOURCE_ITEM_NOT_UNDER_SOURCE_HOLDING = "Source item is not under source holding";
        public static final String DEST_HOLDINGS_ATTACHED_WITH_DIFF_BIB = "Destination holdings is linked with another bib and not under destination bib";
        public static final String DEST_HOLDING_DEACCESSIONED="Destination holding is a deaccessioned holding";
        public static final String SOURCE_ITEM_DEACCESSIONED="Source item is a deaccessioned item";
        public static final String DEST_BIB_DEACCESSIONED="Destination Bib is a deaccessioned bib";
        public static final String SOURCE_HOLDING_DEACCESSIONED="Source Holding is a deaccessioned holding";

        public static final String SUCCESSFULLY_RELINKED = "Successfully relinked";
        public static final String RELINKED_FAILED = "Relinked Failed";
        public static final String COMPLETED = "Success";
        public static final String FAILED = "Failed";
        public static final String PARTIALLY_SUCCESS = "Partially Success";
        public static final String REQUEST = "Request";
        public static final String RESPONSE = "Response";
        public static final String INSTITUTION = "Institution";
        public static final String TRANSFER_TYPE = "TransferType";
        public static class TRANSFER_TYPES {
            public static final String HOLDINGS_TRANSFER = "Holdings Transfer";
            public static final String ITEM_TRANSFER = "Item Transfer";
        }
        public static final String ROOT = "Root";
    }

    public static final String ITEM_STATUS_COMPLETE="ItemCatalogingStatus:Complete";
    public static final String BIB_STATUS_INCOMPLETE="BibCatalogingStatus:Incomplete";
    public static final String DISTINCT_VALUES_FALSE="{!distinctValues=false}Barcode";
    public static final String GROUP = "group";
    public static final String GROUP_FIELD = "group.field";
    public static final String INCREMENTAL_DUMP_TO_NOW = "TO NOW";

    public static final String ACCESSION_JOB_INITIATE_QUEUE = "scsbactivemq:queue:accessionInitiateQ";
    public static final String ACCESSION_JOB_INITIATE_ROUTE_ID = "scsbactivemq:queue:accessionInitiateRoute";
    public static final String ACCESSION_JOB_COMPLETION_OUTGOING_QUEUE = "scsbactivemq:queue:accessionCompletionOutgoingQ";
    public static final String MATCHING_ALGORITHM_JOB_INITIATE_QUEUE = "scsbactivemq:queue:matchingAlgorithmInitiateQ";
    public static final String MATCHING_ALGORITHM_JOB_INITIATE_ROUTE_ID = "scsbactivemq:queue:matchingAlgorithmInitiateRoute";
    public static final String MATCHING_ALGORITHM_JOB_COMPLETION_OUTGOING_QUEUE = "scsbactivemq:queue:matchingAlgorithmCompletionOutgoingQ";

    public static final String JOB_ID = "jobId";
    public static final String PROCESS_TYPE = "processType";
    public static final String CREATED_DATE = "createdDate";
    public static final String PROCESSING = "Processing";
    public static final String MATCHING_BATCH_JOB_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
    public static final String TOTAL_REQUESTS_FOUND = "TotalRequestsFound";
    public static final String TOTAL_REQUESTS_IDS = "TotalRequestIds";
    public static final String INVALID_REQUEST = "InvalidRequest";

    public static final String DIRECT_ROUTE_FOR_EXCEPTION = "direct:Exception";
    public static final String ACCESSION__CAUGHT_EXCEPTION_METHOD = "caughtException";

    private RecapConstants(){}
}