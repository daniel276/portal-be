package com.studentportal.portal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    @JsonIgnore
    private Module module;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Attendance> attendanceList = new ArrayList<>();

    @NotBlank(message = "class name cannot be empty")
    private String name;

    private String location;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date endTime;

    public Class() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
