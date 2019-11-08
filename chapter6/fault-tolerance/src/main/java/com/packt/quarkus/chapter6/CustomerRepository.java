package com.packt.quarkus.chapter6;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class CustomerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger("CustomerRepository");
    @Inject
    EntityManager entityManager;


    @Timeout(250)
    @Fallback(fallbackMethod = "findAllStatic")
    @Retry(maxRetries = 3)
    public List<Customer> findAll() {

        randomSleep();

        return entityManager.createNamedQuery("Customers.findAll", Customer.class)
                .getResultList();

    }

    private void randomSleep() {
        try {
            Thread.sleep(new Random().nextInt(400));
        } catch (Exception e) {

            LOGGER.info("The application is taking too long...{}", e.getMessage());

        }

    }
    private List<Customer> findAllStatic() {
        LOGGER.info("Building Static List of Customers");
        return buildStaticList();

    }
    private List<Customer> buildStaticList() {
        List<Customer> customerList = new ArrayList();
        Customer c1 = new Customer();
        c1.setId(1l);
        c1.setName("John");
        c1.setSurname("Doe");

        Customer c2 = new Customer();
        c2.setId(2l);
        c2.setName("Fred");
        c2.setSurname("Smith");

        customerList.add(c1);
        customerList.add(c2);
        return customerList;
    }
    public Customer findCustomerById(Long id) {

        Customer customer = entityManager.find(Customer.class, id);

        if (customer == null) {
            throw new WebApplicationException("Customer with id of " + id + " does not exist.", 404);
        }
        return customer;
    }

    @Transactional
    public void updateCustomer(Customer customer) {

        Customer customerToUpdate = findCustomerById(customer.getId());
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setSurname(customer.getSurname());
    }

    @Transactional
    public void createCustomer(Customer customer) {

        entityManager.persist(customer);

    }

    @Transactional
    public void deleteCustomer(Long customerId) {

        Customer c = findCustomerById(customerId);
        entityManager.remove(c);


    }
}
