package com.codeinsyt.churchpressapi.services.impl;

import com.codeinsyt.churchpressapi.models.Leader;
import com.codeinsyt.churchpressapi.services.interfaces.LeaderService;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class LeaderServiceImpl implements LeaderService {


    public HashMap<String, Object> responseAPI(Object data, String message, HttpStatus status){
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("data",data);
        responseData.put("message",message);
        responseData.put("status", status.value());

        return responseData;
    }

    @Override
    public HashMap<String, Object> createLeader(Leader leader) {
        return null;
    }

    @Override
    public HashMap<String, Object> updateLeader(Leader leader) {
        return null;
    }

    @Override
    public HashMap<String, Object> softDeleteLeader(Long id) {
        return null;
    }

    @Override
    public HashMap<String, Object> hardDeleteLeader(Long id) {
        return null;
    }

    @Override
    public HashMap<String, Object> listLeaders() {
        return null;
    }

    @Override
    public HashMap<String, Object> getLeader(Long id) {
        return null;
    }
}
