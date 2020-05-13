package org.recap.repository.jpa;

import org.recap.model.jpa.CustomerCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by rajeshbabuk on 18/10/16.
 */
@RepositoryRestResource(collectionResourceRel = "customerCode", path = "customerCode")
public interface CustomerCodeDetailsRepository extends JpaRepository<CustomerCodeEntity, Integer> {

    /**
     * Find the customer code entity by using the customer code.
     *
     * @param customerCode the customer code
     * @return the customer code entity
     */
    CustomerCodeEntity findByCustomerCode(@Param("customerCode")String customerCode);

    /**
     * Find a list of customer code entities by using a list of customer code.
     *
     * @param customerCodes the customer codes
     * @return the list
     */
    List<CustomerCodeEntity> findByCustomerCodeIn(List<String> customerCodes);
}
