package com.packt.quarkus.chapter5;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.WebApplicationException;
import javax.transaction.Transactional;
import io.quarkus.panache.common.Sort;
@ApplicationScoped
public class CustomerRepository {


    public List<Customer> findAll() {

        return Customer.listAll(Sort.by("id"));


    }

    public Customer findCustomerById(Long id) {
        Customer customer = Customer.findById(id);



        if (customer == null) {
            throw new WebApplicationException("Customer with id of " + id + " does not exist.", 404);
        }
        return customer;
    }
    @Transactional
    public void updateCustomer(Customer customer) {

        Customer customerToUpdate = findCustomerById(customer.id);
        customerToUpdate.name = customer.name;
        customerToUpdate.surname = customer.surname;
    }
    @Transactional
    public void createCustomer(Customer customer) {

        customer.persist();

    }
    @Transactional
    public void deleteCustomer(Long customerId) {

        Customer customer = findCustomerById(customerId);
        customer.delete();


    }
}
