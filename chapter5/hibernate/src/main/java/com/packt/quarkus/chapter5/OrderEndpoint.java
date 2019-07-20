package com.packt.quarkus.chapter5;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("orders")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class OrderEndpoint {

    @Inject OrderRepository orderRepository;
    @Inject CustomerRepository customerRepository;

    @GET
    public List<Orders> getAll(@QueryParam("customerId") Long customerId) {
        return orderRepository.findAll(customerId);

    }

    @POST
    @Path("/{customer}")
    public Response create(Orders order, @PathParam("customer") Long customerId) {
        System.out.println("Order is "+order);

        Customer c = customerRepository.findCustomerById(customerId);

        orderRepository.createOrder(order,c);
        return Response.status(201).build();

    }

    @PUT
    public Response update(Orders order) {
        System.out.println("Updating order id "+order);
        orderRepository.updateOrder(order);
        return Response.status(204).build();
    }
    @DELETE
<<<<<<< HEAD
    @Path("/{order}")
    public Response delete(@PathParam("order") Long orderId) {
=======
    public Response delete(@QueryParam("id") Long orderId) {
>>>>>>> fc099194d947fb486088b902a424566c9b9fa3a7
        System.out.println("Deleting order id "+orderId);
        orderRepository.deleteOrder(orderId);
        return Response.status(204).build();
    }

}