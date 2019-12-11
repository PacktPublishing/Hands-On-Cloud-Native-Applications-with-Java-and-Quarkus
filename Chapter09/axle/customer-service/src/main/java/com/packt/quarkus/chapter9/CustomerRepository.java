package com.packt.quarkus.chapter9;

import io.vertx.core.Vertx;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class CustomerRepository {
    @Inject
    Vertx vertx;

    @ConfigProperty(name = "file.path" )
    String path;

    List<Customer> customerList = new ArrayList();
    int counter;

    public int getNextCustomerId() {
        return counter++;
    }

    public List<Customer> findAll() {
        return customerList;
    }

    public Customer findCustomerById(Integer id) {
        for (Customer c:customerList) {
            if (c.getId().equals(id))  {
                return c;
            }
        }
        throw new CustomerException("Customer not found!");
    }

    public void updateCustomer(Customer customer) {
        Customer customerToUpdate = findCustomerById(customer.getId());
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setSurname(customer.getSurname());
    }

    public void createCustomer(Customer customer) {


        customer.setId(getNextCustomerId());
        findAll().add(customer);

    }

    public void deleteCustomer(Integer customerId) {

        Customer c = findCustomerById(customerId);
        findAll().remove(c);
    }


}
