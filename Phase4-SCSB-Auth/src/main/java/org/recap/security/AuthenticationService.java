package org.recap.security;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.recap.model.UserForm;

/**
 * Created by dharmendrag on 21/12/16.
 */
@FunctionalInterface
public interface AuthenticationService {

    /**
     * Do authentication for the given UsernamePasswordToken.
     *
     * @param token the token
     * @return the UserForm
     */
    UserForm doAuthentication(UsernamePasswordToken token);

}
