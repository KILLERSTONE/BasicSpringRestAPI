package com.learn.learn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.learn.Model.Attendance;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Long>{
    
}
