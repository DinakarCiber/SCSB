package org.recap.controller;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.support.DefaultWebSubjectContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.recap.BaseTestCase;
import org.recap.RecapConstants;
import org.recap.config.ApacheShiroConfig;
import org.recap.security.AuthorizationServiceImpl;
import org.recap.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by dharmendrag on 6/2/17.
 */
public class AuthorizationControllerUT extends BaseTestCase {

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationServiceImpl authorizationService;

    @Autowired
    LoginController loginController;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpSession session;

    @Mock
    ApacheShiroConfig apacheShiroConfig;

    @Autowired
    AuthorizationController authorizationController;

    UsernamePasswordToken usernamePasswordToken=null;

    Map<Integer,String> permissionMap=null;

    @Before
    public void setUp(){
        permissionMap= userService.getPermissions();
        DefaultWebSubjectContext webSubjectContext = new DefaultWebSubjectContext();
        usernamePasswordToken = new UsernamePasswordToken("john:CUL", "123");
        webSubjectContext.setAuthenticationToken(usernamePasswordToken);
        Subject subject = securityManager.createSubject(webSubjectContext);
        assertNotNull(subject);
        Subject loggedInSubject = securityManager.login(subject, usernamePasswordToken);
        authorizationService.setSubject(usernamePasswordToken,loggedInSubject);
        Session session=loggedInSubject.getSession();
        session.setAttribute(RecapConstants.PERMISSION_MAP,permissionMap);
    }


    @Test
    public void checkSearchPermission(){
        boolean result=authorizationController.searchRecords(request,usernamePasswordToken);
        assertTrue(result);
    }

    @Test
    public void checkRequestPermission(){
        boolean result=false;
        result=authorizationController.request(usernamePasswordToken);
        assertTrue(result);
    }

    @Test
    public void checkCollectionPermission(){
        boolean result=false;
        result=authorizationController.collection(usernamePasswordToken);
        assertTrue(result);
    }

    @Test
    public void checkReportsPermission(){
        boolean result=false;
        result=authorizationController.reports(usernamePasswordToken);
        assertTrue(result);
    }

    @Test
    public void checkUsersPermission(){
        boolean result=false;
        result=authorizationController.userRoles(usernamePasswordToken);
        assertTrue(result);
    }

}
