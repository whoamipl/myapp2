package com.example.restwsejbdemo.service;

import com.example.restwsejbdemo.domain.Owner;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OwnerManager {

    @PersistenceContext
    EntityManager em;

    public void addOwner(Owner person) {
        em.persist(person);
    }

    public void deleteOwner(Owner person) {
        em.remove(person);
    }

    public Owner getOwner(Long id) {
        return em.find(Owner.class, id);
    }

    public Owner updateOwner(Owner person) {
        return em.merge(person);
    }

    public void clearOwners() {
        em.createNamedQuery("delete.all.owners").executeUpdate();
    }
}
