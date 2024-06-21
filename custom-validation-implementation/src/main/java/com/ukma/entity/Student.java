package com.ukma.entity;

import com.ukma.runtime.annotations.Length;
import com.ukma.runtime.annotations.NotBlank;
import com.ukma.annotations.NullValidator;

@NullValidator
public class Student {

    @Length(min = 3, max = 10)
    private String name;
    @NotBlank
    private String phone;
    private String email;
    private String faculty;

    public Student(
            String name,
            String phone,
            String email,
            String faculty
    ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getFaculty() {
        return faculty;
    }

    @Override
    public String toString() {
        return "Student(" +
               "name='" + name + '\'' +
               ", phone='" + phone + '\'' +
               ", email='" + email + '\'' +
               ", faculty='" + faculty + '\'' +
               ')';
    }
}
