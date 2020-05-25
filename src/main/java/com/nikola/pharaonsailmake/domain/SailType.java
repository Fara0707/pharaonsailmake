package com.nikola.pharaonsailmake.domain;

import javax.persistence.*;

@Entity
@Table(name = "SailType")

public class SailType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Type", length = 50)
    private String Type;

    public SailType(String type) {
        Type = type;
    }

    public SailType() {
    }

    public Integer getId() {
        return Id;
    }

    public SailType setId(Integer id) {
        Id = id;
        return this;
    }

    public String getType() {
        return Type;
    }

    public SailType setType(String type) {
        Type = type;
        return this;
    }
}
