package com.ukma.tests;

import com.ukma.entity.Person;
import com.ukma.entity.Student;
import com.ukma.runtime.annotations.Test;
import com.ukma.annotations.validators.AnnotationValidator;
import com.ukma.validators.PersonNullValidator;
import com.ukma.validators.StudentNullValidator;
import static org.assertj.core.api.Assertions.assertThat;


public class UnitTests {

    @Test("Student NullValidator test")
    public void studentNullValidatorTest(){
        Student student = new Student(
                "Volodymyr",
                "+380666666666",
                "vova123@mail.com",
                "FI"
        );
        Student nullStudent = null;
        Student studentWithNullField = new Student(
                "Volodymyr",
                "+380666666666",
                "vova123@mail.com",
                null
        );
            StudentNullValidator studentNullValidator = new StudentNullValidator();
            assertThat(studentNullValidator.validate(student)).isTrue();
            assertThat(studentNullValidator.validate(nullStudent)).isFalse();
            assertThat(studentNullValidator.validate(studentWithNullField)).isFalse();
    }

    @Test("Person NullValidator test")
    public void personNullValidatorTest(){
        Person person = new Person(
                "Volodymyr",
                32,
                "Ukraine",
                "lawyer"
        );
        Person nullPerson = null;
        Person personWithNullField = new Person(
                "Volodymyr",
                32,
                "Ukraine",
                null
        );

            PersonNullValidator personNullValidator = new PersonNullValidator();
            assertThat(personNullValidator.validate(person)).isTrue();
            assertThat(personNullValidator.validate(nullPerson)).isFalse();
            assertThat(personNullValidator.validate(personWithNullField)).isFalse();
    }

    @Test("Correct student AnnotationValidator test")
    public void studentAnnotationValidatorTest(){
        AnnotationValidator annotationValidator = new AnnotationValidator();
        Student student = new Student(
                "Volodymyr",
                "+380666666666",
                "vova123@mail.com",
                "FI"
        );

        assertThat(annotationValidator.validate(student)).isTrue();
    }

    @Test("Student with incorrect name length AnnotationValidator test")
    public void studentAnnotationValidatorFailedNameTest(){
        AnnotationValidator annotationValidator = new AnnotationValidator();
        Student student = new Student(
                "Volodymyrrrrrrrrrr",
                "+380666666666",
                "vova123@mail.com",
                "FI"
        );

        assertThat(annotationValidator.validate(student)).isFalse();
    }

    @Test("Student with incorrect phone format AnnotationValidator test")
    public void studentAnnotationValidatorFailedPhoneTest(){
        AnnotationValidator annotationValidator = new AnnotationValidator();
        Student student = new Student(
                "Volodymyr",
                "  ",
                "vova123@mail.com",
                "FI"
        );

        assertThat(annotationValidator.validate(student)).isFalse();
    }
}
