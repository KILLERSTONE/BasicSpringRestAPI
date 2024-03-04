package com.learn.learn.Model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;


@Entity
@Table(name="Student")
public class Student {

    
    @Id
    @Column(name="reg_no")
    private String regNo;

    private String name;

    private LocalDate dob;

    private String gender;

    private String address;

    private String phone_no;

    private String email;

    private String guardianName;

    private String guardianPhone;

    private LocalDate doj;

    private String branch;

    @JsonManagedReference
    @OneToMany(mappedBy="student")
    private List<Marks> marks;
    
    @JsonManagedReference
    @OneToMany(mappedBy="student")
    private List<Attendance> attendance;

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public List<Marks> getMarks() {
        return marks;
    }

    public void setMarks(List<Marks> marks) {
        this.marks = marks;
    }

    public List<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Attendance> attendance) {
        this.attendance = attendance;
    }

    public void updateStudentInfo(Student updatedStudent) {
        this.setName(updatedStudent.getName());
        this.setDob(updatedStudent.getDob());
        this.setGender(updatedStudent.getGender());
        this.setAddress(updatedStudent.getAddress());
        this.setPhone_no(updatedStudent.getPhone_no());
        this.setEmail(updatedStudent.getEmail());
        this.setGuardianName(updatedStudent.getGuardianName());
        this.setGuardianPhone(updatedStudent.getGuardianPhone());
        this.setDoj(updatedStudent.getDoj());
        this.setBranch(updatedStudent.getBranch());
    }

    
}
