package com.packt.quarkus.chapter5;

import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
@Cacheable
@Entity
@NamedQuery(name = "Customers.findAll",
        query = "SELECT c FROM Customer c ORDER BY c.id",
        hints = @QueryHint(name = "org.hibernate.cacheable", value = "true") )
public class Customer extends PanacheEntityBase {
    @Id
    @SequenceGenerator(
            name = "customerSequence",
            sequenceName = "customerId_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSequence")
    public Long id;

    @Column(length = 40)
    public String name;
    @Column(length = 40)
    public String surname;


    @OneToMany(mappedBy = "customer")
    public List<Orders> orders;
}