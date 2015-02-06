package ru.mremne.model;

import java.util.Date;

public class Result  {

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private long timestamp;

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

    private Status status;
    private IdResult result;

    public Result() {
        timestamp = (new Date()).getTime();
        status = Status.RUNNING;
    }


}