package com.studentportal.portal.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "module_attendance")
public class ModuleAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private StudentModule studentModule;

    private Module moduleId;

    private Date dateTime;

    private String mark; // P or A


}
