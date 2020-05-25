package com.nikola.pharaonsailmake.domain;

import javax.persistence.*;

@Entity
@Table(name = "DeliverieCloths")

public class DeliverieCloth {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @Column(name = "LotNumber")
    private Integer LotNumber;

    @Column(name = "RollLength")
    private Double RollLength;

    @Column(name = "Price")
    private Double Price;

    @Column(name = "Data")
    private String Data;

    @ManyToOne
    @JoinColumn(name = "Cloth_id")
    private Cloth cloth_id;

    @ManyToOne
    @JoinColumn(name = "Dealer_id")
    private Dealer dealer_id;

    @ManyToOne
    @JoinColumn(name = "StorageUnit_id")
    private StorageUnit storageUnit_id;

    public DeliverieCloth() {
    }

    public DeliverieCloth(Integer lotNumber, Double rollLength, Double price, String data, Cloth cloth_id, Dealer dealer_id, StorageUnit storageUnit_id) {
        LotNumber = lotNumber;
        RollLength = rollLength;
        Price = price;
        Data = data;
        this.cloth_id = cloth_id;
        this.dealer_id = dealer_id;
        this.storageUnit_id = storageUnit_id;
    }

    public Integer getId() {
        return Id;
    }

    public DeliverieCloth setId(Integer id) {
        Id = id;
        return this;
    }

    public Integer getLotNumber() {
        return LotNumber;
    }

    public DeliverieCloth setLotNumber(Integer lotNumber) {
        LotNumber = lotNumber;
        return this;
    }

    public Double getRollLength() {
        return RollLength;
    }

    public DeliverieCloth setRollLength(Double rollLength) {
        RollLength = rollLength;
        return this;
    }

    public Double getPrice() {
        return Price;
    }

    public DeliverieCloth setPrice(Double price) {
        Price = price;
        return this;
    }

    public String getData() {
        return Data;
    }

    public DeliverieCloth setData(String data) {
        Data = data;
        return this;
    }

    public Cloth getCloth_id() {
        return cloth_id;
    }

    public DeliverieCloth setCloth_id(Cloth cloth_id) {
        this.cloth_id = cloth_id;
        return this;
    }

    public Dealer getDealer_id() {
        return dealer_id;
    }

    public DeliverieCloth setDealer_id(Dealer dealer_id) {
        this.dealer_id = dealer_id;
        return this;
    }

    public StorageUnit getStorageUnit_id() {
        return storageUnit_id;
    }

    public DeliverieCloth setStorageUnit_id(StorageUnit storageUnit_id) {
        this.storageUnit_id = storageUnit_id;
        return this;
    }
}
