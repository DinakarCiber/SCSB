package org.recap.controller.swagger;

import io.swagger.annotations.*;
import org.recap.ReCAPConstants;
import org.recap.spring.SwaggerAPIProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by premkb on 19/8/16.
 */
@RestController
@RequestMapping("/dataDump")
@Api(value="dataDump", description="Export data dump", position = 1)
public class DataDumpRestController {

    private static final Logger logger = LoggerFactory.getLogger(DataDumpRestController.class);

    @Value("${scsb.etl.url}")
    private String scsbEtlUrl;

    public String getScsbEtlUrl() {
        return scsbEtlUrl;
    }

    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * This method is the entry point to start data export process and passes to request to the scsb-etl microservice.
     *
     * @param institutionCodes          the institution codes
     * @param requestingInstitutionCode the requesting institution code
     * @param fetchType                 the fetch type
     * @param outputFormat              the output format
     * @param date                      the date
     * @param collectionGroupIds        the collection group ids
     * @param transmissionType          the transmission type
     * @param emailToAddress            the email to address
     * @return the response entity
     */
    @RequestMapping(value="/exportDataDump", method = RequestMethod.GET)
    @ApiOperation(value = "exportDataDump",
            notes = "The Export Data Dump API allows export of bibliographic records in SCSB database into MARCXML or SCSBXML format. This is used by partners to export records in preferred format and update their respective discovery systems. These jobs are scheduled to run by HTC support.", nickname = "exportDataDump", position = 0)
    @ApiResponses(value = {@ApiResponse(code = 200, message = ReCAPConstants.DATADUMP_PROCESS_STARTED)})
    @ResponseBody
    public ResponseEntity exportDataDump(@ApiParam(value = "Institution code(s) for requesting shared/open updates from partners: PUL = Princeton, CUL = Columbia, NYPL = New York Public Library" , required = true, name = "institutionCodes") @RequestParam String institutionCodes,
                                         @ApiParam(value = "Institution codes of the requesting institution. PUL = Princeton, CUL = Columbia, NYPL = New York Public Library",required=true, name = "requestingInstitutionCode") @RequestParam String requestingInstitutionCode,
                                         @ApiParam(value = "Type of export - Incremental (use 1) or Deleted (use 2)" , required = true , name = "fetchType") @RequestParam String fetchType,
                                         @ApiParam(value = "Type of format - Marc xml (use 0) or SCSB xml (use 1), for deleted records only json format (use 2)",required=true, name = "outputFormat") @RequestParam String outputFormat,
                                         @ApiParam(value = "Get updates to middleware collection since the date provided. Date format will be a string (yyyy-MM-dd HH:mm) and is Eastern Time.",name = "date") @RequestParam(required = false) String date,
                                         @ApiParam(value = "Data can be requested by Collection Group ID, either Shared (use 1) or Open (use 2). Default is both, can use 1,2 as well.", name = "collectionGroupIds") @RequestParam(required=false) String collectionGroupIds,
                                         @ApiParam(value = "Type of transmission - for FTP use 0, for HTTP response use 1. Default is FTP.", name = "transmissionType")@RequestParam(required=false) String transmissionType,
                                         @ApiParam(value = "Email address to whom email will be sent upon completion" , name = "emailToAddress")@RequestParam(required=false) String emailToAddress
    ){
        RestTemplate restTemplate = getRestTemplate();
        Map<String,String> inputMap = new HashMap<>();
        inputMap.put("institutionCodes",institutionCodes);
        inputMap.put("requestingInstitutionCode",requestingInstitutionCode);
        inputMap.put("fetchType",fetchType);
        inputMap.put("outputFormat",outputFormat);
        inputMap.put("date",date);
        inputMap.put("collectionGroupIds",collectionGroupIds);
        inputMap.put("transmissionType",transmissionType);
        inputMap.put("emailToAddress",emailToAddress);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("api_key", SwaggerAPIProvider.getInstance().getSwaggerApiKey());
            HttpEntity requestEntity = new HttpEntity(headers);
            ResponseEntity<String> response = restTemplate.exchange(getScsbEtlUrl() + "dataDump/exportDataDump/?institutionCodes={institutionCodes}&requestingInstitutionCode={requestingInstitutionCode}&fetchType={fetchType}&outputFormat={outputFormat}&date={date}&collectionGroupIds={collectionGroupIds}&transmissionType={transmissionType}&emailToAddress={emailToAddress}", HttpMethod.GET, requestEntity, String.class, inputMap);
            return new ResponseEntity(response.getBody(), getHttpHeaders(), getHttpStatus(response.getBody()));
        } catch (Exception exception) {
            logger.error("error-->",exception);
            return new ResponseEntity("Scsb Etl Service is Unavailable.", getHttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    /**
     * This method is the entry point to start data export process and passes to request to the scsb-etl microservice.
     *
     * @param institutionCodes          the institution codes
     * @param requestingInstitutionCode the requesting institution code
     * @param fetchType                 the fetch type
     * @param outputFormat              the output format
     * @param date                      the date
     * @param toDate                    the toDate
     * @param collectionGroupIds        the collection group ids
     * @param transmissionType          the transmission type
     * @param emailToAddress            the email to address
     * @return the response entity
     */
    @RequestMapping(value="/exportDataDumpWithToDate", method = RequestMethod.GET)
    @ApiOperation(value = "exportDataDump",
            notes = "The Export Data Dump API allows export of bibliographic records in SCSB database into MARCXML or SCSBXML format. This is used by partners to export records in preferred format and update their respective discovery systems. These jobs are scheduled to run by HTC support.", nickname = "exportDataDump", position = 0)
    @ApiResponses(value = {@ApiResponse(code = 200, message = ReCAPConstants.DATADUMP_PROCESS_STARTED)})
    @ResponseBody
    public ResponseEntity exportDataDumpWithToDate(@ApiParam(value = "Institution code(s) for requesting shared/open updates from partners: PUL = Princeton, CUL = Columbia, NYPL = New York Public Library" , required = true, name = "institutionCodes") @RequestParam String institutionCodes,
                                         @ApiParam(value = "Institution codes of the requesting institution. PUL = Princeton, CUL = Columbia, NYPL = New York Public Library",required=true, name = "requestingInstitutionCode") @RequestParam String requestingInstitutionCode,
                                         @ApiParam(value = "Type of export - Incremental (use 1) or Deleted (use 2)" , required = true , name = "fetchType") @RequestParam String fetchType,
                                         @ApiParam(value = "Type of format - Marc xml (use 0) or SCSB xml (use 1), for deleted records only json format (use 2)",required=true, name = "outputFormat") @RequestParam String outputFormat,
                                         @ApiParam(value = "Get updates to middleware collection since the date provided. Date format will be a string (yyyy-MM-dd HH:mm) and is Eastern Time.",name = "date") @RequestParam(required = false) String date,
                                         @ApiParam(value = "Get updates to middleware collection until the date provided. Date format will be a string (yyyy-MM-dd HH:mm) and is Eastern Time.",name = "toDate") @RequestParam(required = false) String toDate,
                                         @ApiParam(value = "Data can be requested by Collection Group ID, either Shared (use 1) or Open (use 2). Default is both, can use 1,2 as well.", name = "collectionGroupIds") @RequestParam(required=false) String collectionGroupIds,
                                         @ApiParam(value = "Type of transmission - for FTP use 0, for HTTP response use 1. Default is FTP.", name = "transmissionType")@RequestParam(required=false) String transmissionType,
                                         @ApiParam(value = "Email address to whom email will be sent upon completion" , name = "emailToAddress")@RequestParam(required=false) String emailToAddress
    ){
        RestTemplate restTemplate = getRestTemplate();
        Map<String,String> inputMap = new HashMap<>();
        inputMap.put("institutionCodes",institutionCodes);
        inputMap.put("requestingInstitutionCode",requestingInstitutionCode);
        inputMap.put("fetchType",fetchType);
        inputMap.put("outputFormat",outputFormat);
        inputMap.put("date",date);
        inputMap.put("toDate",toDate);
        inputMap.put("collectionGroupIds",collectionGroupIds);
        inputMap.put("transmissionType",transmissionType);
        inputMap.put("emailToAddress",emailToAddress);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("api_key", SwaggerAPIProvider.getInstance().getSwaggerApiKey());
            HttpEntity requestEntity = new HttpEntity(headers);
            ResponseEntity<String> response = restTemplate.exchange(getScsbEtlUrl() + "dataDump/exportDataDump/?institutionCodes={institutionCodes}&requestingInstitutionCode={requestingInstitutionCode}&fetchType={fetchType}&outputFormat={outputFormat}&date={date}&toDate={toDate}&collectionGroupIds={collectionGroupIds}&transmissionType={transmissionType}&emailToAddress={emailToAddress}", HttpMethod.GET, requestEntity, String.class, inputMap);
            return new ResponseEntity(response.getBody(), getHttpHeaders(), getHttpStatus(response.getBody()));
        } catch (Exception exception) {
            logger.error("error-->",exception);
            return new ResponseEntity("Scsb Etl Service is Unavailable.", getHttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(ReCAPConstants.RESPONSE_DATE, new Date().toString());
        return responseHeaders;
    }

    private HttpStatus getHttpStatus(String message){
        if(message.equals(ReCAPConstants.DATADUMP_PROCESS_STARTED) || message.equals(ReCAPConstants.DATADUMP_NO_RECORD) || message.contains(ReCAPConstants.XML)){
            return HttpStatus.OK;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }
}
