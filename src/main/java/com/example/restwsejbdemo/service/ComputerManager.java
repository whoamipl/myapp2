package com.example.restwsejbdemo.service;

import com.example.restwsejbdemo.domain.Computer;
import com.example.restwsejbdemo.domain.Computer_;
import com.example.restwsejbdemo.domain.Producer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ComputerManager {

    @PersistenceContext
    EntityManager entityManager;

    public void addComputer(Computer computer) {
        entityManager.persist(computer);
    }

    public void deleteComputer(Computer computer) {
        entityManager.remove(computer);
    }

    public Computer getComputer(Long id) {
        return entityManager.find(Computer.class, id);
    }

    public Computer updateComputer(Computer computer) {
        return entityManager.merge(computer);
    }

    @SuppressWarnings("unchecked")
    public List<Computer> getAllComputers() {
        return entityManager.createNamedQuery("computers.all").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getComputersByOwnerCity (String city) {
        return entityManager.createNamedQuery("computerOwner.findByOwnerCity").setParameter("city", city).getResultList();
    }

    @SuppressWarnings("unchecked")
    public void deleteAll() {
        entityManager.createNamedQuery("computer.delete.all").executeUpdate();
    }

    public List<Computer> getComputersByModel(String model) {

        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Computer> c = qb.createQuery(Computer.class);

        Root<Computer> b = c.from(Computer.class);

        Predicate condition = qb.equal(b.get(Computer_.model), model);
        c.where(condition);

        TypedQuery<Computer> q = entityManager.createQuery(c);

        List<Computer> result = q.getResultList();

        return result;
    }

    public Producer getCompany(Long id) {
        return entityManager.find(Producer.class, id);
    }

}
