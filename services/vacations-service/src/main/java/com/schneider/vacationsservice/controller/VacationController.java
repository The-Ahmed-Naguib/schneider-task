package com.schneider.vacationsservice.controller;

import com.schneider.vacationsservice.dto.Response;
import com.schneider.vacationsservice.dto.VacationDto;
import com.schneider.vacationsservice.dto.VacationSubmitDto;
import com.schneider.vacationsservice.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.schneider.vacationsservice.util.SecurityUtil.getCurrentUserIdFromSession;

@RestController
@RequestMapping("/vacation")
public class VacationController {

    VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/all")
    ResponseEntity<Response<List<VacationDto>>> get() {
        return ResponseEntity.ok(new Response(
                vacationService.getAllVacationsByEmployeeId(getCurrentUserIdFromSession()))
        );
    }

    @PostMapping
    ResponseEntity<Response<VacationDto>> addVacation(@RequestBody VacationSubmitDto vacationDto) {
        return ResponseEntity.ok(new Response(vacationService.addVacation(vacationDto)));
    }

    @GetMapping("/duration")
    ResponseEntity<Response<Integer>> calculateDuration(@RequestParam String startDate, @RequestParam String endDate) {
        return ResponseEntity.ok(new Response(
                vacationService.calculateVacationDuration(startDate, endDate))
        );
    }


}