package com.studentportal.portal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date recordTime;

    private boolean isPresent = false;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserLogin.class)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserLogin user;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Class.class)
    @JoinColumn(name = "class_id")
    private Class aClass;

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

    public UserLogin getUser() {
        return user;
    }

    public void setUser(UserLogin user) {
        this.user = user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}
