package ru.mremne.view;

import ru.mremne.model.mongo.dao.identification.Result;

import java.util.List;

/**
 * autor:maksim
 * date: 11.05.15
 * time: 0:59.
 */
public class ViewResultData {
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
