package ru.mremne.model.mongo.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import ru.mremne.model.identification.IdResult;
import ru.mremne.model.identification.Status;

import java.util.Date;

/**
 * autor:maksim
 * date: 07.05.15
 * time: 22:57.
 */
@Entity
public class Result {
    @Id
    private ObjectId id;
    private String fakeId;
    private Status status;
    private IdResult idResult;
    private long timestamp;
    public Result() {
        timestamp = (new Date()).getTime();
        status = Status.RUNNING;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFakeId() {
        return fakeId;
    }

    public void setFakeId(String fakeId) {
        this.fakeId = fakeId;
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
    @Override
    public String toString() {
        return "id: "+id+", status: \""+status+"\", result: \""+ idResult +"\"";
    }
}
