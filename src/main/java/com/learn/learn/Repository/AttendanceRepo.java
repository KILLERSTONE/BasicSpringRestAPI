package com.learn.learn.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.learn.Model.Attendance;
import com.learn.learn.Model.Student;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,Long>{

    Attendance findByStudentAndDate(Student student, LocalDate date);

    List<Attendance> findAllByStudent(Student student);

    List<Attendance> findAllByDate(LocalDate date);
    
}
