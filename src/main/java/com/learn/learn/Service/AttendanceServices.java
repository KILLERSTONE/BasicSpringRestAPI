package com.learn.learn.Service;

import java.time.LocalDate;
import java.util.List;

import com.learn.learn.Model.Attendance;

public interface AttendanceServices {
    
    void setAttendance(String reg_no,Attendance att);
    List<Attendance> getAttendance(String reg_no);
    List<Attendance> getDayAttendance(LocalDate date);

    void markAttendanceForStudentsOnDate(List<String> regNos,LocalDate date,String status);    
    
}
