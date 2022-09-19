package com.schneider.vacationsservice.controller;

import com.schneider.vacationsservice.dto.Response;
import com.schneider.vacationsservice.entity.VacationTypeEntity;
import com.schneider.vacationsservice.service.LookupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lookups")
public class LookupsController {

    LookupsService lookupsService;

    @Autowired
    public LookupsController(LookupsService lookupsService) {
        this.lookupsService = lookupsService;
    }

    @GetMapping("vacation-type")
    ResponseEntity<Response<List<VacationTypeEntity>>> get() {
        return ResponseEntity.ok(new Response(lookupsService.getVacationTypes()));
    }
}