package com.codeinsyt.churchpressapi.dto;

import java.util.Date;

public class DashBoardDTO {


    private Long sermonCount;
    private Long eventCount;
    private Date nextEvent;


    public Long getSermonCount() {
        return sermonCount;
    }

    public void setSermonCount(Long sermonCount) {
        this.sermonCount = sermonCount;
    }

    public Long getEventCount() {
        return eventCount;
    }

    public void setEventCount(Long eventCount) {
        this.eventCount = eventCount;
    }

    public Date getNextEvent() {
        return nextEvent;
    }

    public void setNextEvent(Date nextEvent) {
        this.nextEvent = nextEvent;
    }
}
