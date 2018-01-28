package com.example.restwsejbdemo.service;

import com.example.restwsejbdemo.domain.Producer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProducerManager {

    @PersistenceContext
    EntityManager em;

    public void addProducer(Producer producer) {
        em.persist(producer);
    }

    public void deleteProducer(Producer producer) {
        em.remove(producer);
    }

    public Producer getProducer(Long id) {
        return em.find(Producer.class, id);
    }

    public Producer updateProducer(Producer producer) {
        return em.merge(producer);
    }

}
