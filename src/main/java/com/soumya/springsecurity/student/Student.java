package com.soumya.springsecurity.student;

public class Student {
    private long id;
    private String name;

    protected Student() {

    }

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id=" + this.id +
                ", name='" + name + '\'' +
                '}';
    }
}
