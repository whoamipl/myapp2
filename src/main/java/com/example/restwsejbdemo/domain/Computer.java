package com.example.restwsejbdemo.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Entity
@NamedQueries({
        @NamedQuery(name = "computers.all", query = "Select c from Computer c"),
        @NamedQuery(name = "computers.delete", query = "Delete from Computer "),
        @NamedQuery(name = "computerOwner.findByOwnerCity", query = "Select o.name, o.surname, c.model from Computer c JOIN c.owners o where o.city = :city")
})
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Owner> owners = new ArrayList<>();
    private double price;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Producer producer;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Warranty warranty;


    public Computer() {
    }

    public Computer(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public Computer(String model, List<Owner> owners, double price, Producer producer, Warranty warranty) {
        this.model = model;
        this.owners = owners;
        this.price = price;
        this.producer = producer;
        this.warranty = warranty;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public void addOwners(List<Owner> owners) {

        this.setOwners(owners);
        for (Owner owner : owners) {
            owner.getComputers().add(this);
        }
    }


    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Warranty getWarranty() {
        return warranty;
    }

    public void setWarranty(Warranty pos) {
        this.warranty = pos;
    }
}
