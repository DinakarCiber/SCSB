package org.recap.model.usermanagement;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dharmendrag on 29/11/16.
 */
public class UserForm implements Serializable {

    private Integer userId;

    private String username;

    private String password;

    private boolean rememberMe;

    private String wrongCredentials;

    private boolean passwordMatcher;

    private String institution;

    private String errorMessage;

    private Set<String> permissions=new HashSet<>();

    /**
     * Gets password matcher.
     *
     * @return the boolean
     */
    public boolean isPasswordMatcher() {
        return passwordMatcher;
    }

    /**
     * Sets password matcher.
     *
     * @param passwordMatcher the password matcher
     */
    public void setPasswordMatcher(boolean passwordMatcher) {
        this.passwordMatcher = passwordMatcher;
    }

    /**
     * Gets wrong credentials.
     *
     * @return the wrong credentials
     */
    public String getWrongCredentials() {
        return wrongCredentials;
    }

    /**
     * Sets wrong credentials.
     *
     * @param wrongCredentials the wrong credentials
     */
    public void setWrongCredentials(String wrongCredentials) {
        this.wrongCredentials = wrongCredentials;
    }

    /**
     * Gets permissions.
     *
     * @return the permissions
     */
    public Set<String> getPermissions() {
        return permissions;
    }

    /**
     * Sets permissions.
     *
     * @param permissions the permissions
     */
    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    /**
     * Gets institution.
     *
     * @return the institution
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * Sets institution.
     *
     * @param institution the institution
     */
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Is remember me boolean.
     *
     * @return the boolean
     */
    public boolean isRememberMe() {
        return rememberMe;
    }

    /**
     * Sets remember me.
     *
     * @param rememberMe the remember me
     */
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
