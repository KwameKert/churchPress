package com.codeinsyt.churchpressapi.services.interfaces;

import com.codeinsyt.churchpressapi.models.Event;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

public interface DashboardService {

    Long countSermons();

    Long countEvents();

    Long getNextEventTime() throws ParseException;

    HashMap<String, Object> getDashboardComponents();

}
