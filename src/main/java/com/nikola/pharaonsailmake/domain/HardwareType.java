package com.nikola.pharaonsailmake.domain;

import javax.persistence.*;

@Entity
@Table(name = "HardwareType")

public class HardwareType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Type")
    private String Type;

    public HardwareType() {
    }

    public HardwareType(String type) {
        Type = type;
    }

    public Integer getId() {
        return Id;
    }

    public HardwareType setId(Integer id) {
        Id = id;
        return this;
    }

    public String getType() {
        return Type;
    }

    public HardwareType setType(String type) {
        Type = type;
        return this;
    }
}
