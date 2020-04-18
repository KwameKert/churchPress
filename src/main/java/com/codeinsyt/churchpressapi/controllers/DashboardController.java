package com.codeinsyt.churchpressapi.controllers;

import com.codeinsyt.churchpressapi.services.interfaces.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/dashboard/")
public class DashboardController {

    private DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<?> getDashboard(){
        return new ResponseEntity<>(this.dashboardService.getDashboardComponents(), HttpStatus.OK);
    }

    @GetMapping("nextEvent")
    public ResponseEntity<?> getNextEvent(){
        System.out.println("Imn here");
        return new ResponseEntity<>(this.dashboardService.getNextEvent(), HttpStatus.OK);
    }
}
