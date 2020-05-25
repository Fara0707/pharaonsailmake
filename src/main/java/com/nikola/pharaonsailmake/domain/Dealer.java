package com.nikola.pharaonsailmake.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Dealers")
public class Dealer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Name", length = 50)
    private String Name;

    public Dealer() {
    }

    public Dealer(String name) {
        Name = name;
    }

    public Integer getId() {
        return Id;
    }

    public Dealer setId(Integer id) {
        Id = id;
        return this;
    }

    public String getName() {
        return Name;
    }

    public Dealer setName(String name) {
        Name = name;
        return this;
    }
}
