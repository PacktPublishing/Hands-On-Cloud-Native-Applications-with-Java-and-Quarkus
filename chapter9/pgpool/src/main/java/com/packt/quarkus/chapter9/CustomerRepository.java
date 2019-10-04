package com.packt.quarkus.chapter9;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class CustomerRepository {

    List<Customer> customerList = new ArrayList();
    Long counter = new Long(0);

    public Long getNextCustomerId() {
        return counter++;
    }

    public List<Customer> findAll() {
        System.out.println("Customer list "+customerList);
        return customerList;
    }

    public Customer findCustomerById(Long id) {
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

    public void deleteCustomer(Long customerId) {

        Customer c = findCustomerById(customerId);
        findAll().remove(c);
    }
}