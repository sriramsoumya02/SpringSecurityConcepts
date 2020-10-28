package com.soumya.springsecurity.student;

public class Student {
    private final long Id;
    private final String password;

    public Student(long id, String password) {
        Id = id;
        this.password = password;
    }

    public long getId() {
        return Id;
    }

    public String getPassword() {
        return password;
    }
}
