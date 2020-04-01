package com.codeinsyt.churchpressapi.controllers;

import com.codeinsyt.churchpressapi.models.Department;
import com.codeinsyt.churchpressapi.services.interfaces.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/department/")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping
    public ResponseEntity<?> addDepartment(@Valid @RequestBody Department department){
        return new ResponseEntity<>(this.departmentService.createDepartment(department), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateDepartment(@Valid @RequestBody Department department){
        return new ResponseEntity<>(this.departmentService.updateDepartment(department), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.departmentService.getDepartment(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.departmentService.softDeleteDepartment(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listDepartments(){
        return new ResponseEntity<>(this.departmentService.listDepartments(), HttpStatus.OK);
    }
}
