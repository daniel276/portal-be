package com.studentportal.portal.domain;

import javax.persistence.Id;
import java.util.Date;

public class Attendance {

    @Id
    private Long id;

    private Date recordTime;

    private boolean isPresent;

    public Attendance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}
