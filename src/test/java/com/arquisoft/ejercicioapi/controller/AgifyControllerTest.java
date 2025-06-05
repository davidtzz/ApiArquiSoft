package com.arquisoft.ejercicioapi.controller;

import com.arquisoft.ejercicioapi.entities.AgifyResponse;
import com.arquisoft.ejercicioapi.service.AgifyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AgifyController.class)
@Import(AgifyControllerTest.MockConfig.class)
class AgifyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AgifyService agifyService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public AgifyService agifyService() {
            return mock(AgifyService.class);
        }
    }

    @Test
    void testGetAge() throws Exception {
        String name = "sebastian";
        AgifyResponse response = new AgifyResponse();
        response.setName(name);
        response.setAge(41);
        response.setCount(77621);

        when(agifyService.getAgeByName(name)).thenReturn(response);

        mockMvc.perform(get("/api/age")
                        .param("name", name)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("sebastian"))
                .andExpect(jsonPath("$.age").value(41))
                .andExpect(jsonPath("$.count").value(77621));
    }
}
