package com.codeinsyt.churchpressapi.services.interfaces;

import com.codeinsyt.churchpressapi.models.Leader;

import java.util.HashMap;

public interface LeaderService {

    HashMap<String, Object> createLeader(Leader leader);

    HashMap<String, Object> updateLeader(Leader leader);

    HashMap<String, Object>  softDeleteLeader(Long id);

    HashMap<String, Object> hardDeleteLeader(Long id);

    HashMap<String, Object> listLeaders();

    HashMap<String, Object> getLeader(Long id);
}
