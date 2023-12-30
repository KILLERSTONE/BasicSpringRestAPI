package com.learn.learn.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.learn.Model.Attendance;
import com.learn.learn.Repository.AttendanceRepo;

@Service
public class AttendanceServiceImpl implements AttendanceServices{
    
    @Autowired
    private AttendanceRepo attdRepo;

    @Override
    public void setAttendance(String reg_no, Attendance att) {
        // TODO Auto-generated method stub
    }

    @Override
    public List<Attendance> getAttendance(String reg_no) {
        return null;
        // TODO Auto-generated method stub
    }

    @Override
    public List<Attendance> getDayAttendance(LocalDate date) {
        return null;
        // TODO Auto-generated method stub
    }

    @Override
    public void markAttendanceForStudentsOnDate(List<String> regNos, LocalDate date) {
        // TODO Auto-generated method stub
    }
    
}
