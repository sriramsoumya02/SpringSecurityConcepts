package com.soumya.springsecurity.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static List<Student> students = Arrays.asList(
            new Student(1, "soumya"),
            new Student(2, "Rishi"),
            new Student(3, "Harsha")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable long studentId) {
        return students.stream().filter(student -> student.getId() == studentId).findFirst().orElseThrow(() -> new IllegalStateException("Student " + studentId + " not exists"));
    }
}
