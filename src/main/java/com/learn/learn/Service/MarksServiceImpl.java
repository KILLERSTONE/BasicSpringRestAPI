package com.learn.learn.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.learn.Exception.StudentNotFoundException;
import com.learn.learn.Model.Marks;
import com.learn.learn.Model.Student;
import com.learn.learn.Repository.MarksRepo;
import com.learn.learn.Repository.StudentRepo;

@Service
public class MarksServiceImpl implements MarksServices {
    

    @Autowired
    private MarksRepo marksRepo;
    @Autowired
    private StudentRepo stdRepo;


    @Override
    public void setMarks(String reg_no, Marks marks) {
        Student std=stdRepo.findById(reg_no).orElse(null);

        if(std==null)throw new StudentNotFoundException("No student of this reg no exists");
        Marks m=marksRepo.findByStudentAndSubject(std,marks.getSubject());

        if(m==null){
            m=new Marks();
            m.setExamDate(marks.getExamDate());
            m.setMarks(marks.getMarks());
            m.setStudent(std);
            m.setSubject(marks.getSubject());
        }
        else{
            m.setMarks(marks.getMarks());
        }

        marksRepo.save(m);
    }

    @Override
    public List<Marks> getMarks(String reg_no) {
        Student std=stdRepo.findById(reg_no).orElse(null);

        if(std==null)throw new StudentNotFoundException("No student of this reg no exists");
        
        List<Marks> marks=marksRepo.findAllByStudent(std);

        return marks;
    }

    @Override
    public List<Marks> getSubjectMarks(String subject, LocalDate examDate) {
        List<Marks> marks=marksRepo.findAllBySubjectAndExamDate(subject,examDate);
        return marks;
        
    }

    @Override
    public void updateMarksForStudentsInExam(String subject, LocalDate examDate, List<String> regNos, int marks) {
        List<Student> students=stdRepo.findAllById(regNos);

        for(Student std:students){
            Marks m=marksRepo.findByStudentAndSubjectAndExamDate(std, subject,examDate);

            if(m==null){
                m=new Marks();
                m.setExamDate(examDate);
                m.setMarks(marks);
                m.setStudent(std);
                m.setSubject(subject);
            }else{
                m.setMarks(marks);
            }

            marksRepo.save(m);
        }
    }

    @Override
    public void deleteMarksByStudent(Student std) {
        List<Marks> marks=marksRepo.findAllByStudent(std);
        marksRepo.deleteAll(marks);

    }
}
