package com.learn.learn.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.learn.Model.Marks;
import com.learn.learn.Repository.MarksRepo;

@Service
public class MarksServiceImpl implements MarksServices {
    

    @Autowired
    private MarksRepo marksRepo;

    @Override
    public void setMarks(String reg_no, Marks marks) {
        // TODO Auto-generated method stub
    }

    @Override
    public List<Marks> getMarks(String reg_no) {
        return null;
        // TODO Auto-generated method stub
    }

    @Override
    public List<Marks> getSubjectMarks(String subject, LocalDate examDate) {
        return null;
        // TODO Auto-generated method stub
    }

    @Override
    public void updateMarksForStudentsInExam(String subject, LocalDate examDate, List<String> regNos, int marks) {
        // TODO Auto-generated method stub
    }
}
