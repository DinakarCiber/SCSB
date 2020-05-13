package org.recap.repository;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.jpa.PermissionEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by dharmendrag on 1/2/17.
 */
public class PermissionsRepositoryUT extends BaseTestCase {


    @Autowired
    PermissionsRepository permissionsRepository;

    @PersistenceContext
    EntityManager entityManager;



    @Test
    public void createPermission() {

        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setPermissionDesc("Permission to edit user");
        permissionEntity.setPermissionName("EditUser");
        PermissionEntity savedPermission = permissionsRepository.saveAndFlush(permissionEntity);
        entityManager.refresh(savedPermission);

        assertNotNull(savedPermission);
        assertEquals(permissionEntity.getPermissionName(),savedPermission.getPermissionName());
        assertEquals(permissionEntity.getPermissionDesc(),savedPermission.getPermissionDesc());

        List<PermissionEntity> permissionEntities=permissionsRepository.findAll();
        permissionEntities.contains(savedPermission);

    }

}
