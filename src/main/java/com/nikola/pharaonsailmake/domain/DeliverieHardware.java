package com.nikola.pharaonsailmake.domain;

import javax.persistence.*;

@Entity
@Table(name = "DeliverieHardwares")

public class DeliverieHardware {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Price")
    private Double Price;

    @Column(name = "Data")
    private String Data;

    @Column(name = "NumberOf")
    private Integer Number;

    @ManyToOne
    @JoinColumn(name = "StorageUnit_id")
    private StorageUnit storageUnit_id;

    @ManyToOne
    @JoinColumn(name = "Dealer_id")
    private Dealer dealer_id;

    @ManyToOne
    @JoinColumn(name = "Hardware_id")
    private Hardware hardware_id;

    public DeliverieHardware() {
    }

    public DeliverieHardware(Double price, String data, Integer number, StorageUnit storageUnit_id, Dealer dealer_id, Hardware hardware_id) {
        Price = price;
        Data = data;
        Number = number;
        this.storageUnit_id = storageUnit_id;
        this.dealer_id = dealer_id;
        this.hardware_id = hardware_id;
    }

    public Integer getId() {
        return Id;
    }

    public DeliverieHardware setId(Integer id) {
        Id = id;
        return this;
    }

    public Double getPrice() {
        return Price;
    }

    public DeliverieHardware setPrice(Double price) {
        Price = price;
        return this;
    }

    public String getData() {
        return Data;
    }

    public DeliverieHardware setData(String data) {
        Data = data;
        return this;
    }

    public Integer getNumber() {
        return Number;
    }

    public DeliverieHardware setNumber(Integer number) {
        Number = number;
        return this;
    }

    public StorageUnit getStorageUnit_id() {
        return storageUnit_id;
    }

    public DeliverieHardware setStorageUnit_id(StorageUnit storageUnit_id) {
        this.storageUnit_id = storageUnit_id;
        return this;
    }

    public Dealer getDealer_id() {
        return dealer_id;
    }

    public DeliverieHardware setDealer_id(Dealer dealer_id) {
        this.dealer_id = dealer_id;
        return this;
    }

    public Hardware getHardware_id() {
        return hardware_id;
    }

    public DeliverieHardware setHardware_id(Hardware hardware_id) {
        this.hardware_id = hardware_id;
        return this;
    }
}
