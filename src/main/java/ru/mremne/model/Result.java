package ru.mremne.model;

import java.util.Date;

public class Result  {

    private String id;
    private Status status;
    private IdResult idResult;
    private long timestamp;
    public Result() {
        timestamp = (new Date()).getTime();
        status = Status.RUNNING;
    }

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

    public IdResult getIdResult() {
        return idResult;
    }

    public void setIdResult(IdResult idResult) {
        this.idResult = idResult;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "id: "+id+", status: \""+status+"\", result: \""+ idResult +"\"";
    }



}