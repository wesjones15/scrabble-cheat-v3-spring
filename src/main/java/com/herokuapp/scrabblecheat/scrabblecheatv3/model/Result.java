package com.herokuapp.scrabblecheat.scrabblecheatv3.model;

import java.util.LinkedHashMap;

public class Result {
    private LinkedHashMap<String, Integer> results;

    public Result (LinkedHashMap<String, Integer> results) {
        this.results = results;
    }

    public LinkedHashMap<String, Integer> getResults() {
        return results;
    }

    public void setResults(LinkedHashMap<String, Integer> results) {
        this.results = results;
    }
}
