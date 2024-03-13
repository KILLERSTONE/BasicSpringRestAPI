package com.learn.learn.Controller.Teacher;

import java.net.http.HttpResponse.ResponseInfo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.learn.Exception.UserNotFoundException;
import com.learn.learn.Model.Subject;
import com.learn.learn.Model.Student.Student;
import com.learn.learn.Model.Student.StudentAttendance;
import com.learn.learn.Model.Teacher.Teacher;
import com.learn.learn.Model.Teacher.TeacherAttendance;
import com.learn.learn.Service.Subject.SubjectServices;
import com.learn.learn.Service.Teacher.TeacherAttendanceService;
import com.learn.learn.Service.Teacher.TeacherServices;

import jakarta.security.auth.message.callback.PrivateKeyCallback.SubjectKeyIDRequest;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class TeacherController {

    @Autowired
    private TeacherServices teacherServices;

    @Autowired
    private TeacherAttendanceService teacherAttendance;

    @Autowired
    private SubjectServices subjectServices;

    @PostMapping("/teacher")
    public ResponseEntity<?> saveTeacher(@RequestBody Teacher teacher) {
        try {
            Long id = teacher.getId();

            if (id == null)
                return new ResponseEntity<>("Unique ID is required", HttpStatus.BAD_REQUEST);

            if (teacherServices.getTeacherById(id) != null) {
                return new ResponseEntity<>("Teacher with this ID already exists", HttpStatus.CONFLICT);

            }

            teacherServices.saveTeacher(teacher);
            return new ResponseEntity<>("Teacher Created Succesfully", HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to save teacher", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/teachers")
    public ResponseEntity<?> getAllTeachers() {

        try {
            List<Teacher> teachers = teacherServices.getAllTeachers();

            return new ResponseEntity<>(teachers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to list teachers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<?> getTeacher(@PathVariable String id) {
        try {
            Teacher teach = teacherServices.getTeacherById(Long.parseLong(id));
            return new ResponseEntity<>(teach, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>("Unable to fetch teacher", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/teacher/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable String id, @RequestBody Teacher newteacher) {
        try {
            Teacher teach = teacherServices.getTeacherById(Long.parseLong(id));
            teach.updateTeacherInfo(newteacher);
            teacherServices.updateTeacher(newteacher);
            saveAttendanceAndSubjectForTeacher(newteacher);

            return new ResponseEntity<>("Teacher updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Cant find a teacher with that id", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable String id) {
        try {
            Teacher teach = teacherServices.getTeacherById(Long.parseLong(id));
            deleteAttendanceAndSubjectForTeacher(teach);

            return new ResponseEntity<>("Successfully deleted teacher", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Can't Delete Teacher with this id", HttpStatus.BAD_REQUEST);
        }

    }

    public void saveAttendanceAndSubjectForTeacher(Teacher teacher) {
        // Save attendance for the student

        List<TeacherAttendance> attendance = teacher.getAttendances();
        List<Subject> subjects = teacher.getSubjects();

        if (attendance != null) {

            for (TeacherAttendance attd : attendance) {
                teacherAttendance.setAttendance(teacher.getId(), attd);
            }

        }

        if (subjects != null) {
            for (Subject sub : subjects) {
                subjectServices.assignTeacherToSubject(sub.getId(), teacher.getId());
            }
        }
    }

    public void deleteAttendanceAndSubjectForTeacher(Teacher teacher) {
        Long id = teacher.getId();

        teacherAttendance.deleteAttendanceByTeacherId(id);
        subjectServices.deleteAllSubjectByTeacherId(id);
        teacherServices.deleteTeacherById(id);
    }
}
