package com.soumya.springsecurity.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    private static List<Student> students = Arrays.asList(
            new Student(1, "soumya"),
            new Student(2, "Rishi"),
            new Student(3, "Harsha")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudent() {
        return students;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void createStudent(@RequestBody Student student) {
        // students.add(student);
        System.out.println("Create Student");
    }

    @PutMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable long studentId, @RequestBody Student student) {
        System.out.println("update Student");
        System.out.println("studentId" + studentId);
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable long studentId) {
        System.out.println("delete Student");
        System.out.println("studentId" + studentId);
    }

}
