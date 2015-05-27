package ru.mremne.model.mongo.dao.identification;

import org.mongodb.morphia.annotations.Entity;
import ru.mremne.model.identification.IdResult;

import java.util.Date;

/**
 * autor:maksim
 * date: 07.05.15
 * time: 22:57.
 */
@Entity
public class Result {
    private String userId;
    private Status status;
    private IdResult idResult;
    private long timestamp;
    private float match;
    public Result() {
        timestamp = (new Date()).getTime();
        status = Status.RUNNING;
    }

    public String getUserId() {
        return userId;
    }

    public void setId(String id) {
        this.userId = id;
    }

    public IdResult getIdResult() {
        return idResult;
    }

    public void setIdResult(IdResult idResult) {
        this.idResult = idResult;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getMatch() {
        return match;
    }

    public void setMatch(float match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "{\"id\": " + userId + ", \"status\": \"" + status + "\", \"result\": \"" + idResult + "\"}";
    }
}
