package com.codeinsyt.churchpressapi.repositories;

import com.codeinsyt.churchpressapi.models.Event;
import com.codeinsyt.churchpressapi.models.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


    @Modifying(clearAutomatically = true)
    @Query("UPDATE Event e SET e.stat = ?2 WHERE e.id = ?1")
    @Transactional
    int UpdateEventStat(Long eventId, String status);

    List<Event> findAllByStatNotOrderByIdAsc(String status);

    Event findTopByStatAndEndDateAfter(String status,Date endDate);

}
