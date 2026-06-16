package com.example.demo.services.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthServiceImplTest {

    private final HealthServiceImpl service =
            new HealthServiceImpl();

    @Test
    void shouldReturnOk() {

        String result = service.health();

        assertEquals("ok!", result);
    }

}