package com.studentportal.portal.services;

import com.studentportal.portal.domain.Attendance;
import com.studentportal.portal.domain.Class;
import com.studentportal.portal.domain.UserLogin;
import com.studentportal.portal.repositories.AttendanceRepository;
import com.studentportal.portal.repositories.ClassRepository;
import com.studentportal.portal.repositories.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    UserLoginRepository userLoginRepository;

    @Autowired
    ClassRepository classRepository;

    public ResponseEntity<?> saveAttendanceByDevice(String username, Long class_id) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String getDateTime = formatter.format(new Date());
        // CREATE NEW ATTENDANCE RECORD
        Attendance attendance = new Attendance();

        Class aClass = classRepository.findClassById(class_id);
        UserLogin user = userLoginRepository.findByUsername(username); // FIND USER OBJECT BASED OF GIVEN USERNAME
        Date recordTime = new Date();
        String formattedTimeStr = formatter.format(recordTime);
        Date formattedDate = formatter.parse(formattedTimeStr);
        //check if class start and end time is no later than current time
        boolean isValid = checkDateTime(formattedDate, aClass.getStartTime(), aClass.getEndTime());

        attendance.setUser(user);
        attendance.setaClass(aClass);
        attendance.setPresent(true);
        attendance.setRecordTime(formattedDate);

        if(isValid){
            attendanceRepository.save(attendance);
            return new ResponseEntity<>(formattedTimeStr, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Expired Class session selected!", HttpStatus.LOCKED);
        }

    }

    public Iterable<Attendance> findAllByUserId(Long id){
        return attendanceRepository.findAttendancesByUserId(id);
    }

    private boolean checkDateTime(Date dateToCheck, Date startDate, Date endDate) {
        return dateToCheck.compareTo(startDate) >= 0 && dateToCheck.compareTo(endDate) <=0;
    }

}
