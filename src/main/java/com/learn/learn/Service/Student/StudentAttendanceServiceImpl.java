package com.learn.learn.Service.Student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.learn.Exception.StudentNotFoundException;
import com.learn.learn.Model.Student.Student;
import com.learn.learn.Model.Student.StudentAttendance;
import com.learn.learn.Repository.Student.StudentAttendanceRepo;
import com.learn.learn.Repository.Student.StudentRepo;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceServices{
    
    @Autowired
    private StudentAttendanceRepo attdRepo;
    @Autowired
    private StudentRepo stdRepo;

    @Override
    public void setAttendance(String reg_no, StudentAttendance att) {
        Student std=stdRepo.findById(reg_no).orElse(null);

        if(std==null)throw new StudentNotFoundException("No student of this reg no exists");

        
        StudentAttendance attd=attdRepo.findByStudentAndDate(std,att.getDate());

        if(attd==null){
            attd=new StudentAttendance();
            attd.setStudent(std);
            attd.setDate(att.getDate());
            attd.setStatus(att.getStatus());
        }else{
            attd.setStatus(att.getStatus());
        }

        attdRepo.save(attd);
        
    }

    @Override
    public List<StudentAttendance> getAttendance(String reg_no) {

        Student std=stdRepo.findById(reg_no).orElse(null);

        if(std==null)throw new StudentNotFoundException("No student of this reg no exists");

        return attdRepo.findAllByStudent(std);

        
    }

    @Override
    public List<StudentAttendance> getDayAttendance(LocalDate date) {
        List<StudentAttendance> attendances=attdRepo.findAllByDate(date);
        return attendances;
        
    }

    @Override
    public void markAttendanceForStudentsOnDate(List<String> regNos, LocalDate date,String status) {
        List<Student> students=stdRepo.findAllById(regNos);

        for(Student std:students){
            StudentAttendance attd=attdRepo.findByStudentAndDate(std, date);

            if(attd==null){
                attd=new StudentAttendance();
                attd.setDate(date);
                attd.setStatus(status);
                attd.setStudent(std);
            }else{
                attd.setStatus(status);
            }

            attdRepo.save(attd);
        }
    }

    @Override
    public void deleteAttendanceByStudent(Student std) {
        // TODO Auto-generated method stub
        List<StudentAttendance> attdList=attdRepo.findAllByStudent(std);
        attdRepo.deleteAll(attdList);
    }
}