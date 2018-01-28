package com.example.restwsejbdemo.service;

import com.example.restwsejbdemo.domain.Warranty;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class WarrantyManager {

    @PersistenceContext
    EntityManager entityManager;

    public void addWarranty(Warranty warranty) {
        entityManager.persist(warranty);
    }

    public void deleteWarranty(Warranty warranty) {
        entityManager.remove(warranty);
    }

    public Warranty getWarranty(Long id) {
        return entityManager.find(Warranty.class, id);
    }

    public Warranty updateWarranty(Warranty warranty) {
        return entityManager.merge(warranty);
    }

}
