package com.learn.learn.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.learn.learn.Model.Subject;
import com.learn.learn.Model.Student.Student;
import com.learn.learn.Model.Teacher.Teacher;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long> {

    // Query to find all subjects with associated students and teachers
    @Query("SELECT s FROM Subject s JOIN FETCH s.student JOIN FETCH s.teacher")
    List<Subject> findAllWithStudentsAndTeacher();

    // Query to find a subject by its ID
    Optional<Subject> findById(Long id);

    // Query to find all subjects by the name of the teacher
    List<Subject> findAllByTeacherName(String teacherName);

    // Query to find all subjects by the name of the student
    List<Subject> findAllByStudentName(String studentName);

    List<Subject> findAllByTeacherId(Long id);

    //List<Subject> findAllByStudentId(String id);
}