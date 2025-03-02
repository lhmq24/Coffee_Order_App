package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class Account {
    @SerializedName("acc_id")
    private String accountId;
    @SerializedName("role_id")
    private String roleId;
    @SerializedName("staff_id")
    private String staffId;
    @SerializedName("acc_username")
    private String accountUsername;
    @SerializedName("acc_password")
    private String accountPassword;
    @SerializedName("acc_created_at")
    private String accountCreatedTime;

    public Account(String accountId, String roleId, String staffId,
                   String accountUsername, String accountPassword,
                   String accountCreatedTime) {
        this.accountId = accountId;
        this.roleId = roleId;
        this.staffId = staffId;
        this.accountUsername = accountUsername;
        this.accountPassword = accountPassword;
        this.accountCreatedTime = accountCreatedTime;
    }

    //Getter

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    //Setter

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountCreatedTime() {
        return accountCreatedTime;
    }

    public void setAccountCreatedTime(String accountCreatedTime) {
        this.accountCreatedTime = accountCreatedTime;
    }
}
