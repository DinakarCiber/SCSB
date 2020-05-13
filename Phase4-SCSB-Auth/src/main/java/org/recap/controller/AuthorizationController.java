package org.recap.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.recap.RecapConstants;
import org.recap.security.AuthorizationServiceImpl;
import org.recap.security.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dharmendrag on 10/1/17.
 */
@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    @Autowired
    private AuthorizationServiceImpl authorizationService;

    /**
     * The User management service.
     */
    @Autowired
    UserManagementService userManagementService;


    /**
     * Check the privilege for the search record screen
     *
     * @param request the request
     * @param token   the token
     * @return the boolean
     */
    @RequestMapping(value="/search",method= RequestMethod.POST)
    @ApiOperation(value="search authentication",notes="Used to Authenticate User",consumes = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "search authentication success")})
    public boolean searchRecords(HttpServletRequest request, @RequestBody UsernamePasswordToken token) {
        return authorizationService.checkPrivilege(token, userManagementService.getPermissionId(RecapConstants.SCSB_SEARCH_EXPORT));
    }

    /**
     * Check the privilege for the request screen
     *
     * @param token the token
     * @return the boolean
     */
    @RequestMapping(value="/request",method= RequestMethod.POST)
    @ApiOperation(value="request authentication",notes="Used to Authenticate User",consumes = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "request authentication success")})
    public Boolean request(@RequestBody UsernamePasswordToken token) {
        return authorizationService.checkPrivilege(token,userManagementService.getPermissionId(RecapConstants.REQUEST_PLACE));
    }

    /**
     * Check the privilege for the collection screen
     *
     * @param token the token
     * @return the boolean
     */
    @RequestMapping(value = "/collection", method = RequestMethod.POST)
    @ApiOperation(value="collection authentication",notes="Used to Authenticate User",consumes = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "collection authentication success")})
    public Boolean collection(@RequestBody UsernamePasswordToken token) {
        return authorizationService.checkPrivilege(token, userManagementService.getPermissionId(RecapConstants.WRITE_GCD));

    }

    /**
     * Check the privilege for the report screen
     *
     * @param usernamePasswordToken the username password token
     * @return the boolean
     */
    @RequestMapping(value="/reports",method= RequestMethod.POST)
    @ApiOperation(value="reports authentication",notes="Used to Authenticate User",consumes = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "reports authentication success")})
    public boolean reports(@RequestBody UsernamePasswordToken usernamePasswordToken) {
        return authorizationService.checkPrivilege(usernamePasswordToken,userManagementService.getPermissionId(RecapConstants.VIEW_PRINT_REPORTS));

    }

    /**
     * Check the privilege for the user screen
     *
     * @param usernamePasswordToken the username password token
     * @return the boolean
     */
    @RequestMapping(value="/userRoles",method= RequestMethod.POST)
    @ApiOperation(value="user authentication",notes="Used to Authorizer User for Users",consumes = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User & Role authentication success")})
    public boolean userRoles(@RequestBody UsernamePasswordToken usernamePasswordToken) {
        return authorizationService.checkPrivilege(usernamePasswordToken,userManagementService.getPermissionId(RecapConstants.CREATE_USER));

    }

    /**
     * Check the privilege for the roles screen
     *
     * @param usernamePasswordToken the username password token
     * @return the boolean
     */
    @RequestMapping(value="/roles",method= RequestMethod.POST)
    @ApiOperation(value="roles authentication",notes="Used to Authorizer User for Roles",consumes = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Role authentication success")})
    public boolean roles(@RequestBody UsernamePasswordToken usernamePasswordToken) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        List<Integer> roleId = userManagementService.getRolesForUser((Integer) subject.getPrincipal());
        return roleId.contains(1);
    }

    /**
     * Check the privilege for the roles screen
     *
     * @param usernamePasswordToken the username password token
     * @return the boolean
     */
    @RequestMapping(value="/touchExistingSession",method= RequestMethod.POST)
    @ApiOperation(value="touch existing session",notes="Used to touch existing session for the user",consumes = "application/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully extended the session")})
    public boolean touchExistingSession(@RequestBody UsernamePasswordToken usernamePasswordToken) {
        Subject subject = authorizationService.getSubject(usernamePasswordToken);
        try {
            subject.getSession().touch();
            return true;
        } catch (InvalidSessionException e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping(value="/bulkRequest",method= RequestMethod.POST)
    public boolean bulkRequest(@RequestBody UsernamePasswordToken usernamePasswordToken) {
        return authorizationService.checkPrivilege(usernamePasswordToken,userManagementService.getPermissionId(RecapConstants.BULK_REQUEST));

    }
}
