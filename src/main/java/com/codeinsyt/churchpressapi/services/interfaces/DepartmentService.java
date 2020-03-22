package com.codeinsyt.churchpressapi.services.interfaces;

import com.codeinsyt.churchpressapi.models.Department;

import java.util.HashMap;

public interface DepartmentService {

    HashMap<String, Object> createDepartment(Department department);

    HashMap<String, Object> updateDepartment(Department department);

    HashMap<String, Object>  softDeleteDepartment(Long id);

    HashMap<String, Object> hardDeleteDepartment(Long id);

    HashMap<String, Object> listDepartments();

    HashMap<String, Object> getDepartment(Long id);
}
