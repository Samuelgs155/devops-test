package com.example.demo.controller;

import com.example.demo.services.IHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private IHealthService healthService;

    @GetMapping
    public String health() {
        return healthService.health();
    }


}
