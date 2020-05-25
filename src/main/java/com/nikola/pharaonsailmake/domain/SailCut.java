package com.nikola.pharaonsailmake.domain;

import javax.persistence.*;

@Entity
@Table(name = "SailCuts")

public class SailCut {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Cut")
    private String Cut;

    public SailCut() {
    }

    public SailCut(String cut) {
        Cut = cut;
    }

    public Integer getId() {
        return Id;
    }

    public SailCut setId(Integer id) {
        Id = id;
        return this;
    }

    public String getCut() {
        return Cut;
    }

    public SailCut setCut(String cut) {
        Cut = cut;
        return this;
    }
}
