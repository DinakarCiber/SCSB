package org.recap.model.jpa;

import org.junit.Test;
import org.recap.BaseTestCase;

import static org.junit.Assert.*;

/**
 * Created by hemalathas on 20/4/17.
 */
public class MatchingBibInfoDetailUT extends BaseTestCase{

    @Test
    public void testMatchingBibInfoDetail(){
        MatchingBibInfoDetail matchingBibInfoDetail = new MatchingBibInfoDetail();
        matchingBibInfoDetail.setMatchingBibInfoDetailId(1);
        matchingBibInfoDetail.setBibId("1");
        matchingBibInfoDetail.setOwningInstitutionBibId("565658565465");
        matchingBibInfoDetail.setOwningInstitution("PUL");
        matchingBibInfoDetail.setRecordNum(10);
        assertNotNull(matchingBibInfoDetail.getMatchingBibInfoDetailId());
        assertNotNull(matchingBibInfoDetail.getBibId());
        assertNotNull(matchingBibInfoDetail.getOwningInstitutionBibId());
        assertNotNull(matchingBibInfoDetail.getOwningInstitution());
        assertNotNull(matchingBibInfoDetail.getRecordNum());
    }

}