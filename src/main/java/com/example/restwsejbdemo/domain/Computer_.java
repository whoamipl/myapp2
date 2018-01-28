package com.example.restwsejbdemo.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Computer.class)
public abstract class Computer_ {

    public static volatile SingularAttribute<Computer, Double> price;
    public static volatile SingularAttribute<Computer, String> cpu;
    public static volatile SingularAttribute<Computer, Producer> producer;
    public static volatile SingularAttribute<Computer, Warranty> warranty;
    public static volatile SingularAttribute<Computer, String> model;
    public static volatile ListAttribute<Computer, Owner> owners;
    public static volatile SingularAttribute<Computer, Long> id;
    public static volatile SingularAttribute<Computer, String> gpu;
}