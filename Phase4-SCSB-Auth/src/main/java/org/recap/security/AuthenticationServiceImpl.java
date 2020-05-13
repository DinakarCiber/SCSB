package org.recap.security;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.recap.model.UserForm;
import org.recap.model.jpa.InstitutionEntity;
import org.recap.repository.InstitutionDetailsRepository;
import org.recap.repository.UserDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.recap.util.HelperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dharmendrag on 21/12/16.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private InstitutionDetailsRepository institutionDetailsRepository;

    @Autowired
    private HelperUtil helperUtil;

    @Override
    public UserForm doAuthentication(UsernamePasswordToken token) {
        logger.info(token.getUsername());
        UserForm userForm = new UserForm();
        String[] user = UserManagementService.userAndInstitution(token.getUsername());
        userForm.setUsername(user[0]);
        InstitutionEntity institutionEntity = helperUtil.getInstitutionIdByCode(user[1]);
        userForm.setInstitution(institutionEntity.getInstitutionId());
        userForm = getCredential(institutionEntity.getInstitutionId(), user[0], userForm);
        return userForm;
    }

    private UserForm getCredential(Integer institution, String username, UserForm userForm) {
        InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setInstitutionId(institution);
        userForm = UserManagementService.toUserForm(userDetailsRepository.findByLoginIdAndInstitutionEntity(username, institutionEntity), userForm);
        userForm.setPasswordMatcher(true);
        return userForm;
    }



}
