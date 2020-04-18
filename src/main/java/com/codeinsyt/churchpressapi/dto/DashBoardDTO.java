package com.codeinsyt.churchpressapi.dto;

import java.util.Date;

public class DashBoardDTO {


    private Long sermonCount;
    private Long eventCount;
    private Long departmentCount;

    public Long getDepartmentCount() {
        return departmentCount;
    }

    public void setDepartmentCount(Long departmentCount) {
        this.departmentCount = departmentCount;
    }

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

}
