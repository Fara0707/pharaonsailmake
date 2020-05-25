package com.nikola.pharaonsailmake.domain;

import javax.persistence.*;

@Entity
@Table(name = "Cloths")

public class Cloth {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "Type_id")
    private ClothType Type_id; // спинакерная или лавировочная

    @Column(name = "Name", length = 100)
    private String Name; // название ткани как в прайсе

    @Column(name = "Width")
    private Integer Width;

    @Column(name = "Weight")
    private Double Weight;

    @Column(name = "Price")
    private Double Price;


    public Cloth() {
    }

    public Cloth(ClothType type_id, String name, Integer width, Double weight, Double price) {
        this.Type_id = type_id;
        Name = name;
        Width = width;
        Weight = weight;
        Price = price;
    }

    public Integer getId() {
        return Id;
    }

    public Cloth setId(Integer id) {
        Id = id;
        return this;
    }

    public ClothType getType() {
        return Type_id;
    }

    public Cloth setType(ClothType type_id) {
        Type_id = type_id;
        return this;
    }

    public String getName() {
        return Name;
    }

    public Cloth setName(String name) {
        Name = name;
        return this;
    }

    public Integer getWidth() {
        return Width;
    }

    public Cloth setWidth(Integer width) {
        Width = width;
        return this;
    }

    public Double getWeight() {
        return Weight;
    }

    public Cloth setWeight(Double weight) {
        Weight = weight;
        return this;
    }

    public Double getPrice() {
        return Price;
    }

    public Cloth setPrice(Double price) {
        Price = price;
        return this;
    }
}
