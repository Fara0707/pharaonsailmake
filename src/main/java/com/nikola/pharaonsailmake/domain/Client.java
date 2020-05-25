package com.nikola.pharaonsailmake.domain;

import javax.persistence.*;

@Entity
@Table(name = "Clients")

public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @Column(name = "SurName", length = 50)
    private String SurName;

    @Column(name = "FirstName", length = 50)
    private String FirstName;

    @Column(name = "Town", length = 50)
    private String Town;

    @Column(name = "MobileTel", length = 50)
    private String MobTel;

    @Column(name = "NewPostNumber")
    private Integer NewPostNumber;

    public Client(String surName, String firstName, String town, String mobTel, Integer newPostNumber) {
        SurName = surName;
        FirstName = firstName;
        Town = town;
        MobTel = mobTel;
        NewPostNumber = newPostNumber;
    }

    public Client() {
    }

    public Integer getId() {
        return Id;
    }

    public Client setId(Integer id) {
        Id = id;
        return this;
    }

    public String getSurName() {
        return SurName;
    }

    public Client setSurName(String surName) {
        SurName = surName;
        return this;
    }

    public String getFirstName() {
        return FirstName;
    }

    public Client setFirstName(String firstName) {
        FirstName = firstName;
        return this;
    }

    public String getTown() {
        return Town;
    }

    public Client setTown(String town) {
        Town = town;
        return this;
    }

    public String getMobTel() {
        return MobTel;
    }

    public Client setMobTel(String mobTel) {
        MobTel = mobTel;
        return this;
    }

    public Integer getNewPostNumber() {
        return NewPostNumber;
    }

    public Client setNewPostNumber(Integer newPostNumber) {
        NewPostNumber = newPostNumber;
        return this;
    }
}
