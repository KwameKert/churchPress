package com.codeinsyt.churchpressapi.services.impl;


import com.codeinsyt.churchpressapi.models.Department;
import com.codeinsyt.churchpressapi.services.interfaces.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class DepartmentServiceImpl  implements DepartmentService {

    public HashMap<String, Object> responseAPI(Object data, String message, HttpStatus status){
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("data",data);
        responseData.put("message",message);
        responseData.put("status", status.value());

        return responseData;
    }

    @Override
    public HashMap<String, Object> createDepartment(Department department) {
        return null;
    }

    @Override
    public HashMap<String, Object> updateDepartment(Department department) {
        return null;
    }

    @Override
    public HashMap<String, Object> softDeleteDepartment(Long id) {
        return null;
    }

    @Override
    public HashMap<String, Object> hardDeleteDepartment(Long id) {
        return null;
    }

    @Override
    public HashMap<String, Object> listDepartments() {
        return null;
    }

    @Override
    public HashMap<String, Object> getDepartment(Long id) {
        return null;
    }
}
