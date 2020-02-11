package com.studentportal.portal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank(message = "module code cannot be empty")
    private String code;

    @NotBlank(message = "module name cannot be empty")
    private String name;

    private float credit;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date endDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "module", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private List<Class> classList = new ArrayList<>();

    public Module() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setClassList(List<Class> classList) {
        this.classList = classList;
    }
}
