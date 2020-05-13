package org.recap.config;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.subject.support.DefaultWebSubjectContext;
import org.recap.security.realm.SimpleAuthorizationRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dharmendrag on 25/11/16.
 */
@Configuration
@ControllerAdvice
public class ApacheShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ApacheShiroConfig.class);

    @Value("${users.session.timeout}")
    private String sessionTimeOut;//in milliseconds


    /**
     * Handling the exception for the Swagger .
     *
     * @param e     the e
     * @param model the model
     * @return the string
     */
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleException(AuthorizationException e, Model model) {

        // you could return a 404 here instead (this is how github handles 403, so the user does NOT know there is a
        // resource at that location)

        Map<String, Object> map = new HashMap<>();
        map.put("status", HttpStatus.FORBIDDEN.value());
        map.put("message", "No message available");
        model.addAttribute("errors", map);

        return "error";
    }

    /**
     * Register ModularRealmAuthenticator.
     *
     * @return the ModularRealmAuthenticator instance
     */
    @Bean
    public ModularRealmAuthenticator authenticator() {
        return new ModularRealmAuthenticator();
    }

    /**
     * Register ModularRealmAuthorizer
     *
     * @return the ModularRealmAuthorizer instance
     */
    @Bean
    public ModularRealmAuthorizer authorizer() {
        return new ModularRealmAuthorizer();
    }

    /**
     * Register DefaultWebSessionManager and set the session timeout
     *
     * @return the DefaultWebSessionManager instance
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(Long.valueOf(sessionTimeOut));
        return sessionManager;
    }


    /**
     * Register SecurityManager
     *
     * @return the instance of SecurityManager
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        SecurityManager securityManager = new DefaultWebSecurityManager(new SimpleAuthorizationRealm());
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    /**
     * Register DefaultWebSubjectContext
     *
     * @return the instance of DefaultWebSubjectContext
     */
    @Bean(name="subjectContext")
    public DefaultWebSubjectContext getSubjectContext(){
        return new DefaultWebSubjectContext();
    }


    /**
     * Register ShiroFilterChainDefinitions and set the filter path mappings
     *
     * @return the instance of ShiroFilterChainDefinition
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        Map<String, String> filterChainsMap = new HashMap<>();
        filterChainsMap.put("/", "authc");
        filterChainsMap.put("/logout", "logout");
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinitions(filterChainsMap);
        return chainDefinition;
    }

    /**
     * Register ShiroFilterFactoryBean and set the urls
     *
     * @param securityManager the security manager
     * @return the instance of ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/");
        shiroFilterFactoryBean.setSuccessUrl("/search");
        shiroFilterFactoryBean.setUnauthorizedUrl("/");
        return shiroFilterFactoryBean;
    }


    /**
     * Register Subject
     *
     * @return the instance of Subject
     */
    @ModelAttribute(name = "subject")
    public Subject subject() {
        Subject subject=null;
        try{
            subject=SecurityUtils.getSubject();
        }catch(Exception e){
            logger.error("error-->",e);
        }
        return subject;
    }


}
