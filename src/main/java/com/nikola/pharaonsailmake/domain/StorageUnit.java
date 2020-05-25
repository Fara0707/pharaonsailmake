package com.nikola.pharaonsailmake.domain;

import javax.persistence.*;

@Entity
@Table(name = "StorageUnits")

public class StorageUnit {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Town", length = 50)
    private String Town;

    @Column(name = "Address", length = 100)
    private String Address;

    public StorageUnit(String town, String address) {
        Town = town;
        Address = address;
    }

    public StorageUnit() {
    }

    public Integer getId() {
        return Id;
    }

    public StorageUnit setId(Integer id) {
        Id = id;
        return this;
    }

    public String getTown() {
        return Town;
    }

    public StorageUnit setTown(String town) {
        Town = town;
        return this;
    }

    public String getAddress() {
        return Address;
    }

    public StorageUnit setAddress(String address) {
        Address = address;
        return this;
    }
}
