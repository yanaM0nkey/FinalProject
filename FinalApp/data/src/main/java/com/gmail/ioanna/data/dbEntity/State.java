package com.gmail.ioanna.data.dbEntity;


import com.google.gson.annotations.SerializedName;

public class State {

    @SerializedName("state")
    private String state;

    @SerializedName("code")
    private String code;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
