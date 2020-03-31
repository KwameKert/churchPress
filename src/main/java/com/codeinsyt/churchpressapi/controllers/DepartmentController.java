package com.codeinsyt.churchpressapi.controllers;

import com.codeinsyt.churchpressapi.models.Department;
import com.codeinsyt.churchpressapi.services.interfaces.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/department/")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    public ResponseEntity<?> addDepartment(@Valid @RequestBody Department department){
        return new ResponseEntity<>(this.departmentService.createDepartment(department), HttpStatus.OK);
    }
}
