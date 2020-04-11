package com.codeinsyt.churchpressapi.services.impl;

import com.codeinsyt.churchpressapi.models.Event;
import com.codeinsyt.churchpressapi.repositories.EventRepository;
import com.codeinsyt.churchpressapi.services.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;


    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public HashMap<String, Object> responseAPI(Object data, String message, HttpStatus status){
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("data",data);
        responseData.put("message",message);
        responseData.put("status", status.value());

        return responseData;
    }



    @Override
    public HashMap<String, Object> createEvent(Event event) {
        try{
            Event newEvent = this.eventRepository.save(event);
            return responseAPI(newEvent, "Event created ",HttpStatus.OK);


        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }


    public Optional<Event> eventExists(Long id){

        try{
            Optional<Event> eventFound = this.eventRepository.findById(id);

            if(!eventFound.isPresent()){
                return null;
            }
            return eventFound;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HashMap<String, Object> updateEvent(Event event) {

       try{
           if(eventExists(event.getId()).isPresent()){
               Event updatedEvent = this.eventRepository.save(event);

               return responseAPI(updatedEvent,"Event updated", HttpStatus.OK);
           }
           return responseAPI(null, "Event not found", HttpStatus.NOT_FOUND);

       }catch(Exception e){
           e.printStackTrace();
           return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
       }
    }

    @Override
    public HashMap<String, Object> getEvent(Long eventId) {

        try{
            Optional<Event> found = eventExists(eventId);
            if(found.isPresent()){
                return responseAPI(found.get(),"Event found",HttpStatus.FOUND);
            }

            return responseAPI(null, "Event not found", HttpStatus.NOT_FOUND);

        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> softDeleteEvent(Long eventId) {
        try{
            if(eventExists(eventId).isPresent()){
                this.eventRepository.UpdateEventStat(eventId, "deleted");
                return this.listEvents();
            }
            return responseAPI(null,"Event doesnt exist", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> listEvents() {
        try{

            List<Event> events = this.eventRepository.findAllByStatNotOrderByIdAsc("deleted");

            if(events.isEmpty()){
                return responseAPI(null, "No event found",HttpStatus.NO_CONTENT);
            }

            return responseAPI(events, "Events found",HttpStatus.FOUND);


        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
