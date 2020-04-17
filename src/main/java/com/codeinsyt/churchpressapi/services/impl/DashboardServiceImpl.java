package com.codeinsyt.churchpressapi.services.impl;

import com.codeinsyt.churchpressapi.dto.DashBoardDTO;
import com.codeinsyt.churchpressapi.models.Event;
import com.codeinsyt.churchpressapi.repositories.EventRepository;
import com.codeinsyt.churchpressapi.repositories.SermonRepository;
import com.codeinsyt.churchpressapi.services.interfaces.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public Long getNextEventTime() throws ParseException {
      //  SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Event event = this.eventRepository.findTopByEndDateAfter(new Date());

//        Date endDate = format.parse(event.getEndDate().toString());
        Date currentDate = new Date();

        Date endDate = event.getEndDate();

        Date current = new Date();

        Long d1 =   endDate.getTime()   ;
        Long d2 = current.getTime();

        System.out.println(d1);
        System.out.println(d2);
       Long diff = d1 -d2;



        System.out.println(diff);
        return diff;
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
