package com.gmail.ioanna.domain.entity;


public class ResponseAnswerModel implements DomainModel {
    private int status;
    private ResponseProfileModel responseProfileModel;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResponseProfileModel getResponseProfileModel() {
        return responseProfileModel;
    }

    public void setResponseProfileModel(ResponseProfileModel responseProfileModel) {
        this.responseProfileModel = responseProfileModel;
    }
}
