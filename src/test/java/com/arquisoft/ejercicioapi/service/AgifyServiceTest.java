package com.arquisoft.ejercicioapi.service;

import com.arquisoft.ejercicioapi.entities.AgifyResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AgifyServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AgifyService agifyService;

    @Test
    void testGetAgeByName() {
        String name = "sebastian";
        AgifyResponse mockResponse = new AgifyResponse();
        mockResponse.setName(name);
        mockResponse.setAge(41);
        mockResponse.setCount(77621);

        String expectedUrl = "https://api.agify.io?name=" + name;
        when(restTemplate.getForObject(expectedUrl, AgifyResponse.class)).thenReturn(mockResponse);

        AgifyResponse result = agifyService.getAgeByName(name);
        assertEquals("sebastian", result.getName());
        assertEquals(41, result.getAge());
        assertEquals(77621, result.getCount());
    }
}
