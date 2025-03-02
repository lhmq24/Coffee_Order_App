package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

public class Staff {
    @SerializedName("staff_id")
    private int staffId;
    @SerializedName("staff_name")
    private String staffName;
    @SerializedName("staff_age")
    private int staffAge;
    @SerializedName("staff_email")
    private String staffEmail;
    @SerializedName("staff_phone")
    private String staffPhone;

    public Staff(int staffId, String staffName, int staffAge, String staffEmail, String staffPhone) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.staffAge = staffAge;
        this.staffEmail = staffEmail;
        this.staffPhone = staffPhone;
    }

    //Getter

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getStaffAge() {
        return staffAge;
    }

    //Setter

    public void setStaffAge(int staffAge) {
        this.staffAge = staffAge;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }
}
