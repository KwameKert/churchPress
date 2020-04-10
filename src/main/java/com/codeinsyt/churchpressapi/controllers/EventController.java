package com.codeinsyt.churchpressapi.controllers;


import com.codeinsyt.churchpressapi.models.Event;
import com.codeinsyt.churchpressapi.services.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/event/")
public class EventController {
    
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @PostMapping
    public ResponseEntity<?> addEvent(@Valid @RequestBody Event event){
        return new ResponseEntity<>(this.eventService.createEvent(event), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateEvent( @RequestBody Event event){
        //System.out.println("Update event");
        return new ResponseEntity<>(this.eventService.updateEvent(event), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.eventService.getEvent(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.eventService.softDeleteEvent(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listEvents(){
        return new ResponseEntity<>(this.eventService.listEvents(), HttpStatus.OK);
    }

}
