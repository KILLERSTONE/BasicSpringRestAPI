package com.learn.learn.Service.Marks;

import java.time.LocalDate;
import java.util.List;

import com.learn.learn.Model.Marks;
import com.learn.learn.Model.Student.Student;

public interface MarksServices {
    
    void setMarks(String reg_no,Marks marks);
    List<Marks> getMarks(String reg_no);
    List<Marks> getSubjectMarks(String subject,LocalDate examDate);
    void updateMarksForStudentsInExam(String subject, LocalDate examDate, List<String> regNos, int marks);
    void deleteMarksByStudent(Student std);


}
