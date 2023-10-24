package ru.kudryashov.springCrudApp.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private Integer id;

    @NotEmpty(message = "Name cant be empty")
    @Size(min = 2, max = 30, message = "Name size 2 - 30 characters")
    private String name;

    @Min(value = 1, message = "Age should be greater than 1")
    private Integer age;

    @NotEmpty(message = "Email cant be empty")
    @Email
    private String email;

    public Person() {
    }

    public Person(Integer id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
