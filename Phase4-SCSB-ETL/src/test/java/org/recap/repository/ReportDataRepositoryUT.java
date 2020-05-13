package org.recap.repository;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.RecapConstants;
import org.recap.model.jpa.ReportDataEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by premkb on 25/1/17.
 */
public class ReportDataRepositoryUT extends BaseTestCase {

    @Autowired
    private ReportDataRepository reportDataRepository;

    @Test
    public void getReportDataForMatchingInstitutionBib(){
        saveReportDataEntity();
        List<String> recordNumList = new ArrayList<>();
        recordNumList.add("50");
        List<ReportDataEntity> reportDataEntityList = reportDataRepository.getReportDataForMatchingInstitutionBib(recordNumList,getHeaderNameList());
        assertNotNull(reportDataEntityList);
    }

    private void saveReportDataEntity(){
        ReportDataEntity reportDataEntity = new ReportDataEntity();
        reportDataEntity.setReportDataId(100);
        reportDataEntity.setHeaderName(RecapConstants.BIB_ID);
        reportDataEntity.setHeaderValue("10,20");
        reportDataEntity.setRecordNum("50");
        reportDataRepository.saveAndFlush(reportDataEntity);
    }

    private List<String> getHeaderNameList() {
        List<String> headerNameList = new ArrayList<>();
        headerNameList.add(RecapConstants.BIB_ID);
        headerNameList.add(RecapConstants.OWNING_INSTITUTION);
        headerNameList.add(RecapConstants.OWNING_INSTITUTION_BIB_ID);
        return headerNameList;
    }
}
