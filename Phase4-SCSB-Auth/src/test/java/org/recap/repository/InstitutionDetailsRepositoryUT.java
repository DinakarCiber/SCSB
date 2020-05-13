package org.recap.repository;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.jpa.InstitutionEntity;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by pvsubrah on 6/22/16.
 */
public class InstitutionDetailsRepositoryUT extends BaseTestCase {

    @Autowired
    InstitutionDetailsRepository institutionDetailsRepository;

    @Test
    public void saveAndFind() throws Exception {
        assertNotNull(institutionDetailsRepository);

        InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setInstitutionCode("OXF");
        institutionEntity.setInstitutionName("Oxford");

        InstitutionEntity savedInstitutionEntity = institutionDetailsRepository.save(institutionEntity);
        assertNotNull(savedInstitutionEntity);
        assertNotNull(savedInstitutionEntity.getInstitutionId());
        assertEquals(savedInstitutionEntity.getInstitutionCode(), "OXF");
        assertEquals(savedInstitutionEntity.getInstitutionName(), "Oxford");

        InstitutionEntity byInstitutionCode = institutionDetailsRepository.findByInstitutionCode("OXF");
        assertNotNull(byInstitutionCode);
        assertEquals("OXF",byInstitutionCode.getInstitutionCode());
        assertEquals("Oxford",byInstitutionCode.getInstitutionName());

        InstitutionEntity byInstitutionName = institutionDetailsRepository.findByInstitutionName("Oxford");
        assertNotNull(byInstitutionName);
    }

}