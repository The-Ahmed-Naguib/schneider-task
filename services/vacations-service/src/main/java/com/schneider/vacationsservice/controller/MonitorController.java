package com.schneider.vacationsservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
    @GetMapping
    ResponseEntity<String> get() {
        return ResponseEntity.ok("I am up");
    }
}