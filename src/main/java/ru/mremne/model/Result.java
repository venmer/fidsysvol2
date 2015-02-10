package ru.mremne.model;

import java.util.Date;

public class Result  {

    private String id;
    private Status status;
    private IdResult result;
    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public IdResult getResult() {
        return result;
    }

    public void setResult(IdResult result) {
        this.result = result;
    }

    public Result() {
        timestamp = (new Date()).getTime();
        status = Status.RUNNING;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "id: "+id+", status: \""+status+"\", idresult: \""+result+"\"";
    }



}