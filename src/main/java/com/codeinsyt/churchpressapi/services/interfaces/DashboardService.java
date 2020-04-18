package com.codeinsyt.churchpressapi.services.interfaces;

import java.text.ParseException;
import java.util.HashMap;

public interface DashboardService {

    Long countSermons();

    Long countEvents();

    Long countDepartments();

    HashMap<String, Object> getNextEvent();

    Long getNextEventTime() throws ParseException;

    HashMap<String, Object> getDashboardComponents();

}
