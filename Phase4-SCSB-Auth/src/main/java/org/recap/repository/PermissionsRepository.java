package org.recap.repository;

import org.recap.model.jpa.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dharmendrag on 13/12/16.
 */
public interface PermissionsRepository extends JpaRepository<PermissionEntity,Integer> {

    /**
     * Gets permission entity for the given permission name.
     *
     * @param permissionName the permission name
     * @return the permission entity
     */
    PermissionEntity findByPermissionName(String permissionName);


}
