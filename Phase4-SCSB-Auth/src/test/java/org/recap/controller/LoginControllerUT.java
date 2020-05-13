package org.recap.controller;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Test;
import org.mockito.Mock;
import org.recap.BaseTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by dharmendrag on 6/2/17.
 */
public class LoginControllerUT extends BaseTestCase {

    @Autowired
    LoginController loginController;

    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    BindingResult bindingResult;

    @Test
    public void testCreateSession(){
        String loginUser="superadmin:PUL";
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginUser, "123");
        Map<String,Object> map = loginController.createSession(usernamePasswordToken,httpServletRequest,bindingResult);
        assertNotNull(map);
    }

}
