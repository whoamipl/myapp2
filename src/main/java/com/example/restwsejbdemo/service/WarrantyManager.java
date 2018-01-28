package com.example.restwsejbdemo.service;

import com.example.restwsejbdemo.domain.Warranty;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class WarrantyManager {

    @PersistenceContext
    EntityManager entityManager;

    public void addWarranty(Warranty pos) {
        entityManager.persist(pos);
    }

    public void deleteWarranty(Warranty pos) {
        entityManager.remove(pos);
    }

    public Warranty getWarranty(Long id) {
        return entityManager.find(Warranty.class, id);
    }

    public Warranty updateWarranty(Warranty pos) {
        return entityManager.merge(pos);
    }

}
