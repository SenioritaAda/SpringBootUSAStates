package com.spring.states.service;

import com.spring.states.dto.State;
import com.spring.states.retriever.StatesRetriever;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StateServiceImplTest {

    @Mock
    private StatesRetriever statesRetriever;

    @InjectMocks
    private StateServiceImpl stateService;

    @Test
    void shouldReturnEmptyCollectionWhenGettingStateAndRetrieverReturnsNull() {
        Mockito.when(statesRetriever.getStates()).thenReturn(null);
        List<State> states = stateService.getStates();
        assertEquals(Collections.emptyList(), states);
    }

    @Test
    void shouldReturnEmptyCollectionWhenGettingStateAndRetrieverReturnsEmptyList() {
        Mockito.when(statesRetriever.getStates()).thenReturn(Collections.emptyList());
        List<State> states = stateService.getStates();
        assertEquals(Collections.emptyList(), states);
    }

    @Test
    void shouldReturnEmptyCollectionWhenGettingStateAndRetrieverReturnsSingletonList() {
        List<State> expectedStates = Collections.singletonList(Mockito.mock(State.class));
        Mockito.when(statesRetriever.getStates()).thenReturn(expectedStates);
        List<State> states = stateService.getStates();
        assertEquals(expectedStates, states);
    }

    @Test
    void shouldReturnEmptyCollectionWhenGettingStateAndRetrieverReturnsListWith2Elements() {
        List<State> expectedStates = List.of(mockState(200), mockState(100));
        Mockito.when(statesRetriever.getStates()).thenReturn(expectedStates);
        List<State> states = stateService.getStates();
        assertEquals(expectedStates, states);
    }

    @Test
    void shouldReturnEmptyCollectionWhenGettingStateAndRetrieverReturnsListWith3Elements() {
        State stateOne = mockState(200);
        State stateTwo = mockState(100);
        State stateThree = mockState(300);
        List<State> retrieverReturnedStates = List.of(stateOne, stateTwo, stateThree);
        Mockito.when(statesRetriever.getStates()).thenReturn(retrieverReturnedStates);
        List<State> states = stateService.getStates();
        assertEquals(List.of(stateThree, stateOne, stateTwo), states);
    }

    @Test
    void shouldReturnEmptyCollectionWhenGettingStateAndRetrieverReturnsListWith10Elements() {
        State stateOne = mockState(20000);
        State stateTwo = mockState(100);
        State stateThree = mockState(500);
        State stateFour = mockState(500);
        State stateFive = mockState(600);
        State stateSix = mockState(800);
        State stateSeven = mockState(700);
        State stateEight = mockState(10000);
        State stateNine = mockState(150);
        State stateTen = mockState(3000);
        List<State> retrieverReturnedStates = List.of(stateOne, stateTwo, stateThree, stateFour, stateFive,
                stateSix, stateSeven, stateEight, stateNine, stateTen);
        Mockito.when(statesRetriever.getStates()).thenReturn(retrieverReturnedStates);
        List<State> states = stateService.getStates();
        assertEquals(List.of(stateOne, stateEight, stateTen), states);
    }


    private State mockState(int population){
        State state = Mockito.mock(State.class);
        Mockito.when(state.getPopulation()).thenReturn(population);
        return state;
    }


}