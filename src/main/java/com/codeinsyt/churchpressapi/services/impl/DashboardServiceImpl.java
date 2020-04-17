package com.codeinsyt.churchpressapi.services.impl;

import com.codeinsyt.churchpressapi.dto.DashBoardDTO;
import com.codeinsyt.churchpressapi.models.Event;
import com.codeinsyt.churchpressapi.repositories.EventRepository;
import com.codeinsyt.churchpressapi.repositories.SermonRepository;
import com.codeinsyt.churchpressapi.services.interfaces.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class DashboardServiceImpl implements DashboardService {

    private SermonRepository sermonRepository;
    private EventRepository eventRepository;

    @Autowired
    public DashboardServiceImpl(SermonRepository sermonRepository, EventRepository eventRepository) {
        this.sermonRepository = sermonRepository;
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
    public Long countSermons() {
        final long count = sermonRepository.count();
        return count;
    }

    @Override
    public Long countEvents() {
        final long count = eventRepository.count();
        return count;
    }

    @Override
    public Date getNextEventTime() {
        Event event = this.eventRepository.findTopByEndDateBefore(new Date());
        return event.getEndDate();
    }


    @Override
    public HashMap<String, Object> getDashboardComponents() {

        try{
            DashBoardDTO dashBoardDTO = new DashBoardDTO();

            dashBoardDTO.setEventCount(this.countEvents());
            dashBoardDTO.setSermonCount(this.countSermons());
            dashBoardDTO.setNextEvent(this.getNextEventTime());

            return this.responseAPI(dashBoardDTO,"Dashboard data", HttpStatus.FOUND);

        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }




    }
}
