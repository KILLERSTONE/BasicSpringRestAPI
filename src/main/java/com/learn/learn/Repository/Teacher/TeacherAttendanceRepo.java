package com.learn.learn.Repository.Teacher;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.learn.Model.Teacher.Teacher;
import com.learn.learn.Model.Teacher.TeacherAttendance;
import java.util.List;


@Repository
public interface TeacherAttendanceRepo extends JpaRepository<TeacherAttendance,Long> {
    
    TeacherAttendance findByTeacherAndDate(Teacher teacher,LocalDate date);

    List<TeacherAttendance> findAllByTeacher(Teacher teacher);
    
    List<TeacherAttendance> findAllByDate(LocalDate date);

    List<TeacherAttendance> findAllByTeacherId(Long id);
}