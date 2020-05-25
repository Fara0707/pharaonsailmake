package com.nikola.pharaonsailmake.domain;

import javax.persistence.*;

@Entity
@Table(name = "ClothsType")

public class ClothType {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Type")
    private String Type;

    public ClothType() {
    }

    public ClothType(String type) {
        Type = type;
    }

    public Integer getId() {
        return Id;
    }

    public ClothType setId(Integer id) {
        Id = id;
        return this;
    }

    public String getType() {
        return Type;
    }

    public ClothType setType(String type) {
        Type = type;
        return this;
    }
}
