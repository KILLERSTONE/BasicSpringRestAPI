package com.learn.learn.Model.Student;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learn.learn.Model.Subject;

import jakarta.persistence.*;

@Entity
@Table(name = "Attendance")
public class StudentAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    private String status;

    @ManyToOne
    @JoinTable(name = "student_reg_no")
    private Student student;

    @ManyToOne
    @JoinTable(name = "subject_id")
    private Subject subject;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentAttendance() {
    }

}
