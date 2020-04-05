package com.codeinsyt.churchpressapi.services.impl;


import com.codeinsyt.churchpressapi.models.Department;
import com.codeinsyt.churchpressapi.repositories.DepartmentRepository;
import com.codeinsyt.churchpressapi.services.interfaces.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl  implements DepartmentService {

    private DepartmentRepository departmentRepository;


    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
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
    public HashMap<String, Object> createDepartment(Department department) {

        try{
            Department newDepartment = this.departmentRepository.save(department);
            return responseAPI(newDepartment,"Department saved", HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

    }

    public Optional<Department> departmentExists(Long id){
        try{
            Optional<Department> foundDepartment = this.departmentRepository.findById(id);
            if(foundDepartment.isPresent()){
                return foundDepartment;
            }
            return null;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public HashMap<String, Object> updateDepartment(Department department) {

        try{
            if(departmentExists(department.getId()).isPresent()){
                Department updatedDepartment =  this.departmentRepository.save(department);
                return responseAPI(updatedDepartment,"Department updated ", HttpStatus.OK);
            }
            return responseAPI(null,"Department doesnt exist", HttpStatus.NO_CONTENT);

        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

    }

    @Override
    public HashMap<String, Object> softDeleteDepartment(Long id) {

        try{
            if(departmentExists(id).isPresent()){
                this.departmentRepository.UpdateDepartmentStat(id, "inactive");
                return this.listDepartments();
            }
            return responseAPI(null,"Department doesnt exist", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> hardDeleteDepartment(Long id) {

        try{
            if(departmentExists(id).isPresent()){
                this.departmentRepository.deleteById(id);
                return this.listDepartments();
            }
            return responseAPI(null,"Department doesnt exist", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> listDepartments() {

        try{
            List<Department> sermons = this.departmentRepository.
                                        findAllByStatNotOrderByIdAsc("inactive");
            if(sermons.isEmpty()){
                return responseAPI(null, "No sermon found",HttpStatus.NO_CONTENT);
            }
            return responseAPI(sermons,"Departments",HttpStatus.FOUND);
        }catch(Exception e){
            e.printStackTrace();
            return responseAPI(null,e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public HashMap<String, Object> getDepartment(Long id) {

        try {
            if (departmentExists(id).isPresent()) {
                return responseAPI(departmentExists(id).get(),"Department Found",HttpStatus.OK);
            }
            return responseAPI(null, "Department doesnt exist", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return responseAPI(null, e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
