package com.gmail.ioanna.data.dbEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private int id;
    private String name;
    private int percentOfCompletion;
    private String state;
    private int estimatedTime;
    private String startDate;
    private String dueDate;

    public Task() {
    }

    public Task(int id, String name, int percentOfCompletion, String state, int estimatedTime,
                String startDate, String dueDate){
        this.id = id;
        this.name = name;
        this.percentOfCompletion = percentOfCompletion;
        this.state = state;
        this.estimatedTime = estimatedTime;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentOfCompletion() {
        return percentOfCompletion;
    }

    public void setPercentOfCompletion(int percentOfCompletion) {
        this.percentOfCompletion = percentOfCompletion;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate){
        this.dueDate = dueDate;
    }

    //хотела изначально делать с Date
    public Date parse(String date) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        return format.parse(date);
    }
}
