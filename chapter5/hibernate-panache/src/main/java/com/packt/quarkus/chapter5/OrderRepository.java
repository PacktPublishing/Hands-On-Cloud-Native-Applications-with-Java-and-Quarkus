package com.packt.quarkus.chapter5;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import javax.ws.rs.QueryParam;
import javax.persistence.Query;


@ApplicationScoped
public class OrderRepository {


    public List<Orders> findAll(Long customerId) {

        return Orders.list("id", customerId);

    }

    public Orders findOrderById(Long id) {

        Orders order = Orders.findById(id);

        if (order == null) {
            throw new WebApplicationException("Order with id of " + id + " does not exist.", 404);
        }
        return order;
    }
    @Transactional
    public void updateOrder(Orders order) {
        Orders orderToUpdate = findOrderById(order.id);
        orderToUpdate.item = order.item;
        orderToUpdate.price = order.price;
    }
    @Transactional
    public void createOrder(Orders order, Customer c) {

        order.customer = c;
        order.persist();

    }
    @Transactional
    public void deleteOrder(Long orderId) {

        Orders order = findOrderById(orderId);
        order.delete();


    }
}