package com.example.restwsejbdemo.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Entity
@NamedQueries({
        @NamedQuery(name = "delete.all.owners", query = "DELETE from Owner")
})
public class Owner {

    private Long id;
    private String name;
    private String surname;
    private String city;
    private List<Computer> computers = new ArrayList<>();

    public Owner(String name, String surname, String city) {
        super();
        this.name = name;
        this.surname = surname;
        this.city = city;
    }

    public Owner() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Computer> getComputers() {
        return computers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }
}
