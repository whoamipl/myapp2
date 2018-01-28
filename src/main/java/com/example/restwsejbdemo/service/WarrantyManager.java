package com.example.restwsejbdemo.service;

import com.example.restwsejbdemo.domain.Warranty;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class WarrantyManager {

    @PersistenceContext
    EntityManager entityManager;

    public void addPlaceOnShelf(Warranty pos) {
        entityManager.persist(pos);
    }

    public void deletePlaceOnShelf(Warranty pos) {
        entityManager.remove(pos);
    }

    public Warranty getPlaceOnShelf(Long id) {
        return entityManager.find(Warranty.class, id);
    }

    public Warranty updatePlaceOnShelf(Warranty pos) {
        return entityManager.merge(pos);
    }

}
