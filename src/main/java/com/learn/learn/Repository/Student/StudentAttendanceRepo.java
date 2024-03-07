package com.learn.learn.Repository.Student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.learn.Model.Student.Student;
import com.learn.learn.Model.Student.StudentAttendance;

@Repository
public interface StudentAttendanceRepo extends JpaRepository<StudentAttendance,Long>{

    StudentAttendance findByStudentAndDate(Student student, LocalDate date);

    List<StudentAttendance> findAllByStudent(Student student);

    List<StudentAttendance> findAllByDate(LocalDate date);
    
}
