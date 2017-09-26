package com.gmail.ioanna.data.entity;


import com.google.gson.annotations.SerializedName;

public class ResponseAnswer implements DataModel {

    @SerializedName("status")
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @SerializedName("data")
    private ResponseProfile responseProfile;

    public ResponseProfile getResponseProfile() {
        return responseProfile;
    }

    public void setResponseProfile(ResponseProfile responseProfile) {
        this.responseProfile = responseProfile;
    }
}
