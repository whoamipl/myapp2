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

    private Long id;
    private String model;
    private List<Owner> owners = new ArrayList<>();
    private double price;
    private Producer producer;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Warranty getWarranty() {
        return warranty;
    }

    public void setWarranty(Warranty pos) {
        this.warranty = pos;
    }
}
