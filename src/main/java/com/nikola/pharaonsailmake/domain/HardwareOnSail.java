package com.nikola.pharaonsailmake.domain;


import javax.persistence.*;

@Entity
@Table(name = "HardwareOnSail")

public class HardwareOnSail {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "DeliverieHardware_id")
    private DeliverieHardware deliverieHardware_id;

    @ManyToOne
    @JoinColumn(name = "Sail_id")
    private Sail sail_id;

    @Column(name = "NumberOfHardware")
    private Integer NumberOfHardware;

    @Column(name = "Price")
    private Double Price;


    public Integer getId() {
        return Id;
    }

    public HardwareOnSail setId(Integer id) {
        Id = id;
        return this;
    }

    public DeliverieHardware getDeliverieHardware_id() {
        return deliverieHardware_id;
    }

    public HardwareOnSail setDeliverieHardware_id(DeliverieHardware deliverieHardware_id) {
        this.deliverieHardware_id = deliverieHardware_id;
        return this;
    }

    public Sail getSail_id() {
        return sail_id;
    }

    public HardwareOnSail setSail_id(Sail sail_id) {
        this.sail_id = sail_id;
        return this;
    }

    public Integer getNumberOfHardware() {
        return NumberOfHardware;
    }

    public HardwareOnSail setNumberOfHardware(Integer numberOfHardware) {
        NumberOfHardware = numberOfHardware;
        return this;
    }

    public Double getPrice() {
        return Price;
    }

    public HardwareOnSail setPrice(Double price) {
        Price = price;
        return this;
    }

    public HardwareOnSail() {
    }

    public HardwareOnSail(DeliverieHardware deliverieHardware_id, Sail sail_id, Integer numberOfHardware, Double price) {
        this.deliverieHardware_id = deliverieHardware_id;
        this.sail_id = sail_id;
        NumberOfHardware = numberOfHardware;
        Price = price;
    }
}
