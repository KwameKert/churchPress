package com.codeinsyt.churchpressapi.services.impl;

import com.codeinsyt.churchpressapi.models.Sermon;
import com.codeinsyt.churchpressapi.repositories.SermonRepository;
import com.codeinsyt.churchpressapi.services.interfaces.SermonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SermonServiceImpl implements SermonService {

    private SermonRepository sermonRepository;


    @Autowired
    public SermonServiceImpl(SermonRepository sermonRepository) {
        this.sermonRepository = sermonRepository;
    }

    public HashMap<String, Object> responseAPI(Object data, String message, HttpStatus status){
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("data",data);
        responseData.put("message",message);
        responseData.put("status", status.value());

        return responseData;
    }

    @Override
    public HashMap<String, Object> createSermon(Sermon sermon) {
        try{
            Sermon newSermon = this.sermonRepository.save(sermon);
            return responseAPI(newSermon,"Sermon saved", HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }


    }

    public Optional<Sermon> sermonExists(Long id){
        try{
            Optional<Sermon> foundSermon = this.sermonRepository.findById(id);
            if(foundSermon.isPresent()){
                return foundSermon;
            }
            return null;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HashMap<String, Object> updateSermon(Sermon sermon) {
        try{
            if(sermonExists(sermon.getId()).isPresent()){
                Sermon updatedSermon =  this.sermonRepository.save(sermon);
                return responseAPI(updatedSermon,"Sermon updated ", HttpStatus.OK);
            }
            return responseAPI(null,"Sermon doesnt exist", HttpStatus.NO_CONTENT);

        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

    }

    @Override
    public HashMap<String, Object> softDeleteSermon(Long id) {

            try{
                if(sermonExists(id).isPresent()){
                    this.sermonRepository.UpdateSermonStat(id, "inactive");
                    return this.listSermons();
                }
               return responseAPI(null,"Sermon doesnt exist", HttpStatus.NO_CONTENT);
            }catch(Exception e){
                e.printStackTrace();
                return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
            }

    }

    @Override
    public HashMap<String, Object> hardDeleteSermon(Long id) {
        try{
            if(sermonExists(id).isPresent()){
                this.sermonRepository.deleteById(id);
                return this.listSermons();
            }
            return responseAPI(null,"Sermon doesnt exist", HttpStatus.NO_CONTENT);
        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

    }

    @Override
    public HashMap<String, Object> listSermons() {
        try{
            List<Sermon> sermons = this.sermonRepository.findAllByStatNotOrderByIdAsc("inactive");
            if(sermons.isEmpty()){
                return responseAPI(null, "No sermon found",HttpStatus.NO_CONTENT);
            }
            return responseAPI(sermons,"Sermons",HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> getSermon(Long id) {

        try {
            if (sermonExists(id).isPresent()) {
                return responseAPI(sermonExists(id).get(),"Sermon Found",HttpStatus.OK);
            }
            return responseAPI(null, "Sermon doesnt exist", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

    }
}
