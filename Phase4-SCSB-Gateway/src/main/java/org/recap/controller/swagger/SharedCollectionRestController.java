package org.recap.controller.swagger;

import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.recap.ReCAPConstants;
import org.recap.model.AccessionRequest;
import org.recap.model.BibItemAvailabityStatusRequest;
import org.recap.model.DeAccessionRequest;
import org.recap.model.ItemAvailabityStatusRequest;
import org.recap.model.acession.AccessionResponse;
import org.recap.model.transfer.TransferRequest;
import org.recap.model.transfer.TransferResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by chenchulakshmig on 6/10/16.
 */
@RestController
@RequestMapping("/sharedCollection")
@Api(value = "sharedCollection")
public class SharedCollectionRestController {

    private static final Logger logger = LoggerFactory.getLogger(SharedCollectionRestController.class);

    @Value("${scsb.solr.client.url}")
    private String scsbSolrClientUrl;

    @Value("${scsb.circ.url}")
    private String scsbCircUrl;

    /**
     * Gets scsb circ url.
     *
     * @return the scsb circ url
     */
    public String getScsbCircUrl() {
        return scsbCircUrl;
    }

    /**
     * Gets rest template.
     *
     * @return the rest template
     */
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    /**
     * Gets scsb solr client url.
     *
     * @return the scsb solr client url
     */
    public String getScsbSolrClientUrl() {
        return scsbSolrClientUrl;
    }

    public LinkedMultiValueMap getLinkedMultiValueMap(){
        return new LinkedMultiValueMap<>();
    }


    /**
     * This method will call scsb-solr-client microservice to get item availability status in scsb.
     *
     * @param itemAvailabityStatus the item availabity status
     * @return the response entity
     */
    @RequestMapping(value = "/itemAvailabilityStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "itemAvailabilityStatus",
            notes = "The Item availability status API returns the availability status of the item in SCSB. It is likely to be used in partner ILS' Discovery systems to retrieve and display item statuses.", nickname = "itemAvailabilityStatus")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ResponseBody
    public ResponseEntity itemAvailabilityStatus(@ApiParam(value = "Item Barcodes with ',' separated", required = true, name = "itemBarcodes") @RequestBody ItemAvailabityStatusRequest itemAvailabityStatus) {
        String response;
        try {
            response = getRestTemplate().postForObject(getScsbSolrClientUrl() + "/sharedCollection/itemAvailabilityStatus", itemAvailabityStatus, String.class);
        } catch (Exception exception) {
            logger.error(ReCAPConstants.LOG_ERROR, exception);
            return new ResponseEntity(ReCAPConstants.SCSB_SOLR_CLIENT_SERVICE_UNAVAILABLE, getHttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        if (StringUtils.isEmpty(response)) {
            return new ResponseEntity(ReCAPConstants.ITEM_BARCDE_DOESNOT_EXIST, getHttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity(response, getHttpHeaders(), HttpStatus.OK);
        }
    }

    /**
     *This method will call scsb-solr-client microservice to get the bib availability status in scsb.
     *
     * @param bibItemAvailabityStatusRequest the bib item availabity status request
     * @return the response entity
     */
    @RequestMapping(value = "/bibAvailabilityStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "bibAvailabilityStatus",
            notes = "Bib availability status API returns the availability statuses of items associated with the bibliographic record. Since it returns availability statuses of all items associated with a bib, it is likely to be used in partner ILS' Discovery systems to retrieve and display multiple items and their statuses in case of serials and multi volume monographs.", nickname = "bibAvailabilityStatus")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),@ApiResponse(code = 503, message = "Service Not Available")})
    @ResponseBody
    public ResponseEntity bibAvailabilityStatus(@ApiParam(value = "Owning Inst BibID, or SCSB BibId", required = true, name = "") @RequestBody BibItemAvailabityStatusRequest bibItemAvailabityStatusRequest) {
        String response;
        try {
            response = getRestTemplate().postForObject(getScsbSolrClientUrl() + "/sharedCollection/bibAvailabilityStatus", bibItemAvailabityStatusRequest, String.class);
        } catch (Exception exception) {
            logger.error(ReCAPConstants.LOG_ERROR, exception);
            return new ResponseEntity(ReCAPConstants.SCSB_SOLR_CLIENT_SERVICE_UNAVAILABLE, getHttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity(response, getHttpHeaders(), HttpStatus.OK);
    }

    /**
     * This method will call scsb-circ microservice to soft delete the given items from the scsb database and scsb solr and mark isDeletedItem field as true.
     *
     * @param deAccessionRequest the de accession request
     * @return the response entity
     */
    @RequestMapping(value = "/deaccession", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "deaccession",
            notes = "The Deaccession API is an internal call made by SCSB to remove a record. Deaccession will only be done through the UI by users who are authorized to perform the operation. Deaccessioning an item would mark the record as removed (deleted) in the SCSB database.", nickname = "deaccession")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ResponseBody
    public ResponseEntity deAccession(@ApiParam(value = "Provide item barcodes to be deaccessioned, separated by comma", required = true, name = "itemBarcodes") @RequestBody DeAccessionRequest deAccessionRequest) {
        try {
            Map<String, String> resultMap = getRestTemplate().postForObject(getScsbCircUrl() + "/sharedCollection/deAccession", deAccessionRequest, Map.class);
            return new ResponseEntity(resultMap, getHttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ReCAPConstants.LOG_ERROR, ex);
            return new ResponseEntity(ReCAPConstants.SCSB_CIRC_SERVICE_UNAVAILABLE, getHttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    /**This method will call scsb-solr-client microservice to add multiple new items in the scsb database and scsb solr.
     * @param accessionRequestList the accession request list
     * @return the response entity
     */
    @RequestMapping(value = "/accessionBatch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "accessionBatch",
            notes = "Accession batch is similar to the accession API except that accession batch API accepts multiple item barcodes in a single request call. The Accession batch process is a deferred process to reduce performance bottlenecks. The barcodes and customer codes are stored in SCSB DB and is processed as per schedule of the Accession job (usually, nightly). After processing, a report on the barcodes that were processed is prepared and stored at the FTP location.", nickname = "accessionBatch")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ResponseBody
    public ResponseEntity accessionBatch(@ApiParam(value = "Item Barcode and Customer Code", required = true, name = "Item Barcode And Customer Code") @RequestBody List<AccessionRequest> accessionRequestList) {
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            String responseMessage = getRestTemplate().postForObject(getScsbSolrClientUrl() + "sharedCollection/accessionBatch", accessionRequestList, String.class);
            ResponseEntity responseEntity = new ResponseEntity(responseMessage, getHttpHeaders(), HttpStatus.OK);
            stopWatch.stop();
            logger.info("Total time taken for saving accession request-->{}sec", stopWatch.getTotalTimeSeconds());
            return responseEntity;
        } catch (Exception exception) {
            logger.error(ReCAPConstants.LOG_ERROR, exception);
            return new ResponseEntity(ReCAPConstants.SCSB_SOLR_CLIENT_SERVICE_UNAVAILABLE, getHttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    /**
     * This method will call scsb-solr-client microservice to add a new item in the scsb database and scsb solr.
     * @param accessionRequestList the accession request list
     * @return the response entity
     */
    @RequestMapping(value = "/accession", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "accession",
            notes = "The Accession API (also known as Ongoing Accession) is used to add item records to SCSB whenever there is a new item added to the ReCAP facility. GFA LAS calls this API as part of the accession workflow with the customer code and item barcode.", nickname = "accession")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ResponseBody
    public ResponseEntity accession(@ApiParam(value = "Item Barcode and Customer Code", required = true, name = "Item Barcode And Customer Code") @RequestBody List<AccessionRequest> accessionRequestList) {
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            ResponseEntity responseEntity;
            List<LinkedHashMap> linkedHashMapList = getRestTemplate().postForObject(getScsbSolrClientUrl() + "sharedCollection/accession", accessionRequestList, List.class);
            if (null != linkedHashMapList && linkedHashMapList.get(0).get("message").toString().contains(ReCAPConstants.ONGOING_ACCESSION_LIMIT_EXCEED_MESSAGE)) {
                responseEntity = new ResponseEntity(linkedHashMapList, getHttpHeaders(), HttpStatus.BAD_REQUEST);
            } else {
                responseEntity = new ResponseEntity(linkedHashMapList, getHttpHeaders(), HttpStatus.OK);
            }
            stopWatch.stop();
            logger.info("Total time taken for accession-->{}sec",stopWatch.getTotalTimeSeconds());
            return responseEntity;
        } catch (ResourceAccessException resourceAccessException){
            logger.error(ReCAPConstants.LOG_ERROR, resourceAccessException);
            List<AccessionResponse> accessionResponseList=new ArrayList<>();
            AccessionResponse accessionResponse=new AccessionResponse();
            accessionResponse.setMessage(ReCAPConstants.SCSB_SOLR_CLIENT_SERVICE_UNAVAILABLE);
            accessionResponseList.add(accessionResponse);
            return new ResponseEntity(accessionResponseList, getHttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception exception) {
            logger.error(ReCAPConstants.LOG_ERROR, exception);
            List<AccessionResponse> accessionResponseList = new ArrayList<>();
            AccessionResponse accessionResponse=new AccessionResponse();
            accessionResponse.setMessage(ReCAPConstants.ACCESSION_INTERNAL_ERROR);
            accessionResponseList.add(accessionResponse);
            return new ResponseEntity(accessionResponseList, getHttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    /**
     * This method will call scsb-circ microservice to update the items which is already present in the scsb database and scsb solr.
     * @param inputRecords the input records
     * @return the response entity
     */
    @RequestMapping(value = "/submitCollection", method = RequestMethod.POST)
    @ApiOperation(value = "submitCollection",
            notes = "Submit collection API is a REST service where users can provide MARC content in either SCSB XML or MARC XML formats and update the underlying record in SCSB. After the successful completion of the API, a report is sent.", nickname = "submitCollection")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ResponseBody
    public ResponseEntity submitCollection(@ApiParam(value = "Provide marc xml or scsb xml format to update the records", required = true, name = "inputRecords") @RequestBody String inputRecords,
                                           @ApiParam(value = "Provide institution code", required = true, name = "institution") @RequestParam String institution,
    @ApiParam(value = "Provide boolean value is cgd protected (true or false)", required = true, name = "isCGDProtected") @RequestParam Boolean isCGDProtected) {
        ResponseEntity responseEntity;
        try {
            MultiValueMap<String,Object> requestParameter = getLinkedMultiValueMap();
            requestParameter.add(ReCAPConstants.INPUT_RECORDS,inputRecords);
            requestParameter.add(ReCAPConstants.INSTITUTION,institution);
            requestParameter.add(ReCAPConstants.IS_CGD_PROTECTED,isCGDProtected);
            List<LinkedHashMap> linkedHashMapList =getRestTemplate().postForObject(getScsbCircUrl() + "sharedCollection/submitCollection",requestParameter, List.class);
            String message = linkedHashMapList != null ? linkedHashMapList.get(0).get("message").toString():ReCAPConstants.SUBMIT_COLLECTION_INTERNAL_ERROR;
            if (message.equalsIgnoreCase(ReCAPConstants.INVALID_MARC_XML_FORMAT_MESSAGE) || message.equalsIgnoreCase(ReCAPConstants.INVALID_SCSB_XML_FORMAT_MESSAGE)
                    || message.equalsIgnoreCase(ReCAPConstants.SUBMIT_COLLECTION_INTERNAL_ERROR)) {
                responseEntity = new ResponseEntity(linkedHashMapList, getHttpHeaders(), HttpStatus.BAD_REQUEST);
            } else {
                responseEntity = new ResponseEntity(linkedHashMapList, getHttpHeaders(), HttpStatus.OK);
            }
            return responseEntity;
        } catch (Exception exception) {
            logger.error(ReCAPConstants.LOG_ERROR, exception);
            responseEntity = new ResponseEntity(ReCAPConstants.SUBMIT_COLLECTION_INTERNAL_ERROR, getHttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
            return responseEntity;
        }
    }

    /**
     * This method will call scsb-solr-client microservice to transfer the holdings/items which is already present in the scsb database and scsb solr.
     * @param transferRequest the input records
     * @return the response entity
     */
    @RequestMapping(value = "/transferHoldingsAndItems", method = RequestMethod.POST)
    @ApiOperation(value = "transferHoldingsAndItems",
            notes = "TransferHoldingsAndItems API is a REST service where users can provide source and destination of " +
                    "the holdings and item for transfer", nickname = "transferHoldingsAndItems")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    @ResponseBody
    public ResponseEntity transferHoldingsAndItems(
            @ApiParam(value = "Source and destination of holdings and items in JSON format", required = true, name = "")
            @RequestBody TransferRequest transferRequest) {
        ResponseEntity responseEntity;
        try {
            TransferResponse transferResponse = getRestTemplate().postForObject(getScsbSolrClientUrl() + "transfer/processTransfer", transferRequest, TransferResponse.class);
            responseEntity = new ResponseEntity(transferResponse, getHttpHeaders(), HttpStatus.OK);
        } catch (Exception exception) {
            logger.error(ReCAPConstants.LOG_ERROR, exception);
            responseEntity = new ResponseEntity(ReCAPConstants.TRANSFER_INTERNAL_ERROR, getHttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return responseEntity;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(ReCAPConstants.RESPONSE_DATE, new Date().toString());
        return responseHeaders;
    }
}