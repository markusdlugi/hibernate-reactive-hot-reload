package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "fruits")
@NamedQuery(name = "fruits.findAll", query = "select f from Fruit f")
public class Fruit {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Size(max = 100)
    private String name;

    public Fruit(String name) {
        this.name = name;
    }

    public Fruit() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
