package com.codeinsyt.churchpressapi.services.interfaces;

import com.codeinsyt.churchpressapi.dto.LeaderDTO;
import com.codeinsyt.churchpressapi.models.Leader;

import java.util.HashMap;

public interface LeaderService {

    HashMap<String, Object> createLeader(LeaderDTO leaderDTO);

    HashMap<String, Object> updateLeader(LeaderDTO leaderDTO);

    HashMap<String, Object>  softDeleteLeader(Long id);

    HashMap<String, Object> hardDeleteLeader(Long id);

    HashMap<String, Object> listLeaders();

    HashMap<String, Object> getLeader(Long id);
}
