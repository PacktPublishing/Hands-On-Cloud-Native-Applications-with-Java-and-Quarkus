package com.packt.quarkus.chapter6;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
@ApplicationScoped

public class OrderRepository {

    @Inject
    EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger("CustomerRepository");
    @CircuitBreaker(successThreshold = 5, requestVolumeThreshold = 4, failureRatio=0.75,
            delay = 1000)
    public List<Orders> findAll(Long customerId) {

        possibleFailure();
        return  (List<Orders>) entityManager.createNamedQuery("Orders.findAll")
                .setParameter("customerId", customerId)
                .getResultList();
    }
    private void possibleFailure() {
        if (new Random().nextFloat() < 0.5f)
        throw new RuntimeException("I have generated a Random Resource failure! Try again accessing the service.");
    }
    public Orders findOrderById(Long id) {

        Orders order = entityManager.find(Orders.class, id);
        if (order == null) {
            throw new WebApplicationException("Order with id of " + id + " does not exist.", 404);
        }
        return order;
    }
    @Transactional
    public void updateOrder(Orders order) {
        Orders orderToUpdate = findOrderById(order.getId());
        orderToUpdate.setItem(order.getItem());
        orderToUpdate.setPrice(order.getPrice());
    }
    @Transactional
    public void createOrder(Orders order, Customer c) {

        order.setCustomer(c);
        entityManager.persist(order);
        writeSomeLogging(order.getItem());

    }
    @Asynchronous
    @Bulkhead(value = 5, waitingTaskQueue = 10)
    private Future writeSomeLogging(String item) {
            LOGGER.info("New Customer order at: "+new java.util.Date());
            LOGGER.info("Item: {}", item);
            return CompletableFuture.completedFuture("ok");
    }
    @Transactional
    public void deleteOrder(Long orderId) {
        Orders o = findOrderById(orderId);
        entityManager.remove(o);
    }
}
