package com.example.restwsejbdemo.service;

import com.example.restwsejbdemo.domain.Owner;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OwnerManager {

    @PersistenceContext
    EntityManager em;

    public void addOwner(Owner owner) {
        em.persist(owner);
    }

    public void deleteOwner(Owner owner) {
        em.remove(owner);
    }

    public Owner getOwner(Long id) {
        return em.find(Owner.class, id);
    }

    public Owner updateOwner(Owner owner) {
        return em.merge(owner);
    }

    public void clearOwners() {
        em.createNamedQuery("delete.all.owners").executeUpdate();
    }
}
