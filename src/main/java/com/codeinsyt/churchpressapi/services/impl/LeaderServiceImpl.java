package com.codeinsyt.churchpressapi.services.impl;

import com.codeinsyt.churchpressapi.models.Department;
import com.codeinsyt.churchpressapi.models.Leader;
import com.codeinsyt.churchpressapi.models.Sermon;
import com.codeinsyt.churchpressapi.repositories.LeaderRepository;
import com.codeinsyt.churchpressapi.services.interfaces.LeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class LeaderServiceImpl implements LeaderService {

    private LeaderRepository leaderRepository;

    @Autowired
    public LeaderServiceImpl(LeaderRepository leaderRepository) {
        this.leaderRepository = leaderRepository;
    }

    public HashMap<String, Object> responseAPI(Object data, String message, HttpStatus status){
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("data",data);
        responseData.put("message",message);
        responseData.put("status", status.value());

        return responseData;
    }

    @Override
    public HashMap<String, Object> createLeader(Leader leader) {

        try{
            Leader newLeader = this.leaderRepository.save(leader);
            return responseAPI(newLeader,"Leader saved", HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    public Optional<Leader> leaderExists(Long id){
        try{
            Optional<Leader> foundLeader = this.leaderRepository.findById(id);
            if(foundLeader.isPresent()){
                return foundLeader;
            }
            return null;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HashMap<String, Object> updateLeader(Leader leader) {

        try{
            if(leaderExists(leader.getId()).isPresent()){
                Leader updatedSermon =  this.leaderRepository.save(leader);
                return responseAPI(updatedSermon,"Leader updated ", HttpStatus.OK);
            }
            return responseAPI(null,"Leader doesnt exist", HttpStatus.NOT_FOUND);

        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

    }

    @Override
    public HashMap<String, Object> softDeleteLeader(Long id) {
         try{
            if(leaderExists(id).isPresent()){
                this.leaderRepository.UpdateLeaderStat(id, "inactive");
                return this.listLeaders();
            }
            return responseAPI(null,"Leader doesnt exist", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> hardDeleteLeader(Long id) {

        try{
            if(leaderExists(id).isPresent()){
                this.leaderRepository.deleteById(id);
                return this.listLeaders();
            }
            return responseAPI(null,"Leader doesnt exist", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

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
