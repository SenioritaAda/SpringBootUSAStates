package com.spring.states.service;

import com.spring.states.dto.State;
import com.spring.states.retriever.StatesRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "stateService")
public class StateServiceImpl implements StateService {

    @Autowired
    private StatesRetriever statesRetriever;

    @Override
    public List<State> getStates() {
        List<State> states = statesRetriever.getStates();

        return states == null ? Collections.emptyList() : states.stream()
                .sorted(Comparator.comparing(State::getPopulation).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }
}
