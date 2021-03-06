package com.codeinsyt.churchpressapi.controllers;

import com.codeinsyt.churchpressapi.models.Sermon;
import com.codeinsyt.churchpressapi.services.interfaces.SermonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/sermon/")
public class SermonController {

    private SermonService sermonService;

    @Autowired
    public SermonController(SermonService sermonService) {
        this.sermonService = sermonService;
    }

    @PostMapping
    public ResponseEntity<?> addSermon(@RequestBody Sermon sermon){
        return new ResponseEntity<>(this.sermonService.createSermon(sermon), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateSermon(@RequestBody Sermon sermon){
        return new ResponseEntity<>(this.sermonService.updateSermon(sermon), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listSermons(){
        return new ResponseEntity<>(this.sermonService.listSermons(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSermon(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.sermonService.getSermon(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSermon(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.sermonService.softDeleteSermon(id),HttpStatus.OK);
    }







}
