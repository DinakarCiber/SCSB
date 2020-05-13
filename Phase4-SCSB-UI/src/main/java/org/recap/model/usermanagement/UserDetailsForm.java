package org.recap.model.usermanagement;

/**
 * Created by dharmendrag on 28/12/16.
 */
public class UserDetailsForm {

    private Integer loginInstitutionId;

    private boolean superAdmin;

    private boolean recapUser;

    private boolean recapPermissionAllowed;

    /**
     * Gets recap permission allowed.
     *
     * @return the boolean
     */
    public boolean isRecapPermissionAllowed() {
        return recapPermissionAllowed;
    }

    /**
     * Sets recap permission allowed.
     *
     * @param recapPermissionAllowed the recap permission allowed
     */
    public void setRecapPermissionAllowed(boolean recapPermissionAllowed) {
        this.recapPermissionAllowed = recapPermissionAllowed;
    }

    /**
     * Gets login institution id.
     *
     * @return the login institution id
     */
    public Integer getLoginInstitutionId() {
        return loginInstitutionId;
    }

    /**
     * Sets login institution id.
     *
     * @param loginInstitutionId the login institution id
     */
    public void setLoginInstitutionId(Integer loginInstitutionId) {
        this.loginInstitutionId = loginInstitutionId;
    }

    /**
     * Gets super admin.
     *
     * @return the boolean
     */
    public boolean isSuperAdmin() {
        return superAdmin;
    }

    /**
     * Sets super admin.
     *
     * @param superAdmin the super admin
     */
    public void setSuperAdmin(boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    /**
     * Gets recap user.
     *
     * @return the boolean
     */
    public boolean isRecapUser() {
        return recapUser;
    }

    /**
     * Sets recap user.
     *
     * @param recapUser the recap user
     */
    public void setRecapUser(boolean recapUser) {
        this.recapUser = recapUser;
    }
}
