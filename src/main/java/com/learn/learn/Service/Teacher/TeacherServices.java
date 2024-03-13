package com.learn.learn.Service.Teacher;

import java.util.List;

import com.learn.learn.Model.Teacher.Teacher;

public interface TeacherServices {
    Long saveTeacher(Teacher teacher);

    Teacher getTeacherById(Long id);

    List<Teacher> getAllTeachers();

    void deleteTeacherById(Long id);

    void deleteTeacherByName(String name);


    void updateTeacher(Teacher teacher);


}

//make a function that saves the Attendance and Subject for the student directly by accessing the TeacherAttendanceService and TeacherServices so i can directly call a single function to my controller class