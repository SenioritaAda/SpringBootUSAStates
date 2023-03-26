package com.spring.states.retriever.remote;

import com.spring.states.dto.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DataUSAServerStatesRetrieverTest {

    @Mock
    private HttpClient httpClient;

    private DataUSAServerStatesRetriever retriever = new DataUSAServerStatesRetriever() {
        @Override
        HttpClient createClient() {
            return httpClient;
        }
    };

    @Test
    void shouldThrowExceptionWhenInvalidStatusCodeReturnedWhileGettingStates() throws Exception {
        HttpResponse response = Mockito.mock(HttpResponse.class);
        Mockito.when(response.statusCode()).thenReturn(400);
        Mockito.when(httpClient.send(Mockito.any(), Mockito.eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(response);
        assertThrows(RuntimeException.class, () -> retriever.getStates());
    }

    @Test
    void shouldThrowExceptionWhenHttpClientThrowsExceptionWhileGettingStates() throws Exception {
        Mockito.when(httpClient.send(Mockito.any(), Mockito.eq(HttpResponse.BodyHandlers.ofString())))
                .thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> retriever.getStates());
    }

    @Test
    void shouldReturnEmptyListWhenEmptyDataReturnedWhileGettingStates() throws Exception {
        HttpResponse response = Mockito.mock(HttpResponse.class);
        Mockito.when(response.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(Mockito.any(), Mockito.eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(response);
        Mockito.when(response.body()).thenReturn("{}");
        List<State> states = retriever.getStates();
        assertEquals(Collections.emptyList(), states);
    }

    @Test
    void shouldReturnStateWhenValidStatesReturnedFromServer() throws Exception {
        HttpResponse response = Mockito.mock(HttpResponse.class);
        Mockito.when(response.statusCode()).thenReturn(200);
        Mockito.when(httpClient.send(Mockito.any(), Mockito.eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(response);
        Mockito.when(response.body()).thenReturn(
                "{\n" +
                "  \"data\":\n" +
                "  [\n" +
                "    {\"ID State\":\"04000US01\",\n" +
                "      \"State\":\"Alabama\",\n" +
                "      \"ID Year\":2020,\n" +
                "      \"Year\":\"2020\",\n" +
                "      \"Population\":4893186,\n" +
                "      \"Slug State\":\"alabama\"\n" +
                "    }\n" +
                "    ],\n" +
                "  \"source\":[\n" +
                "    {\n" +
                "      \"measures\":[\"Population\"],\n" +
                "      \"annotations\":{\n" +
                "        \"source_name\":\"Census Bureau\",\n" +
                "        \"source_description\":\"The American Community Survey (ACS) is conducted by the US Census and sent to a portion of the population every year.\",\n" +
                "        \"dataset_name\":\"ACS 5-year Estimate\",\n" +
                "        \"dataset_link\":\"http://www.census.gov/programs-surveys/acs/\",\n" +
                "        \"table_id\":\"B01003\",\n" +
                "        \"topic\":\"Diversity\",\n" +
                "        \"subtopic\":\"Demographics\"\n" +
                "      },\n" +
                "      \"name\":\"acs_yg_total_population_5\",\n" +
                "      \"substitutions\":[]\n" +
                "    }\n" +
                "  ]\n" +
                "}");
        List<State> states = retriever.getStates();
        assertEquals(1, states.size());
        assertEquals("Alabama", states.get(0).getName());
        assertEquals(4893186, states.get(0).getPopulation());

    }
}