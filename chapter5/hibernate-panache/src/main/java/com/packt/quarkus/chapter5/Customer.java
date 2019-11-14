package com.packt.quarkus.chapter5;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;
@Cacheable
@Entity
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

    @JsonbTransient
    @OneToMany(mappedBy = "customer")
    public List<Orders> orders;
}