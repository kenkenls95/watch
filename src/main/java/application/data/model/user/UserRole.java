package application.data.model.user;

import javax.persistence.*;

@Entity(name = "tbl_userrole")
public class UserRole {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userRoleId;
    private Integer userId;
    private Integer roleId;
    private Integer status;

    public UserRole() {
    }

    public UserRole(Integer userId, Integer roleId, Integer status) {
        this.userId = userId;
        this.roleId = roleId;
        this.status = status;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
