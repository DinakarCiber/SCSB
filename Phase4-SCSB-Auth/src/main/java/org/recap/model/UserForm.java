package org.recap.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dharmendrag on 29/11/16.
 */
@ApiModel(value="userAuthRequest",description = "Model to show user details")
public class UserForm {

    @ApiModelProperty(name="userId",value="primary key against each user",position = 0)
    private Integer userId;

    @ApiModelProperty(name="username",value="Login Id or login name",position = 1)
    private String username;

    @ApiModelProperty(name="password",value="password for login",position = 2)
    private String password;

    @ApiModelProperty(name="institution",value="User's Institution",position = 3 , allowableValues = "1,2,3")
    private int institution;

    private String userInstitution;

    private Set<String> permissions=new HashSet<>();

    private String wrongCredentials;

    private boolean passwordMatcher;

    /**
     * Is password matcher boolean.
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
    public int getInstitution() {
        return institution;
    }

    /**
     * Sets institution.
     *
     * @param institution the institution
     */
    public void setInstitution(int institution) {
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
     * Gets user institution.
     *
     * @return the user institution
     */
    public String getUserInstitution() {
        return userInstitution;
    }

    /**
     * Sets user institution.
     *
     * @param userInstitution the user institution
     */
    public void setUserInstitution(String userInstitution) {
        this.userInstitution = userInstitution;
    }
}
