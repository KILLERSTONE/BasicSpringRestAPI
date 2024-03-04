package com.learn.learn.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.learn.Exception.StudentNotFoundException;
import com.learn.learn.Model.Attendance;
import com.learn.learn.Model.Student;
import com.learn.learn.Repository.AttendanceRepo;
import com.learn.learn.Repository.StudentRepo;

@Service
public class AttendanceServiceImpl implements AttendanceServices{
    
    @Autowired
    private AttendanceRepo attdRepo;
    @Autowired
    private StudentRepo stdRepo;

    @Override
    public void setAttendance(String reg_no, Attendance att) {
        Student std=stdRepo.findById(reg_no).orElse(null);

        if(std==null)throw new StudentNotFoundException("No student of this reg no exists");

        
        Attendance attd=attdRepo.findByStudentAndDate(std,att.getDate());

        if(attd==null){
            attd=new Attendance();
            attd.setStudent(std);
            attd.setDate(att.getDate());
            attd.setStatus(att.getStatus());
        }else{
            attd.setStatus(att.getStatus());
        }

        attdRepo.save(attd);
        
    }

    @Override
    public List<Attendance> getAttendance(String reg_no) {

        Student std=stdRepo.findById(reg_no).orElse(null);

        if(std==null)throw new StudentNotFoundException("No student of this reg no exists");

        return attdRepo.findAllByStudent(std);

        
    }

    @Override
    public List<Attendance> getDayAttendance(LocalDate date) {
        List<Attendance> attendances=attdRepo.findAllByDate(date);
        return attendances;
        
    }

    @Override
    public void markAttendanceForStudentsOnDate(List<String> regNos, LocalDate date,String status) {
        List<Student> students=stdRepo.findAllById(regNos);

        for(Student std:students){
            Attendance attd=attdRepo.findByStudentAndDate(std, date);

            if(attd==null){
                attd=new Attendance();
                attd.setDate(date);
                attd.setStatus(status);
                attd.setStudent(std);
            }else{
                attd.setStatus(status);
            }

            attdRepo.save(attd);
        }
    }
}