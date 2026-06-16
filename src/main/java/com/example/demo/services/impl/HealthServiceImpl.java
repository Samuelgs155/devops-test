package com.example.demo.services.impl;

import com.example.demo.services.IHealthService;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl implements IHealthService {

    @Override
    public String health() {
        return "ok!";
    }
}
