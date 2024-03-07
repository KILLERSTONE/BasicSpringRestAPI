package com.learn.learn.Service.Student;

import java.time.LocalDate;
import java.util.List;

import com.learn.learn.Model.Student.Student;
import com.learn.learn.Model.Student.StudentAttendance;

public interface StudentAttendanceServices {
    
    void setAttendance(String reg_no,StudentAttendance att);
    List<StudentAttendance> getAttendance(String reg_no);
    List<StudentAttendance> getDayAttendance(LocalDate date);

    void markAttendanceForStudentsOnDate(List<String> regNos,LocalDate date,String status);    
    void deleteAttendanceByStudent(Student std);
}
