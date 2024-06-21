package com.ukma.entity;

import com.ukma.annotations.NullValidator;

@NullValidator
public class Person {

    private final String fullName;
    private final Integer age;
    private final String country;
    private final String occupation;


    public Person(String fullName, Integer age, String country, String occupation) {
        this.fullName = fullName;
        this.age = age;
        this.country = country;
        this.occupation = occupation;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getOccupation() {
        return occupation;
    }

    @Override
    public String toString() {
        return "Person(" +
               "fullName='" + fullName + '\'' +
               ", age=" + age +
               ", country='" + country + '\'' +
               ", occupation='" + occupation + '\'' +
               ')';
    }
}
