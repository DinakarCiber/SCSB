package org.recap.model.search;

/**
 * Created by hemalathas on 22/12/16.
 */
public class RolesSearchResult {

    private String rolesName;
    private String rolesDescription;
    private String permissionName;
    private Integer roleId;

    /**
     * Gets roles name.
     *
     * @return the roles name
     */
    public String getRolesName() {
        return rolesName;
    }

    /**
     * Sets roles name.
     *
     * @param rolesName the roles name
     */
    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    /**
     * Gets roles description.
     *
     * @return the roles description
     */
    public String getRolesDescription() {
        return rolesDescription;
    }

    /**
     * Sets roles description.
     *
     * @param rolesDescription the roles description
     */
    public void setRolesDescription(String rolesDescription) {
        this.rolesDescription = rolesDescription;
    }

    /**
     * Gets permission name.
     *
     * @return the permission name
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * Sets permission name.
     *
     * @param permissionName the permission name
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * Gets role id.
     *
     * @return the role id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Sets role id.
     *
     * @param roleId the role id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
