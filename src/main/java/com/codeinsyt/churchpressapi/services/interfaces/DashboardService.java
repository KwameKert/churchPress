package com.codeinsyt.churchpressapi.services.interfaces;

import com.codeinsyt.churchpressapi.models.Event;

import java.util.HashMap;

public interface DashboardService {

    Long countSermons();

    Long countEvents();

    Event getNextEvent();

    HashMap<String, Object> getDashboardComponents();

}
