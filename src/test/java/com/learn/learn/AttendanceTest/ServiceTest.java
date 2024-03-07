package com.learn.learn.AttendanceTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.learn.Model.Student.Student;
import com.learn.learn.Model.Student.StudentAttendance;
import com.learn.learn.Repository.Student.StudentAttendanceRepo;
import com.learn.learn.Repository.Student.StudentRepo;
import com.learn.learn.Service.Student.StudentAttendanceServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceTest {

    @Mock
    private StudentAttendanceRepo attendanceRepo;

    @Mock
    private StudentRepo  studentRepo;

    @InjectMocks
    private StudentAttendanceServiceImpl attendanceService;

    @Test
    public void testSetAttendance() {
        String regNo = "20TEST0001";
        LocalDate date = LocalDate.now();
        StudentAttendance attendance = new StudentAttendance();
        // Set necessary fields in the attendance object for testing

        Student student = new Student();
        // Set necessary fields in the student object for testing

        when(studentRepo.findById(regNo)).thenReturn(java.util.Optional.of(student));
        when(attendanceRepo.findByStudentAndDate(student, date)).thenReturn(null);

        attendanceService.setAttendance(regNo, attendance);
        // Add assertions to verify the behavior of the method
    }

    @Test
    public void testGetAttendance() {
        String regNo = "20TEST0001";
        Student student = new Student();
        // Set necessary fields in the student object for testing

        when(studentRepo.findById(regNo)).thenReturn(java.util.Optional.of(student));
        when(attendanceRepo.findAllByStudent(student)).thenReturn(new ArrayList<>());

        List<StudentAttendance> result = attendanceService.getAttendance(regNo);
        // Add assertions to verify the behavior of the method
    }

    @Test
    public void testGetDayAttendance() {
        LocalDate date = LocalDate.now();
        when(attendanceRepo.findAllByDate(date)).thenReturn(new ArrayList<>());

        List<StudentAttendance> result = attendanceService.getDayAttendance(date);
        // Add assertions to verify the behavior of the method
    }

    @Test
    public void testMarkAttendanceForStudentsOnDate() {
        LocalDate date = LocalDate.now();
        String status = "Present";
        List<String> regNos = new ArrayList<>();
        regNos.add("RegNo1");
        regNos.add("RegNo2");

        Student student1 = new Student();
        Student student2 = new Student();
        // Set necessary fields in the student objects for testing

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);

        when(studentRepo.findAllById(regNos)).thenReturn(students);
        when(attendanceRepo.findByStudentAndDate(student1, date)).thenReturn(null);
        when(attendanceRepo.findByStudentAndDate(student2, date)).thenReturn(new StudentAttendance());

        attendanceService.markAttendanceForStudentsOnDate(regNos, date, status);
        // Add assertions to verify the behavior of the method
    }
}
