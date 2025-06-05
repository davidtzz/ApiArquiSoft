package com.arquisoft.ejercicioapi.controller;

import com.arquisoft.ejercicioapi.entities.AgifyResponse;
import com.arquisoft.ejercicioapi.service.AgifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AgifyController {

    @Autowired
    private AgifyService agifyService;

    @GetMapping("/age")
    public AgifyResponse getAge(@RequestParam String name) {
        return agifyService.getAgeByName(name);
    }
}