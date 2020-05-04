package com.studentportal.portal.web;

import com.studentportal.portal.repositories.AttendanceRepository;
import com.studentportal.portal.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/attendance")
@CrossOrigin
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @GetMapping("/log/{student_id}")
    public Iterable<?> findAllByStudentId(@PathVariable Long student_id){
        return attendanceService.findAllByUserId(student_id);
    }

    @PostMapping("/record/{username}/{class_id}")
    public ResponseEntity<?> recordByUsername(@PathVariable String username, @PathVariable Long class_id) throws ParseException {
        return attendanceService.saveAttendanceByDevice(username, class_id);
    }


}
