package com.codeinsyt.churchpressapi.repositories;

import com.codeinsyt.churchpressapi.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface DepartmentRepository  extends JpaRepository<Department, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Department d SET d.status = ?2 WHERE s.id = ?1")
    @Transactional
    int UpdateStudentStatus(Long departmentId, String status);

    List<Department> findAllByStatusOrderByIdAsc(String status);
}
