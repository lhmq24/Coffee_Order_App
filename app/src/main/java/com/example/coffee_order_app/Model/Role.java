package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class Role {
    @SerializedName("role_id")
    private int roleId;
    @SerializedName("role_name")
    private String roleName;

    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    //Getter

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    //Setter

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
