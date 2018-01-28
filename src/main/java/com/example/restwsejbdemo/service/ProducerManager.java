package com.example.restwsejbdemo.service;

import com.example.restwsejbdemo.domain.Producer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProducerManager {

    @PersistenceContext
    EntityManager em;

    public void addProducer(Producer company) {
        em.persist(company);
    }

    public void deleteProducer(Producer company) {
        em.remove(company);
    }

    public Producer getProducer(Long id) {
        return em.find(Producer.class, id);
    }

    public Producer updateProducer(Producer company) {
        return em.merge(company);
    }

}
