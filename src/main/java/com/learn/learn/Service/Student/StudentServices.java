package com.learn.learn.Service.Student;

import java.util.List;

import com.learn.learn.Model.Student.Student;

public interface StudentServices {
    

    String saveStudent(Student student);
    
    void updateStudent(Student student);

    void deleteStudent(String reg_no);

    Student returnStudent(String id);

    List<Student> returnAllStudents();

    boolean studentExist(String reg_no);

    String updateStudentByRegNo(String name,String reg_no);

    void editStudent(String reg_no,Student newStd);



}
