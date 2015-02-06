package ru.mremne.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * autor:maksim
 * date: 02.02.15
 * time: 21:30.
 */
public class StatusResult {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;
    private IdResult result;
    @JsonProperty("results")
    public IdResult getResult(){
        return result;
    }
    public void setResult(IdResult result){
        this.result=result;
    }


}
