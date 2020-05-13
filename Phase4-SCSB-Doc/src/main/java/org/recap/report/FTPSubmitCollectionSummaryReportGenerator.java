package org.recap.report;

import org.apache.camel.ProducerTemplate;
import org.recap.RecapConstants;
import org.recap.model.csv.SubmitCollectionReportRecord;
import org.recap.model.jpa.ReportEntity;
import org.recap.util.SubmitCollectionReportGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hemalathas on 21/12/16.
 */
@Component
public class FTPSubmitCollectionSummaryReportGenerator implements ReportGeneratorInterface {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    public boolean isInterested(String reportType) {
        return reportType.equalsIgnoreCase(RecapConstants.SUBMIT_COLLECTION_SUMMARY) ? true : false;
    }

    @Override
    public boolean isTransmitted(String transmissionType) {
        return transmissionType.equalsIgnoreCase(RecapConstants.FTP) ? true : false;
    }

    @Override
    public String generateReport(String fileName, List<ReportEntity> reportEntityList) {
        String generatedFileName;
        List<SubmitCollectionReportRecord> submitCollectionReportRecordList = new ArrayList<>();
        SubmitCollectionReportGenerator submitCollectionReportGenerator = new SubmitCollectionReportGenerator();
        for(ReportEntity reportEntity : reportEntityList) {
            List<SubmitCollectionReportRecord> submitCollectionReportRecords = submitCollectionReportGenerator.prepareSubmitCollectionRejectionRecord(reportEntity);
            submitCollectionReportRecordList.addAll(submitCollectionReportRecords);
        }
        DateFormat df = new SimpleDateFormat(RecapConstants.DATE_FORMAT_FOR_FILE_NAME);
        generatedFileName = fileName + "-" + df.format(new Date()) + ".csv";
        producerTemplate.sendBodyAndHeader(RecapConstants.FTP_SUBMIT_COLLECTION_SUMMARY_REPORT_Q, submitCollectionReportRecordList, "fileName", generatedFileName);


        return generatedFileName;
    }
}
