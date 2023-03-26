package com.spring.states.retriever;

import com.spring.states.retriever.remote.DataUSAServerStatesRetriever;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatesRetrieverConfiguration {

    @Bean
    public StatesRetriever statesRetriever(){
        return new DataUSAServerStatesRetriever();
    }
}
