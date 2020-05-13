package org.recap.repository;

import org.recap.model.jpa.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hemalathas on 22/6/16.
 */
public interface InstitutionDetailsRepository extends JpaRepository<InstitutionEntity,Integer> {

    /**
     * Gets institution entity for the given institution id.
     *
     * @param institutionId the institution id
     * @return the institution entity
     */
    InstitutionEntity findByInstitutionId(Integer institutionId);

    /**
     * Gets institution entity for the given institution code.
     *
     * @param institutionCode the institution code
     * @return the institution entity
     */
    InstitutionEntity findByInstitutionCode(String institutionCode);

    /**
     * Gets institution entity for the given institution name.
     *
     * @param institutionName the institution name
     * @return the institution entity
     */
    InstitutionEntity findByInstitutionName(String institutionName);
}
