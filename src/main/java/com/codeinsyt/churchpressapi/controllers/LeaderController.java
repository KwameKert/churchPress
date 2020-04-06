package com.codeinsyt.churchpressapi.controllers;


import com.codeinsyt.churchpressapi.dto.LeaderDTO;
import com.codeinsyt.churchpressapi.models.Department;
import com.codeinsyt.churchpressapi.services.interfaces.LeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/leader/")
public class LeaderController {

    private LeaderService leaderService;

    @Autowired
    public LeaderController(LeaderService leaderService) {
        this.leaderService = leaderService;
    }


    @PostMapping
    public ResponseEntity<?> addLeader(@Valid @RequestBody LeaderDTO leaderDTO){
        return new ResponseEntity<>(this.leaderService.createLeader(leaderDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateLeader(@Valid @RequestBody LeaderDTO leaderDTO){
        //System.out.println("Update leader");
        return new ResponseEntity<>(this.leaderService.updateLeader(leaderDTO), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getLeader(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.leaderService.getLeader(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteLeader(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.leaderService.softDeleteLeader(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listLeaders(){
        return new ResponseEntity<>(this.leaderService.listLeaders(), HttpStatus.OK);
    }



}
