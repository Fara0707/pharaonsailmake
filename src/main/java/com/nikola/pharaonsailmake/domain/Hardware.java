package com.nikola.pharaonsailmake.domain;


import javax.persistence.*;

@Entity
@Table(name = "Hardwares")

public class Hardware {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Name", length = 50)
    private String Name;

    @Column(name = "Price")
    private Double Price;

    @ManyToOne
    @JoinColumn(name = "Type")
    private HardwareType Type_id;

    public Hardware() {
    }

    public Hardware(String name, Double price, HardwareType type) {
        Name = name;
        Price = price;
        Type_id = type;
    }

    public Integer getId() {
        return Id;
    }

    public Hardware setId(Integer id) {
        Id = id;
        return this;
    }

    public String getName() {
        return Name;
    }

    public Hardware setName(String name) {
        Name = name;
        return this;
    }

    public Double getPrice() {
        return Price;
    }

    public Hardware setPrice(Double price) {
        Price = price;
        return this;
    }

    public HardwareType getType() {
        return Type_id;
    }

    public Hardware setType(HardwareType type) {
        Type_id = type;
        return this;
    }
}
