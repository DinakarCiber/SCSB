package org.recap;


import org.apache.shiro.mgt.SecurityManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.recap.config.ApacheShiroConfig;
import org.recap.repository.InstitutionDetailsRepository;
import org.recap.repository.PermissionsRepository;
import org.recap.repository.RolesDetailsRepositorty;
import org.recap.repository.UserDetailsRepository;
import org.recap.security.AuthenticationService;
import org.recap.security.AuthorizationService;
import org.recap.security.realm.SimpleAuthorizationRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@Transactional
@Rollback()
public class BaseTestCase {

    protected MockMvc mockMvc;
    protected HttpMessageConverter mappingJackson2HttpMessageConverter;
    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext applicationContext;

    protected SecurityManager securityManager;

    @Autowired
    public ApacheShiroConfig shiroConfig;

    @Autowired
    public SimpleAuthorizationRealm simpleAuthorizationRealm;

    @Autowired
    public AuthenticationService authenticationService;

    @Autowired
    public AuthorizationService authorizationService;

    @Autowired
    public UserDetailsRepository userRepo;

    @Autowired
    public InstitutionDetailsRepository institutionDetailsRepository;

    @Autowired
    RolesDetailsRepositorty rolesDetailsRepositorty;

    @Autowired
    PermissionsRepository permissionsRepository;

    @Before
    public void loadApplicationContexts() {
        this.mockMvc = webAppContextSetup(applicationContext).build();
        assertNotNull(applicationContext);
        securityManager = (SecurityManager) applicationContext.getBean("securityManager");
        assertNotNull(securityManager);
    }

    @Autowired
    public void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
        Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }

    @Test
    public void loadContexts() {
        System.out.println();
    }



}