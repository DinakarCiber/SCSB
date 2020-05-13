package org.recap.security;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.Subject;
import org.recap.RecapConstants;
import org.recap.model.jpa.PermissionEntity;
import org.recap.model.jpa.RoleEntity;
import org.recap.model.jpa.UsersEntity;
import org.recap.repository.UserDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dharmendrag on 21/12/16.
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    /**
     * The User management service.
     */
    @Autowired
    UserManagementService userManagementService;

    private static Map<String, Subject> tokenMap = new ConcurrentHashMap<>();

    /**
     * Get the subject for the giving usernamePasswordToken.
     *
     * @param usernamePasswordToken the username password token
     * @return the subject
     */
    public Subject getSubject(UsernamePasswordToken usernamePasswordToken) {
        return tokenMap.get(usernamePasswordToken.getUsername());
    }

    /**
     * Sets subject.
     *
     * @param usernamePasswordToken the username password token
     * @param subject               the subject
     */
    public void setSubject(UsernamePasswordToken usernamePasswordToken, Subject subject) {

        tokenMap.put(usernamePasswordToken.getUsername(), subject);
    }

    @Override
    public AuthorizationInfo doAuthorizationInfo(SimpleAuthorizationInfo authorizationInfo, Integer loginId) {
        UsersEntity usersEntity = userDetailsRepository.findByUserId(loginId);
        if (usersEntity == null) {
            return null;
        } else {
            for (RoleEntity role : usersEntity.getUserRole()) {
                authorizationInfo.addRole(role.getRoleName());
                for (PermissionEntity permissionEntity : role.getPermissions()) {
                    authorizationInfo.addStringPermission(permissionEntity.getPermissionName());
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * Un authorized the given user.
     *
     * @param token the token
     */
    public void unAuthorized(UsernamePasswordToken token) {
        logger.debug("Session Time Out Call");
        Subject currentSubject = getSubject(token);
        tokenMap.remove(token.getUsername());
        if (currentSubject != null && currentSubject.getSession() != null) {
            currentSubject.logout();
        }
    }

    /**
     * Check privilege of the user based on the permission id.
     *
     * @param token        the token
     * @param permissionId the permission id
     * @return the boolean
     */
    public boolean checkPrivilege(UsernamePasswordToken token, Integer permissionId) {
        Subject currentSubject = getSubject(token);
        logger.debug("Authorization call for : {} & User {}",permissionId,token);
        Map<Integer, String> permissions = UserManagementService.getPermissions(currentSubject);
        boolean authorized = false;
        try {
            currentSubject.getSession().touch();
            switch(permissionId){

                case RecapConstants.EDIT_CGD_ID:{//to check Edit CGD & Deaccession
                    if (currentSubject.isPermitted(permissions.get(userManagementService.getPermissionId(RecapConstants.WRITE_GCD))) || currentSubject.isPermitted(permissions.get(userManagementService.getPermissionId(RecapConstants.DEACCESSION)))) {
                        authorized=true;
                    }
                    break;
                }

                case RecapConstants.REQUEST_PLACE_ID:{//to check Request
                    if (currentSubject.isPermitted(permissions.get(userManagementService.getPermissionId(RecapConstants.REQUEST_PLACE))) || currentSubject.isPermitted(permissions.get(userManagementService.getPermissionId(RecapConstants.REQUEST_PLACE_ALL))) ||
                            currentSubject.isPermitted(permissions.get(userManagementService.getPermissionId(RecapConstants.REQUEST_ITEMS)))) {
                        authorized=true;
                    }
                    break;
                }

                default:{
                    authorized = currentSubject.isPermitted(permissions.get(permissionId));
                    break;
                }

            }

            if (!authorized) {
                unAuthorized(token);
            }
        } catch (Exception sessionExcp) {
            logger.error("Exception in AuthorizationServiceImpl ",sessionExcp);
            logger.error("Exception in AuthorizationServiceImpl ",sessionExcp.getMessage());
            timeOutExceptionCatch(token);
        }

        return authorized;
    }

    private void timeOutExceptionCatch(UsernamePasswordToken token) {
        logger.debug("Time out Exception thrown for token ",token);
        unAuthorized(token);
    }

}
