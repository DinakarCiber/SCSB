package org.recap.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.support.DefaultWebSubjectContext;
import org.recap.RecapConstants;
import org.recap.model.LoginValidator;
import org.recap.model.UserForm;
import org.recap.model.jpa.InstitutionEntity;
import org.recap.repository.InstitutionDetailsRepository;
import org.recap.repository.UserDetailsRepository;
import org.recap.security.AuthorizationServiceImpl;
import org.recap.security.UserManagementService;
import org.recap.security.UserService;
import org.recap.util.HelperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.*;


/**
 * Created by dharmendrag on 25/11/16.
 */
@RestController
@RequestMapping(value = "/userAuth")
@Api(value = "userAuth")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Value("${recap.assist.email.to}")
    private String recapAssistanceEmailTo;

    @Value("${superadmin.permission.institution}")
    private String superAdminPermissionForInstitution;

    @Autowired
    private InstitutionDetailsRepository institutionDetailsRepository;

    private LoginValidator loginValidator = new LoginValidator();

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationServiceImpl authorizationService;

    @Autowired
    private DefaultWebSubjectContext defaultWebSubjectContext;

    /**
     * The User details repository.
     */
    @Autowired
    UserDetailsRepository userDetailsRepository;

    /**
     * The User management service.
     */
    @Autowired
    UserManagementService userManagementService;

    /**
     * The Helper util.
     */
    @Autowired
    HelperUtil helperUtil;

    /**
     * Create session for the requested user
     *
     * @param token   the token
     * @param request the request
     * @param error   the error
     * @return the map of the authorization values
     */
    @RequestMapping(value = "/authService", method = RequestMethod.POST)
    @ApiOperation(value = "authService", notes = "Used to Authenticate User", consumes = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Session created successfully")})
    public Map<String, Object> createSession(@RequestBody UsernamePasswordToken token, HttpServletRequest request, BindingResult error) {
        UserForm userForm = new UserForm();
        Map<String, Object> authMap = new HashMap<>();
        try {
            if (token == null) {
                throw new CredentialsException(RecapConstants.ERROR_USER_TOKEN_EMPTY);
            }
            String[] values = UserManagementService.userAndInstitution(token.getUsername());
            boolean isValid = false;
            if (values != null) {
                userForm.setUsername(values[0]);
                InstitutionEntity institutionEntity = helperUtil.getInstitutionIdByCode(values[1]);
                userForm.setInstitution(institutionEntity.getInstitutionId());
                userForm.setUserInstitution(institutionEntity.getInstitutionCode());
                isValid = loginValidator.validate(userForm);
            }
            if (!isValid) {
                throw new IncorrectCredentialsException(RecapConstants.ERROR_USER_TOKEN_VALIDATION_FAILED);
            }
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            if (!subject.isAuthenticated()) {
                throw new AuthenticationException(RecapConstants.ERROR_SUBJECT_AUTHENTICATION_FAILED);
            }
            authorizationService.setSubject(token, subject);
            Map<Integer, String> permissionMap = userService.getPermissions();
            getPermissionsForUI(subject, authMap, permissionMap);
            authMap.put(RecapConstants.USER_AUTHENTICATION, true);
            authMap.put(RecapConstants.USER_NAME, userForm.getUsername());
            authMap.put(RecapConstants.USER_INSTITUTION, userForm.getInstitution());
            authMap.put(RecapConstants.USER_ID, subject.getPrincipal());
            List<Integer> roleId = userManagementService.getRolesForUser((Integer) subject.getPrincipal());
            boolean superAdminUser = (roleId.contains(1) && superAdminPermissionForInstitution.contains(userForm.getUserInstitution())) ? Boolean.TRUE : Boolean.FALSE;
            boolean recapUser = subject.isPermitted(permissionMap.get(userManagementService.getPermissionId(RecapConstants.BARCODE_RESTRICTED)));
            authMap.put(RecapConstants.SUPER_ADMIN_USER, superAdminUser);
            authMap.put(RecapConstants.RECAP_USER, recapUser);
            Collections.unmodifiableMap(authMap);
            Session session = subject.getSession();
            session.setAttribute(RecapConstants.PERMISSION_MAP, permissionMap);
            session.setAttribute(RecapConstants.USER_ID, subject.getPrincipal());
        } catch (UnknownAccountException uae) {
            logger.debug("Unknown Account Exception");
            logger.error(RecapConstants.EXCEPTION_IN_AUTHENTICATION, uae);
            authMap.put(RecapConstants.USER_AUTHENTICATION, false);
            authMap.put(RecapConstants.USER_AUTH_ERRORMSG, MessageFormat.format(RecapConstants.ERROR_MESSAGE_USER_NOT_AVAILABLE, recapAssistanceEmailTo, recapAssistanceEmailTo));
        } catch (IncorrectCredentialsException ice) {
            logger.debug("Unknown Account Exception");
            logger.error(RecapConstants.EXCEPTION_IN_AUTHENTICATION, ice);
            authMap.put(RecapConstants.USER_AUTHENTICATION, false);
            authMap.put(RecapConstants.USER_AUTH_ERRORMSG, RecapConstants.ERROR_AUTHENTICATION_FAILED);
        } catch (CredentialsException ce) {
            logger.debug("Credentials exception");
            logger.error(RecapConstants.EXCEPTION_IN_AUTHENTICATION, ce);
            authMap.put(RecapConstants.USER_AUTHENTICATION, false);
            authMap.put(RecapConstants.USER_AUTH_ERRORMSG, RecapConstants.ERROR_AUTHENTICATION_FAILED);
        } catch (AuthenticationException ae) {
            logger.debug("Authentication exception");
            logger.error(RecapConstants.EXCEPTION_IN_AUTHENTICATION, ae);
            authMap.put(RecapConstants.USER_AUTHENTICATION, false);
            authMap.put(RecapConstants.USER_AUTH_ERRORMSG, RecapConstants.ERROR_AUTHENTICATION_FAILED);
        } catch (Exception e) {
            logger.error(RecapConstants.EXCEPTION_IN_AUTHENTICATION, e);
            authMap.put(RecapConstants.USER_AUTHENTICATION, false);
            authMap.put(RecapConstants.USER_AUTH_ERRORMSG, e.getMessage());
        }
        return authMap;
    }

    /**
     * Logout user from the Shiro.
     *
     * @param token the token
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logoutUser(@RequestBody UsernamePasswordToken token) {
        logger.info("Subject Logged out");
        authorizationService.unAuthorized(token);
    }


    private void getPermissionsForUI(Subject subject,Map<String,Object> authMap,Map<Integer,String> permissionMap){
        authMap.put(RecapConstants.REQUEST_PRIVILEGE,subject.isPermitted(permissionMap.get(userManagementService.getPermissionId(RecapConstants.REQUEST_PLACE))));
        authMap.put(RecapConstants.COLLECTION_PRIVILEGE,subject.isPermitted(permissionMap.get(userManagementService.getPermissionId(RecapConstants.WRITE_GCD))));
        authMap.put(RecapConstants.REPORTS_PRIVILEGE,subject.isPermitted(permissionMap.get(userManagementService.getPermissionId(RecapConstants.VIEW_PRINT_REPORTS))));
        authMap.put(RecapConstants.SEARCH_PRIVILEGE,subject.isPermitted(permissionMap.get(userManagementService.getPermissionId(RecapConstants.SCSB_SEARCH_EXPORT))));
        authMap.put(RecapConstants.USER_ROLE_PRIVILEGE,subject.isPermitted(permissionMap.get(userManagementService.getPermissionId(RecapConstants.CREATE_USER))));
        authMap.put(RecapConstants.REQUEST_ALL_PRIVILEGE,subject.isPermitted(permissionMap.get(userManagementService.getPermissionId(RecapConstants.REQUEST_PLACE_ALL))));
        authMap.put(RecapConstants.REQUEST_ITEM_PRIVILEGE,subject.isPermitted(permissionMap.get(userManagementService.getPermissionId(RecapConstants.REQUEST_ITEMS))));
        authMap.put(RecapConstants.BARCODE_RESTRICTED_PRIVILEGE,subject.isPermitted(permissionMap.get(userManagementService.getPermissionId(RecapConstants.BARCODE_RESTRICTED))));
        authMap.put(RecapConstants.DEACCESSION_PRIVILEGE,subject.isPermitted(permissionMap.get(userManagementService.getPermissionId(RecapConstants.DEACCESSION))));
        authMap.put(RecapConstants.BULK_REQUEST_PRIVILEGE,subject.isPermitted(permissionMap.get(userManagementService.getPermissionId(RecapConstants.BULK_REQUEST))));
        authMap.put(RecapConstants.RESUBMIT_REQUEST_PRIVILEGE,subject.isPermitted(permissionMap.get(userManagementService.getPermissionId(RecapConstants.RESUBMIT_REQUEST))));
    }

}
