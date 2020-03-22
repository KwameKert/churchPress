package com.codeinsyt.churchpressapi.services.impl;

import com.codeinsyt.churchpressapi.models.Sermon;
import com.codeinsyt.churchpressapi.services.interfaces.SermonService;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class SermonServiceImpl implements SermonService {


    public HashMap<String, Object> responseAPI(Object data, String message, HttpStatus status){
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("data",data);
        responseData.put("message",message);
        responseData.put("status", status.value());

        return responseData;
    }

    @Override
    public HashMap<String, Object> createSermon(Sermon sermon) {
        return null;
    }

    @Override
    public HashMap<String, Object> updateSermon(Sermon sermon) {
        return null;
    }

    @Override
    public HashMap<String, Object> softDeleteSermon(Long id) {
        return null;
    }

    @Override
    public HashMap<String, Object> hardDeleteSermon(Long id) {
        return null;
    }

    @Override
    public HashMap<String, Object> listSermons() {
        return null;
    }

    @Override
    public HashMap<String, Object> getSermon(Long id) {
        return null;
    }
}
