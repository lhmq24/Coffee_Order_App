package com.example.coffee_order_app.Model;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Account_Session {
    @SerializedName("session_id")
    private int sessionId;
    @SerializedName("acc_id")
    private int accountId;
    @SerializedName("session_token")
    private String sessionToken;
    @SerializedName("session_created_at")
    private Timestamp sessionCreatedTime;
    @SerializedName("session_expires_at")
    private Timestamp sessionExpiredTime;
    @SerializedName("session_revoked")
    private boolean isRevoked;

    public Account_Session(int sessionId, int accountId, String sessionToken,
                           Timestamp sessionCreatedTime, Timestamp sessionExpiredTime,
                           boolean isRevoked) {
        this.sessionId = sessionId;
        this.accountId = accountId;
        this.sessionToken = sessionToken;
        this.sessionCreatedTime = sessionCreatedTime;
        this.sessionExpiredTime = sessionExpiredTime;
        this.isRevoked = isRevoked;
    }

    //Getter

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    //Setter

    public Timestamp getSessionCreatedTime() {
        return sessionCreatedTime;
    }

    public void setSessionCreatedTime(Timestamp sessionCreatedTime) {
        this.sessionCreatedTime = sessionCreatedTime;
    }

    public Timestamp getSessionExpiredTime() {
        return sessionExpiredTime;
    }

    public void setSessionExpiredTime(Timestamp sessionExpiredTime) {
        this.sessionExpiredTime = sessionExpiredTime;
    }

    public boolean isRevoked() {
        return isRevoked;
    }

    public void setRevoked(boolean revoked) {
        isRevoked = revoked;
    }
}
