package org.recap.security;

import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.recap.RecapConstants;
import org.recap.model.UserForm;
import org.recap.model.jpa.PermissionEntity;
import org.recap.model.jpa.RoleEntity;
import org.recap.model.jpa.UsersEntity;
import org.recap.repository.PermissionsRepository;
import org.recap.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by akulak on 27/2/17.
 */
@Service
public class UserManagementService {

    /**
     * The User details repository.
     */
    @Autowired
    UserDetailsRepository userDetailsRepository;

    /**
     * The Permissions repository.
     */
    @Autowired
    PermissionsRepository permissionsRepository;

    /**
     * Get roles for user Id.
     *
     * @param userId the user id
     * @return the list of role ids
     */
    public List<Integer> getRolesForUser(Integer userId){
        List<Integer> roleIds = new ArrayList<>();
        UsersEntity usersEntity = userDetailsRepository.findByUserId(userId);
        List<RoleEntity> userRole = usersEntity.getUserRole();
        for (RoleEntity roleEntity : userRole) {
            Integer roleId = roleEntity.getRoleId();
            roleIds.add(roleId);
        }
        return roleIds;
    }

    /**
     * Get permission id integer.
     *
     * @param permissionName the permission name
     * @return the permission id
     */
    public Integer getPermissionId(String permissionName){
        PermissionEntity permissionEntity = permissionsRepository.findByPermissionName(permissionName);
        return  permissionEntity.getPermissionId();
    }

    /**
     * Split the User and institution from the token.
     *
     * @param token the token
     * @return the array with the value of user and institution.
     */
    public static final String[] userAndInstitution(String token)
    {
        String[] values=new String[2];
        if(token.contains(RecapConstants.TOKEN_SPLITER))
        {
            values=token.split(RecapConstants.TOKEN_SPLITER);
        }else
        {
            return null;
        }
        return values;
    }


    /**
     * Create UserForm with details.
     *
     * @param userEntity the user entity
     * @param userForm   the user form
     * @return the UserForm
     */
    public static UserForm toUserForm(UsersEntity userEntity, UserForm userForm) {
        if (userEntity == null) {
            throw new UnknownAccountException(RecapConstants.ERROR_USER_NOT_AVAILABLE);
        }
        if (userForm == null) {
            userForm = new UserForm();
        }
        userForm.setUserId(userEntity.getUserId());
        userForm.setUsername(userEntity.getLoginId());
        userForm.setInstitution(userEntity.getInstitutionEntity().getInstitutionId());
        userForm.setUserInstitution(userEntity.getInstitutionEntity().getInstitutionCode());
        return userForm;
    }

    /**
     * Get permissions details
     *
     * @param subject the subject
     * @return the map of permission values
     */
    public static Map<Integer,String> getPermissions(Subject subject){
        Session session=subject.getSession();
        return (Map<Integer,String>)session.getAttribute(RecapConstants.PERMISSION_MAP);
    }


}
