package com.codeinsyt.churchpressapi.services.interfaces;


import com.codeinsyt.churchpressapi.models.Event;

import java.util.HashMap;

public interface EventService {

    HashMap<String , Object> createEvent(Event event);

    HashMap<String , Object> updateEvent(Event event);

    HashMap<String , Object> getEvent(Long eventId);

    HashMap<String , Object> softDeleteEvent(Long eventId);


    HashMap<String, Object > listEvents();


}
