package com.studentportal.portal.repositories;

import com.studentportal.portal.domain.Attendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long> {

    Iterable<Attendance> findAttendancesByUserId(Long id);



}
