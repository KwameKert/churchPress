package com.codeinsyt.churchpressapi.services.interfaces;

import com.codeinsyt.churchpressapi.models.Event;

import java.util.Date;
import java.util.HashMap;

public interface DashboardService {

    Long countSermons();

    Long countEvents();

    Long getNextEventTime();

    HashMap<String, Object> getDashboardComponents();

}
