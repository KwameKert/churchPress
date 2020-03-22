package com.codeinsyt.churchpressapi.repositories;

import com.codeinsyt.churchpressapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository  extends JpaRepository<User, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.status = ?2 WHERE u.id = ?1")
    @Transactional
    int UpdateUserStatus(Long userId, String status);

    List<User> findAllByStatusOrderByIdAsc(String status);


}
