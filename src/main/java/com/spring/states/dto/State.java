package com.spring.states.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class State {
    @JsonProperty("state")
    private String name;
    @JsonProperty("population")
    private int population;

    public State(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }


}
