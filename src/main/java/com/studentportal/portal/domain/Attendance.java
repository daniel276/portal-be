package com.studentportal.portal.domain;

import javax.persistence.Id;
import java.util.Date;

public class Attendance {

    @Id
    private Long id;

    private Date recordTime;

    private boolean isPresent;



}
