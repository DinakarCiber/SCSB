package org.recap.security;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

/**
 * Created by dharmendrag on 21/12/16.
 */
@FunctionalInterface
public interface AuthorizationService {

    /**
     * Get the AuthorizationInfo for the loginId.
     *
     * @param authorizationInfo the authorization info
     * @param loginId           the login id
     * @return the authorization info
     */
    AuthorizationInfo doAuthorizationInfo(SimpleAuthorizationInfo authorizationInfo, Integer loginId);
}
