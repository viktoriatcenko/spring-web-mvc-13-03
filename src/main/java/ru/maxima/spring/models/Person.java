package ru.maxima.spring.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private Long id;

    @NotEmpty(message = "Name should not to be empty")
    @Size(min = 2, max = 50, message = "Lenght of name should be min 2 symbols, max 50 symbols")
    private String name;

    @Min(value = 0, message = "Age should be min 0 year")
    private int age;

    @NotEmpty(message = "Email should not to be empty")
    @Email(message = "EMail should be valid")
    private String email;

    public Person(Long id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
