package com.spring.states.controller;


import com.spring.states.dto.State;
import com.spring.states.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/states")
public class USAStatesController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<List<State>> getStates (){
        List<State> list = stateService.getStates();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
