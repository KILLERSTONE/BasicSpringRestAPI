package com.learn.learn.Service.Teacher;

import java.time.LocalDate;
import java.util.List;

import com.learn.learn.Model.Teacher.TeacherAttendance;

public interface TeacherAttendanceService {
    void setAttendance(Long teacherId, TeacherAttendance attendance);

    List<TeacherAttendance> getAttendance(Long teacherId);

    List<TeacherAttendance> getAttendanceOnDate(LocalDate date);

    void markAttendanceForTeacherOnDate(Long teacherId, LocalDate date, String status);

    void deleteAttendanceByTeacherId(Long teacherId);

}
