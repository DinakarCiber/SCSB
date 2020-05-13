package org.recap.repository;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.jpa.InstitutionEntity;
import org.recap.model.jpa.RoleEntity;
import org.recap.model.jpa.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by dharmendrag on 17/1/17.
 */
public class UserDetailsRepositoryUT extends BaseTestCase {

    @Autowired
    public UserDetailsRepository userRepo;

    @Autowired
    public InstitutionDetailsRepository institutionDetailsRepository;

    @Autowired
    RolesDetailsRepositorty rolesDetailsRepositorty;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void createUser(){
        UsersEntity usersEntity=new UsersEntity();
        usersEntity.setLoginId("julius");
        usersEntity.setEmailId("julius@example.org");
        usersEntity.setUserDescription("test User");
        usersEntity.setInstitutionId(1);
        usersEntity.setCreatedBy("superadmin");
        usersEntity.setCreatedDate(new Date());
        usersEntity.setLastUpdatedBy("superadmin");
        usersEntity.setLastUpdatedDate(new Date());
        userRoles(usersEntity);

        UsersEntity savedUser=userRepo.saveAndFlush(usersEntity);
        entityManager.refresh(savedUser);

        assertEquals(usersEntity.getLoginId(),savedUser.getLoginId());

        UsersEntity byLoginId=userRepo.findByLoginId("julius");
        assertEquals(usersEntity.getLoginId(),byLoginId.getLoginId());
        assertEquals(usersEntity.getUserDescription(),byLoginId.getUserDescription());
        assertEquals(usersEntity.getEmailId(),byLoginId.getEmailId());
        assertEquals(usersEntity.getCreatedBy(),byLoginId.getCreatedBy());
        assertEquals(usersEntity.getCreatedDate(),byLoginId.getCreatedDate());
        assertEquals(usersEntity.getLastUpdatedDate(),byLoginId.getLastUpdatedDate());
        assertEquals(usersEntity.getLastUpdatedBy(),byLoginId.getLastUpdatedBy());
        assertEquals(usersEntity.getUserRole().get(0),byLoginId.getUserRole().get(0));

    }

    private void userRoles(UsersEntity usersEntity){
        List<RoleEntity> roleList=new ArrayList<RoleEntity>();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("patron");
        roleEntity.setRoleDescription("patron from CUL");
        roleEntity.setCreatedDate(new Date());
        roleEntity.setCreatedBy("superadmin");
        roleEntity.setLastUpdatedDate(new Date());
        roleEntity.setLastUpdatedBy("superadmin");
        RoleEntity savedRole=rolesDetailsRepositorty.saveAndFlush(roleEntity);
        entityManager.refresh(savedRole);
        roleList.add(savedRole);
        usersEntity.setUserRole(roleList);

    }


}
