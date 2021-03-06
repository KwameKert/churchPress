package com.codeinsyt.churchpressapi.repositories;

import com.codeinsyt.churchpressapi.models.Leader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface LeaderRepository extends JpaRepository<Leader, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Leader l SET l.stat = ?2 WHERE l.id = ?1")
    @Transactional
    int UpdateLeaderStat(Long leaderId, String status);

    List<Leader> findAllByStatNotOrderByIdAsc(String status);
}
