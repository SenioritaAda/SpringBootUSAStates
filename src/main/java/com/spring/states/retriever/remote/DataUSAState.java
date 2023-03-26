package com.spring.states.retriever.remote;

import com.fasterxml.jackson.annotation.JsonProperty;

class DataUSAState {

    @JsonProperty("ID State")
    private String ID;
    @JsonProperty("State")
    private String name;
    @JsonProperty("ID Year")
    private int IDyear;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Population")
    private int populaton;
    @JsonProperty("Slug State")
    private String slugState;

    public DataUSAState() {
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getYearID() {
        return IDyear;
    }

    public String getYear() {
        return year;
    }

    public int getPopulaton() {
        return populaton;
    }

    public String getSlugState() {
        return slugState;
    }
}

