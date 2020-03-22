package com.codeinsyt.churchpressapi.repositories;

import com.codeinsyt.churchpressapi.models.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface SermonRepository extends JpaRepository<SermonRepository, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Sermon s SET s.status = ?2 WHERE s.id = ?1")
    @Transactional
    int UpdateSermonStatus(Long sermonId, String status);

    List<Sermon> findAllByStatusOrderByIdAsc(String status);
}
