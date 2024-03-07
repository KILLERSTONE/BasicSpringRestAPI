package com.learn.learn.Model.Teacher;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learn.learn.Model.Subject;
import com.learn.learn.Model.Student.StudentAttendance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private String gender;

    private String address;

    private String phoneNumber;

    private String email;

    private String designation;

    @JsonManagedReference
    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects;

    @JsonManagedReference
    @OneToMany(mappedBy = "teacher")
    private List<TeacherAttendance> attendances;

    public Teacher() {
    }

    public Teacher(Long id, String name, LocalDate dateOfBirth, String gender, String address, String phoneNumber,
            String email, String designation, List<Subject> subjects, List<TeacherAttendance> attendances) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.designation = designation;
        this.subjects = subjects;
        this.attendances = attendances;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<TeacherAttendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<TeacherAttendance> attendances) {
        this.attendances = attendances;
    }

    // // Method to access/update student attendance for a specific subject
    // public void updateStudentAttendance(Subject subject, StudentAttendance
    // studentAttendance) {
    // // Check if the teacher is assigned to the given subject
    // if (subjects.contains(subject)) {
    // // Update the student attendance
    // subject.updateStudentAttendance(studentAttendance);
    // } else {
    // // Handle case where teacher is not assigned to the subject
    // System.out.println("Teacher is not assigned to the subject");
    // }
    // }
}
