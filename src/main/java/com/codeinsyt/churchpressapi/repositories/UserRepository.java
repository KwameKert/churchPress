package com.codeinsyt.churchpressapi.repositories;

import com.codeinsyt.churchpressapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.stat = ?2 WHERE u.id = ?1")
    @Transactional
    int UpdateUserStat(Long userId, String status);

    List<User> findAllByStatOrderByIdAsc(String status);
    Optional<User> findFirstByUserNameAndPassword(String userName, String password);
    User findByUserName(String username);


}
