package br.com.hackaton.up.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Results {

    @JsonProperty("results")
    List<Feed> results;

    public List<Feed> getResults() {
        return results;
    }

    public void setResults(List<Feed> results) {
        this.results = results;
    }
}
