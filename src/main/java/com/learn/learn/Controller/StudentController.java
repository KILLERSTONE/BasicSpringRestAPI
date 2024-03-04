package com.learn.learn.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.learn.Exception.StudentNotFoundException;
import com.learn.learn.Model.Attendance;
import com.learn.learn.Model.Marks;
import com.learn.learn.Model.Student;
import com.learn.learn.Service.AttendanceServices;
import com.learn.learn.Service.MarksServices;
import com.learn.learn.Service.StudentServices;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentServices services;

    @Autowired
    private MarksServices marksServices;

    @Autowired
    private AttendanceServices attendanceService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/student")
    public ResponseEntity<?> saveStudents(@RequestBody Student std) {

        ResponseEntity<?> entity = null;

        try {

            String reg_no = std.getRegNo();

            if (reg_no == null)
                return new ResponseEntity<>("A unique registration number is needed" + reg_no,
                        HttpStatus.INTERNAL_SERVER_ERROR);

            if (services.studentExist(reg_no) == true)
                return new ResponseEntity<>("Student with this registration number already exists",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            reg_no = services.saveStudent(std);
            List<Marks> markList = std.getMarks();
            List<Attendance> attdList = std.getAttendance();

            if (markList != null) {
                for (Marks mark : markList) {
                    mark.setStudent(std);
                    marksServices.setMarks(reg_no, mark);
                }
            }

            if (attdList != null) {
                for (Attendance attd : attdList) {
                    attd.setStudent(std);
                    attendanceService.setAttendance(reg_no, attd);
                }
            }

            entity = new ResponseEntity<String>("Student '" + reg_no + "' created ", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>("Unable to save Student", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return entity;
    }

    @GetMapping("/all")
    public ResponseEntity<?> returnAllStudents() {
        ResponseEntity<?> entity = null;

        try {
            List<Student> stdList = services.returnAllStudents();
            entity = new ResponseEntity<List<Student>>(stdList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>("Unable to list Student", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return entity;
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> returnStudent(@PathVariable String id) {
        ResponseEntity<?> resp = null;

        try {
            Student std = services.returnStudent(id);
            resp = new ResponseEntity<Student>(std, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>("Unable to find Student", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;
    }

    @DeleteMapping("/student/{reg_no}")
    public ResponseEntity<?> deleteStudent(@PathVariable String reg_no) {
        ResponseEntity<?> resp = null;

        try {
            services.deleteStudent(reg_no);
            resp = new ResponseEntity<String>("Student '" + reg_no + "' deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>("Unable to delete Student", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return resp;
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable String reg_no, @RequestBody Student std) {
        ResponseEntity<?> resp = null;

        try {
            Student s = services.returnStudent(reg_no);
            s.updateStudentInfo(std);

            List<Marks> marksList = std.getMarks();
            List<Attendance> attdList = std.getAttendance();

            if (marksList != null) {
                for (Marks mark : marksList) {
                    mark.setStudent(std);
                    marksServices.setMarks(reg_no, mark);
                }
            }

            if (attdList != null) {
                for (Attendance attd : attdList) {
                    attendanceService.setAttendance(reg_no, attd);
                }
            }
            services.updateStudent(s);

            resp = new ResponseEntity<String>("Student Updated Successfully", HttpStatus.RESET_CONTENT);
        } catch (StudentNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>("Student Not Updated ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }
}
