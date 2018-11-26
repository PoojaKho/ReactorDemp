package com.example.reactordemo.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("people_by_first_name")
public class Person {
    @PrimaryKey
    private PersonKey key;

    public Person(PersonKey key, String lastName, double salary) {
        this.key = key;
        this.lastName = lastName;
        this.salary = salary;
    }

    @Column("last_name")
    private String lastName;


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public PersonKey getKey() {
        return key;
    }

    public void setKey(PersonKey key) {
        this.key = key;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column

    private double salary;
}
