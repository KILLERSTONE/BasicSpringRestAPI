package com.learn.learn.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.learn.Model.Marks;
import com.learn.learn.Model.Student.Student;

@Repository
public interface MarksRepo extends JpaRepository<Marks,Long>{

    Marks findByStudentAndSubject(Student std, String subject);

    List<Marks> findAllByStudent(Student std);

    Marks findByStudentAndSubjectAndExamDate(Student std, String subject, LocalDate examDate);

    List<Marks> findAllBySubjectAndExamDate(String subject, LocalDate examDate);

    
}
