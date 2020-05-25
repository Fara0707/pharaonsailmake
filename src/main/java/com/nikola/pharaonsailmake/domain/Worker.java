package com.nikola.pharaonsailmake.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Workers")
public class Worker {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Name", length = 50)
    private String Name;

    @Column(name = "WorkMeter")
    private Double workMeter;

    public Worker(String name, Double workMeter) {
        Name = name;
        this.workMeter = workMeter;
    }

    public Worker() {
    }

    public Integer getId() {
        return Id;
    }

    public Worker setId(Integer id) {
        Id = id;
        return this;
    }

    public String getName() {
        return Name;
    }

    public Worker setName(String name) {
        Name = name;
        return this;
    }

    public Double getWorkMeter() {
        return workMeter;
    }

    public Worker setWorkMeter(Double workMeter) {
        this.workMeter = workMeter;
        return this;
    }
}
