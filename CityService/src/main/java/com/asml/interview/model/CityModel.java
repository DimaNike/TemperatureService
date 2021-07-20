package com.asml.interview.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class CityModel {

    @NotBlank(message = "Name is mandatory")
    @Column(unique = true)
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
