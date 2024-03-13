package com.learn.learn.Service.Subject;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.learn.Model.Subject;
import com.learn.learn.Model.Student.Student;
import com.learn.learn.Model.Teacher.Teacher;
import com.learn.learn.Repository.SubjectRepo;
import com.learn.learn.Repository.Student.StudentRepo;
import com.learn.learn.Repository.Teacher.TeacherRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class SubjectServicesImpl implements SubjectServices {

    @Autowired
    private SubjectRepo subRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public void saveSubject(Subject sub) {
        if (subRepo.findById(sub.getId()).isPresent()) {
            throw new IllegalArgumentException("Subject with ID " + sub.getId() + " already exists");
        } else {
            subRepo.save(sub);
        }
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subRepo.findById(id).orElse(null);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subRepo.findAll();
    }

    @Override
    public void deleteSubjectById(Long id) {
        Subject sub = subRepo.findById(id).orElse(null);
        if (sub == null) {
            throw new EntityNotFoundException("Subject with ID " + id + " not found");
        } else {
            subRepo.delete(sub);
        }
    }

    @Override
    public void assignTeacherToSubject(Long subId, Long teacherId) {
        Optional<Teacher> teacherOptional = teacherRepo.findById(teacherId);
        Optional<Subject> subjectOptional = subRepo.findById(subId);

        if (subjectOptional.isPresent() && teacherOptional.isPresent()) {
            Subject sub = subjectOptional.get();
            Teacher teach = teacherOptional.get();
            sub.setTeacher(teach);
            subRepo.save(sub);
        } else {
            throw new EntityNotFoundException("Subject or Teacher not found");
        }
    }

    @Override
    public void assignStudentToSubject(Long id, String reg_no) {
        Optional<Student> std = studentRepo.findById(reg_no);
        Optional<Subject> sub = subRepo.findById(id);

        if (std.isPresent() && sub.isPresent()) {
            Student s = std.get();
            Subject j = sub.get();
            List<Student> studentsExisting = j.getStudents();
            studentsExisting.add(s);
            j.setStudents(studentsExisting);
            subRepo.save(j);
        } else {
            throw new EntityNotFoundException("Student or Subject not found");
        }
    }

    @Override
    public void deleteAllSubjectByTeacherId(Long teacherId) {
        List<Subject> subjects = subRepo.findAllByTeacherId(teacherId);
        subRepo.deleteAll(subjects);
    }

    @Override
    public void deleteStudentfromSubject(Long subID, String id) {
        Subject sub = subRepo.findById(subID).orElse(null);

        if (sub != null) {
            List<Student> students = sub.getStudents();
            students.removeIf(s -> s.getRegNo().equals(id));
            subRepo.save(sub);
        }
    }

    @Override
    public void deleteSubjectByTeacherId(Long subId, Long teacherId) {
        Subject sub = subRepo.findById(subId).orElse(null);

        if (sub != null && sub.getTeacher().getId().equals(teacherId)) {
            subRepo.delete(sub);
        } else {
            throw new EntityNotFoundException("Subject with ID " + subId + " or Teacher with ID " + teacherId + " not found");
        }
    }

    @Override
    public void deleteStudentFromAllSubject(String id) {
        Student std = studentRepo.findById(id).orElse(null);
        if (std != null) {
            List<Subject> subjects = subRepo.findAllByStudentName(std.getName());
            for (Subject sub : subjects) {
                List<Student> students = sub.getStudents();
                students.removeIf(s -> s.getRegNo().equals(id));
                subRepo.save(sub);
            }
        } else {
            throw new EntityNotFoundException("No student with ID " + id + " found");
        }
    }
}
