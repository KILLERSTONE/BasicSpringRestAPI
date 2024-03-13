package com.learn.learn.Controller.Subject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.learn.Model.Subject;
import com.learn.learn.Service.Student.StudentServices;
import com.learn.learn.Service.Subject.SubjectServices;
import com.learn.learn.Service.Teacher.TeacherServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class SubjectController {

    @Autowired
    private TeacherServices teacherServices;

    @Autowired
    private StudentServices studentServices;

    @Autowired
    private SubjectServices subjectServices;

    @GetMapping("/subjects")
    public ResponseEntity<?> getAllSubject() {
        List<Subject> subjs = subjectServices.getAllSubjects();

        if (subjs == null)
            return new ResponseEntity<>("No Subject found", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(subjs, HttpStatus.OK);
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable String id) {
        Long subId = Long.parseLong(id);
        Subject subject = subjectServices.getSubjectById(subId);

        if (subject == null)
            return new ResponseEntity<>("No Subject with this id found", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(subject, HttpStatus.OK);

    }

    @PostMapping("/subject")
    public ResponseEntity<?> saveSubject(@RequestBody Subject sub) {

        try {
            Long id = sub.getId();

            if (subjectServices.getSubjectById(id) != null) {
                return new ResponseEntity<>("A Subject having the same id already exists", HttpStatus.CONFLICT);
            }
            subjectServices.saveSubject(sub);
            return new ResponseEntity<>("Successfully saved the subject", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Cannot save the subject", HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/subject/{id}")
    public ResponseEntity<?> editSubject(@PathVariable String id, @RequestBody Subject newSub) {

        try {
            Long checkId = Long.parseLong(id);
            Subject sub = subjectServices.getSubjectById(checkId);

            if (checkId != newSub.getId())
                return new ResponseEntity<>("Not the same entity so cant modifty", HttpStatus.BAD_REQUEST);
            if (sub == null)
                return new ResponseEntity<>("No Subject with the id found to update", HttpStatus.NOT_FOUND);

            subjectServices.deleteSubjectById(Long.parseLong(id));
            subjectServices.saveSubject(newSub);

            return new ResponseEntity<>("Successfully modified the Subject", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Cant Edit the user", HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/subject/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable String id) {
        try {
            Long subId = Long.parseLong(id);

            Subject sub = subjectServices.getSubjectById(subId);

            if (sub == null)
                return new ResponseEntity<>("No subject with id found to delete", HttpStatus.NOT_FOUND);

            subjectServices.deleteSubjectById(subId);
            return new ResponseEntity<>("Successfully deleted the subject with that id", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Cant delete subject", HttpStatus.BAD_REQUEST);
        }
    }
}
