package com.nikola.pharaonsailmake.domain;


import javax.persistence.*;

@Entity
@Table(name = "Sails")

public class Sail {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    @ManyToOne
    @JoinColumn(name = "Cut_id")
    private SailCut cut_id;

    @Column(name = "LengthLeech")
    private Integer LengthLeech;

    @Column(name = "LengthLuff")
    private Integer LengthLuff;

    @Column(name = "LengthFoot")
    private Integer LengthFoot;

    @Column(name = "SickleLeech")
    private Integer SickleLeech;

    @Column(name = "SickleLuff")
    private Integer SickleLuff;

    @Column(name = "SickleFoot")
    private Integer SickleFoot;

    @Column(name = "Seam")
    private Integer Seam;

    @Column(name = "ElapsedLength")
    private Double ElapsedLength;

    @Column(name = "Price")
    private Double Price;

    @ManyToOne
    @JoinColumn(name = "SailType_id")
    private SailType sailType_id;

    @ManyToOne
    @JoinColumn(name = "Client_id")
    private Client client_id;

    @ManyToOne
    @JoinColumn(name = "Worker_id")
    private Worker worker_id;

    @ManyToOne
    @JoinColumn(name = "DeliverieCloth_id")
    private DeliverieCloth deliverieCloth_id;

    public Sail() {
    }

    public Sail(SailCut cut_id, Integer lengthLeech, Integer lengthLuff, Integer lengthFoot, Integer sickleLeech, Integer sickleLuff, Integer sickleFoot, Integer seam, Double elapsedLength, Double price, SailType sailType_id, Client client_id, Worker worker_id, DeliverieCloth deliverieCloth_id) {
        this.cut_id = cut_id;
        LengthLeech = lengthLeech;
        LengthLuff = lengthLuff;
        LengthFoot = lengthFoot;
        SickleLeech = sickleLeech;
        SickleLuff = sickleLuff;
        SickleFoot = sickleFoot;
        Seam = seam;
        ElapsedLength = elapsedLength;
        Price = price;
        this.sailType_id = sailType_id;
        this.client_id = client_id;
        this.worker_id = worker_id;
        this.deliverieCloth_id = deliverieCloth_id;
    }

    public Integer getId() {
        return Id;
    }

    public Sail setId(Integer id) {
        Id = id;
        return this;
    }

    public SailCut getCut_id() {
        return cut_id;
    }

    public Sail setCut_id(SailCut cut_id) {
        this.cut_id = cut_id;
        return this;
    }

    public Integer getLengthLeech() {
        return LengthLeech;
    }

    public Sail setLengthLeech(Integer lengthLeech) {
        LengthLeech = lengthLeech;
        return this;
    }

    public Integer getLengthLuff() {
        return LengthLuff;
    }

    public Sail setLengthLuff(Integer lengthLuff) {
        LengthLuff = lengthLuff;
        return this;
    }

    public Integer getLengthFoot() {
        return LengthFoot;
    }

    public Sail setLengthFoot(Integer lengthFoot) {
        LengthFoot = lengthFoot;
        return this;
    }

    public Integer getSickleLeech() {
        return SickleLeech;
    }

    public Sail setSickleLeech(Integer sickleLeech) {
        SickleLeech = sickleLeech;
        return this;
    }

    public Integer getSickleLuff() {
        return SickleLuff;
    }

    public Sail setSickleLuff(Integer sickleLuff) {
        SickleLuff = sickleLuff;
        return this;
    }

    public Integer getSickleFoot() {
        return SickleFoot;
    }

    public Sail setSickleFoot(Integer sickleFoot) {
        SickleFoot = sickleFoot;
        return this;
    }

    public Integer getSeam() {
        return Seam;
    }

    public Sail setSeam(Integer seam) {
        Seam = seam;
        return this;
    }

    public Double getElapsedLength() {
        return ElapsedLength;
    }

    public Sail setElapsedLength(Double elapsedLength) {
        ElapsedLength = elapsedLength;
        return this;
    }

    public Double getPrice() {
        return Price;
    }

    public Sail setPrice(Double price) {
        Price = price;
        return this;
    }

    public SailType getSailType_id() {
        return sailType_id;
    }

    public Sail setSailType_id(SailType sailType_id) {
        this.sailType_id = sailType_id;
        return this;
    }

    public Client getClient_id() {
        return client_id;
    }

    public Sail setClient_id(Client client_id) {
        this.client_id = client_id;
        return this;
    }

    public Worker getWorker_id() {
        return worker_id;
    }

    public Sail setWorker_id(Worker worker_id) {
        this.worker_id = worker_id;
        return this;
    }

    public DeliverieCloth getDeliverieCloth_id() {
        return deliverieCloth_id;
    }

    public Sail setDeliverieCloth_id(DeliverieCloth deliverieCloth_id) {
        this.deliverieCloth_id = deliverieCloth_id;
        return this;
    }
}
