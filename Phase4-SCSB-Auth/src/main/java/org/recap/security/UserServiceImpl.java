package org.recap.security;

import org.recap.model.UserForm;
import org.recap.model.jpa.PermissionEntity;
import org.recap.repository.InstitutionDetailsRepository;
import org.recap.repository.PermissionsRepository;
import org.recap.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dharmendrag on 29/11/16.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private InstitutionDetailsRepository institutionDetailsRepository;

    @Autowired
    private PermissionsRepository permissionsRepository;


    /**
     * Get the UserForm based on the loginId.
     *
     * @param loginId  the login id
     * @param userForm the user form
     * @return the user form
     * @throws Exception the exception
     */
    public UserForm findUser(String loginId,UserForm userForm)throws Exception
    {
        return UserManagementService.toUserForm(userDetailsRepository.findByLoginId(loginId),userForm);
    }


    @Override
    public Map<Integer,String> getPermissions()
    {
        Map<Integer,String> permissionsMap=new HashMap<>();
        List<PermissionEntity> permissions=permissionsRepository.findAll();

        for(PermissionEntity permissionEntity:permissions)
        {
            permissionsMap.put(permissionEntity.getPermissionId(),permissionEntity.getPermissionName());
        }

        return permissionsMap;
    }






}
