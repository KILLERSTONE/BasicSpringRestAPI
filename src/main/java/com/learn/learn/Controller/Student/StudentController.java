package com.learn.learn.Controller.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.learn.learn.Exception.StudentNotFoundException;
import com.learn.learn.Model.Student.Student;
import com.learn.learn.Model.Student.StudentAttendance;
import com.learn.learn.Model.Marks;
import com.learn.learn.Service.Marks.MarksServices;
import com.learn.learn.Service.Student.StudentAttendanceServices;
import com.learn.learn.Service.Student.StudentServices;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentServices studentServices;

    @Autowired
    private MarksServices marksServices;

    @Autowired
    private StudentAttendanceServices attendanceServices;

    @PostMapping("/student")
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        try {
            String regNo = student.getRegNo();
            if (regNo == null || regNo.isEmpty()) {
                return new ResponseEntity<>("A unique registration number is required", HttpStatus.BAD_REQUEST);
            }
            if (studentServices.studentExist(regNo)) {
                return new ResponseEntity<>("Student with this registration number already exists", HttpStatus.CONFLICT);
            }
            
            studentServices.saveStudent(student);
            saveMarksAndAttendance(student);
            
            return new ResponseEntity<>("Student created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to save Student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudents() {
        try {
            List<Student> students = studentServices.returnAllStudents();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to list Students", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student/{regNo}")
    public ResponseEntity<?> getStudent(@PathVariable String regNo) {
        try {
            Student student = studentServices.returnStudent(regNo);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to fetch Student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/student/{regNo}")
    public ResponseEntity<?> deleteStudent(@PathVariable String regNo) {
        try {
            studentServices.deleteStudent(regNo);
            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to delete Student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/student/{regNo}")
    public ResponseEntity<?> updateStudent(@PathVariable String regNo, @RequestBody Student updatedStudent) {
        try {
            Student student = studentServices.returnStudent(regNo);
            student.updateStudentInfo(updatedStudent);
            studentServices.updateStudent(student);
            
            saveMarksAndAttendance(updatedStudent);

            return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to update Student", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void saveMarksAndAttendance(Student student) {
        String regNo = student.getRegNo();
        List<Marks> marksList = student.getMarks();
        List<StudentAttendance> attendanceList = student.getAttendance();

        if (marksList != null) {
            for (Marks mark : marksList) {
                mark.setStudent(student);
                marksServices.setMarks(regNo, mark);
            }
        }

        if (attendanceList != null) {
            for (StudentAttendance attendance : attendanceList) {
                attendance.setStudent(student);
                attendanceServices.setAttendance(regNo, attendance);
            }
        }
    }
}
