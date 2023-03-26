package com.spring.states.retriever.remote;

import com.spring.states.dto.State;
import com.spring.states.retriever.StatesRetriever;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DataUSAServerStatesRetriever implements StatesRetriever {

    private static final Log logger = LogFactory.getLog(DataUSAServerStatesRetriever.class);
    private static final String SERVER_URL = "https://datausa.io/api/data?drilldowns=State&measures=Population&year=latest";

    public List<State> getStates() {
        HttpRequest request = HttpRequest.newBuilder(URI.create(SERVER_URL))
                .GET()
                .build();
        try {
            HttpResponse<String> response = createClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() >= 300){
                logger.error("Invalid response from dataUSA server. Status code: "+ response.statusCode());
                throw new RuntimeException("Invalid response");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            DataUSAStatesResponse responseData = objectMapper.readValue(response.body(), DataUSAStatesResponse.class);
            if(responseData == null || responseData.getData() == null){
                return Collections.emptyList();
            }
            return responseData.getData().stream()
                    .map(this::mapToState)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    HttpClient createClient() {
        return HttpClient.newHttpClient();
    }

    private State mapToState(DataUSAState state){
        return new State(state.getName(), state.getPopulaton());
    }
}
