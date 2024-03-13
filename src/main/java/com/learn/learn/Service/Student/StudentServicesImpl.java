package com.learn.learn.Service.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.learn.Exception.StudentNotFoundException;
import com.learn.learn.Model.Subject;
import com.learn.learn.Model.Student.Student;
import com.learn.learn.Model.Student.StudentAttendance;
import com.learn.learn.Repository.SubjectRepo;
import com.learn.learn.Repository.Student.StudentRepo;
import com.learn.learn.Service.Marks.MarksServices;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class StudentServicesImpl implements StudentServices {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private SubjectRepo subRepo;

    private StudentAttendanceServices attendanceService;
    private MarksServices marksService;

    public StudentServicesImpl(StudentAttendanceServices attendanceService, MarksServices marksServices) {
        this.attendanceService = attendanceService;
        this.marksService = marksServices;
    }

    @Override
    public String saveStudent(Student s) {

        List<Subject> savedSubjects=new ArrayList<>();

        for(Subject sub:s.getSubjects()){
            if(sub.getId()==null)savedSubjects.add(subRepo.save(sub));
            else savedSubjects.add(sub);
        }

        s.setSubjects(savedSubjects);
        String reg_no = studentRepo.save(s).getRegNo();
        return reg_no;
    }

    @Override
    public void updateStudent(Student s) {
        studentRepo.save(s);
    }

    @Override
    public void deleteStudent(String reg) {
        Student std = returnStudent(reg);
        marksService.deleteMarksByStudent(std);
        attendanceService.deleteAttendanceByStudent(std);
        studentRepo.delete(std);
    }

    @Override
    public Student returnStudent(String reg_no) {
        if (studentExist(reg_no)) {
            return studentRepo.findById(reg_no).orElseThrow(StudentNotFoundException::new);
        }
        throw new StudentNotFoundException();
    }
    
    @Override
    public List<Student> returnAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public boolean studentExist(String reg_no) {
        return studentRepo.existsById(reg_no);
    }

    @Override
    @Transactional
    public String updateStudentByRegNo(String name, String reg_no) {
        if (!studentRepo.existsById(reg_no)) {
            throw new StudentNotFoundException(new StringBuffer()
                    .append("Student '")
                    .append(reg_no)
                    .append("' doesn't exist")
                    .toString());
        }

        return studentRepo.updateStudentByRegNo(name, reg_no);
    }

    @Override
    public void editStudent(String reg_no, Student newStd) {
        Optional<Student> existing = studentRepo.findById(reg_no);

        if (existing.isPresent()) {
            Student exist = existing.get();
            BeanUtils.copyProperties(newStd, existing, "regNo");
            studentRepo.save(exist);
        } else {
            studentRepo.save(newStd);
        }
    }

}
