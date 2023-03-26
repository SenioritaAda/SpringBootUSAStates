package com.spring.states.retriever.remote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
class DataUSAStatesResponse {

    @JsonProperty("data")
    private List<DataUSAState> data;

    public DataUSAStatesResponse() {
    }

    public List<DataUSAState> getData() {
        return data;
    }

    public void setData(List<DataUSAState> data) {
        this.data = data;
    }
}
