package com.codeinsyt.churchpressapi.services.impl;

import com.codeinsyt.churchpressapi.dto.LeaderDTO;
import com.codeinsyt.churchpressapi.models.Department;
import com.codeinsyt.churchpressapi.models.Leader;
import com.codeinsyt.churchpressapi.models.Sermon;
import com.codeinsyt.churchpressapi.repositories.DepartmentRepository;
import com.codeinsyt.churchpressapi.repositories.LeaderRepository;
import com.codeinsyt.churchpressapi.services.interfaces.LeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class LeaderServiceImpl implements LeaderService {

    private LeaderRepository leaderRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public LeaderServiceImpl(LeaderRepository leaderRepository, DepartmentRepository departmentRepository) {
        this.leaderRepository = leaderRepository;
        this.departmentRepository = departmentRepository;
    }




    public HashMap<String, Object> responseAPI(Object data, String message, HttpStatus status){
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("data",data);
        responseData.put("message",message);
        responseData.put("status", status.value());

        return responseData;
    }

    @Override
    public HashMap<String, Object> createLeader(LeaderDTO leaderDTO) {

        try{

            Leader leader = new Leader();
            leader.setName(leaderDTO.getName());
            leader.setDescription(leaderDTO.getDescription());
            leader.setDepartment(this.departmentRepository.findById(leaderDTO.getDepartment_id()).get());
            leader.setImage_url(leaderDTO.getImage_url());

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
    public HashMap<String, Object> updateLeader(LeaderDTO leaderDTO) {

        try{
            if(leaderExists(leaderDTO.getId()).isPresent()){

                Leader leader = new Leader();
                leader.setId(leaderDTO.getId());
                leader.setName(leaderDTO.getName());
                leader.setDescription(leaderDTO.getDescription());
                leader.setDepartment(this.departmentRepository.findById(leaderDTO.getDepartment_id()).get());
                leader.setImage_url(leaderDTO.getImage_url());

                Leader updatedLeader =  this.leaderRepository.save(leader);
                return responseAPI(updatedLeader,"Leader updated ", HttpStatus.OK);
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

        try{
            List<Leader> sermons = this.leaderRepository.findAllByStatNotOrderByIdAsc("inactive");
            if(sermons.isEmpty()){
                return responseAPI(null, "No leader found",HttpStatus.FOUND);
            }
            return responseAPI(sermons,"Leaders",HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> getLeader(Long id) {

        try {
            if (leaderExists(id).isPresent()) {
                return responseAPI(leaderExists(id).get(),"Leader Found",HttpStatus.FOUND);
            }
            return responseAPI(null, "Leader doesnt exist", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
