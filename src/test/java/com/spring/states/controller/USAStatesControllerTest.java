package com.spring.states.controller;

import com.spring.states.dto.State;
import com.spring.states.service.StateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class USAStatesControllerTest {

    @Mock
    private StateService stateService;

    @InjectMocks
    private USAStatesController controller;

    @Test
    void shouldReturnOkResponseWithNullStatesWhenServiceReturnsNull() {
        Mockito.when(stateService.getStates()).thenReturn(null);
        ResponseEntity<List<State>> response = controller.getStates();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void shouldReturnOkResponseWithEmptyStatesWhenServiceReturnsEmptyList() {
        Mockito.when(stateService.getStates()).thenReturn(Collections.emptyList());
        ResponseEntity<List<State>> response = controller.getStates();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(),response.getBody());
    }

    @Test
    void shouldReturnOkResponseWithStatesWhenServiceReturnsValidList() {
        List<State> expectedStates = List.of(Mockito.mock(State.class), Mockito.mock(State.class), Mockito.mock(State.class));
        Mockito.when(stateService.getStates()).thenReturn(expectedStates);
        ResponseEntity<List<State>> response = controller.getStates();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStates, response.getBody());
    }

}