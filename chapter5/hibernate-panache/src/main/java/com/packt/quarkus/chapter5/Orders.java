package com.packt.quarkus.chapter5;

import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.NamedQuery;

import javax.persistence.Cacheable;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
@Entity
@NamedQuery(name = "Orders.findAll",
        query = "SELECT o FROM Orders o WHERE o.customer.id = :id ORDER BY o.item")
public class Orders extends PanacheEntityBase {
    @Id
    @SequenceGenerator(
            name = "orderSequence",
            sequenceName = "orderId_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSequence")
    public Long id;

    @Column(length = 40)
    public String item;

    @Column
    public Long price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer customer;


}
