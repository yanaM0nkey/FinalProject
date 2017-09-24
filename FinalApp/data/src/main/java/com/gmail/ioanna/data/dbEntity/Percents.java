package com.gmail.ioanna.data.dbEntity;


import com.google.gson.annotations.SerializedName;

public class Percents {

    @SerializedName("percent")
    private String percent;
    @SerializedName("code")
    private String code;

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
