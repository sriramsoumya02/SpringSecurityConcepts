package com.soumya.springsecurity.student;

public class Student {
    private final long Id;
    private final String name;

    public Student(long id, String name) {
        Id = id;
        this.name = name;
    }

    public long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }
}
