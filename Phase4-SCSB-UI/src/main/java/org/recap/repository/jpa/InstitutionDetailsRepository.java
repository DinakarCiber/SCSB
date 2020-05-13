package org.recap.repository.jpa;

import org.recap.model.jpa.InstitutionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by hemalathas on 22/6/16.
 */
public interface InstitutionDetailsRepository extends PagingAndSortingRepository<InstitutionEntity,Integer> {
    /**
     * To get the institution entity for the given institution id.
     *
     * @param institutionId the institution id
     * @return the institution entity
     */
    InstitutionEntity findByInstitutionId(Integer institutionId);

    /**
     *To get the institution entity for the given institution code.
     *
     * @param institutionCode the institution code
     * @return the institution entity
     */
    InstitutionEntity findByInstitutionCode(String institutionCode);

    /**
     *To get the institution entity for the given institution name.
     *
     * @param institutionName the institution name
     * @return the institution entity
     */
    InstitutionEntity findByInstitutionName(String institutionName);

    /**
     * To get the list of institution entities for the super admin role.
     *
     * @return the institution code for super admin
     */
    @Query(value="select inst from InstitutionEntity inst where inst.institutionCode not in ('HTC')")
    List<InstitutionEntity> getInstitutionCodeForSuperAdmin();
}
