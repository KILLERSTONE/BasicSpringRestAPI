package com.learn.learn.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.learn.Exception.StudentNotFoundException;
import com.learn.learn.Model.Attendance;
import com.learn.learn.Model.Student;
import com.learn.learn.Repository.StudentRepo;

import jakarta.transaction.Transactional;

@Service
public class StudentServicesImpl implements StudentServices {
    

    @Autowired
    private StudentRepo studentRepo;

    private AttendanceServices attendanceService;
    private MarksServices marksService;

    public StudentServicesImpl(AttendanceServices attendanceService,MarksServices marksServices){
        this.attendanceService=attendanceService;
        this.marksService=marksServices;
    }


    @Override
    public String saveStudent(Student s){
        String reg_no=studentRepo.save(s).getRegNo();        
        return reg_no;
    }

    @Override
    public void updateStudent(Student s){
        studentRepo.save(s);
    }

    @Override
    public void deleteStudent(String reg){
        Student std=returnStudent(reg);
        studentRepo.delete(std);
    }

    @Override
    public Student returnStudent(String reg){
        Optional<Student> optionalStudent = studentRepo.findById(reg);
        return optionalStudent.orElse(null); 
        }
    
    

    @Override
    public List<Student> returnAllStudents(){
        return studentRepo.findAll();
    }

    @Override
    public boolean studentExist(String reg_no){
        return studentRepo.existsById(reg_no);
    }

    @Override
    @Transactional
    public String updateStudentByRegNo(String name,String reg_no){
        if(!studentRepo.existsById(reg_no)){
            throw new StudentNotFoundException(new StringBuffer()
            .append("Student '")
            .append(reg_no)
            .append("' doesn't exist")
            .toString());
        }

        return studentRepo.updateStudentByRegNo(name, reg_no);
    }
}
