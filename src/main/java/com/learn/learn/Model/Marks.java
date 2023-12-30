package com.learn.learn.Model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="Marks")
public class Marks {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String subject ;

    private LocalDate doe;

    private Integer marks;


    @ManyToOne
    @JoinColumn(name="student_reg_no")
    private Student student;


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }


    public LocalDate getDoe() {
        return doe;
    }


    public void setDoe(LocalDate doe) {
        this.doe = doe;
    }


    public Integer getMarks() {
        return marks;
    }


    public void setMarks(Integer marks) {
        this.marks = marks;
    }


    public Student getStudent() {
        return student;
    }


    public void setStudent(Student student) {
        this.student = student;
    }
    

}
