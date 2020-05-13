package org.recap;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.support.DefaultWebSubjectContext;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by peris on 1/5/17.
 */
public class ShiroTest extends BaseTestCase {

    @Test
    public void loginSingleUser() throws Exception {

        DefaultWebSubjectContext webSubjectContext = new DefaultWebSubjectContext();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("john:CUL", "123");
        webSubjectContext.setAuthenticationToken(usernamePasswordToken);
        Subject subject = securityManager.createSubject(webSubjectContext);
        assertNotNull(subject);


        Subject loggedInSubject = securityManager.login(subject, usernamePasswordToken);
        boolean loggedInSubjectAuthenticated = loggedInSubject.isAuthenticated();
        assertNotNull(loggedInSubject);
        assertTrue(loggedInSubjectAuthenticated);
        boolean permitted = loggedInSubject.isPermitted("RequestPlace");
        loggedInSubject.getSession().setTimeout(1000);

        Thread.sleep(2000);

        try {
            loggedInSubject.isPermitted("RequestPlace");
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginConcurrentUser() throws Exception {
        DefaultWebSubjectContext webSubjectContext = new DefaultWebSubjectContext();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("john:CUL", "123");
        webSubjectContext.setAuthenticationToken(usernamePasswordToken);
        Subject subject = securityManager.createSubject(webSubjectContext);
        assertNotNull(subject);

//        DefaultWebSubjectContext webSubjectContext = new DefaultWebSubjectContext();
        UsernamePasswordToken usernamePasswordToken1 = new UsernamePasswordToken("danie:CUL", "123");
        webSubjectContext.setAuthenticationToken(usernamePasswordToken1);
        Subject subject1 = securityManager.createSubject(webSubjectContext);
        assertNotNull(subject1);


        Subject loggedInSubject1 = securityManager.login(subject, usernamePasswordToken);
        assertNotNull(loggedInSubject1);
        Subject loggedInSubject2 = securityManager.login(subject1, usernamePasswordToken1);
        assertNotNull(loggedInSubject2);

        boolean loggedInSubject1Authenticated = loggedInSubject1.isAuthenticated();
        assertTrue(loggedInSubject1Authenticated);


        boolean loggedInSubject2Authenticated = loggedInSubject2.isAuthenticated();
        assertTrue(loggedInSubject2Authenticated);


    }
}
