    package com.learn.learn.Model;

    import java.util.List;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import com.learn.learn.Model.Student.Student;
    import com.learn.learn.Model.Student.StudentAttendance;
    import com.learn.learn.Model.Teacher.Teacher;
    import com.learn.learn.Model.Teacher.TeacherAttendance;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.ManyToMany;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.OneToMany;
    import jakarta.persistence.OneToOne;
    import jakarta.persistence.Table;

    @Entity
    @Table(name = "Subject")
    public class Subject {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        // Other subject-related fields and methods

        @ManyToMany(mappedBy = "subjects")
        private List<Student> student;

        @ManyToOne()
        @JsonBackReference
        private Teacher teacher;

        // Getters and setters

        public Subject() {
        }




    }
