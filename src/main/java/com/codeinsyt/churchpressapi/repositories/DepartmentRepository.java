package com.codeinsyt.churchpressapi.repositories;

import com.codeinsyt.churchpressapi.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface DepartmentRepository  extends JpaRepository<Department, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Department d SET d.stat = ?2 WHERE d.id = ?1")
    @Transactional
    int UpdateDepartmentStat(Long departmentId, String status);

    List<Department> findAllByStatOrderByIdAsc(String status);
}
