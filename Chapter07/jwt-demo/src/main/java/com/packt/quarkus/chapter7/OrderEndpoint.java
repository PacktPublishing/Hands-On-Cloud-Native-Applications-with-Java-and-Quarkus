package com.packt.quarkus.chapter7;


import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("orders")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
@RolesAllowed("admin")
public class OrderEndpoint {


    @Inject OrderRepository orderRepository;
    @Inject CustomerRepository customerRepository;

    @GET
    @RolesAllowed("user")
    public List<Orders> getAll(@QueryParam("customerId") Long customerId) {
        return orderRepository.findAll(customerId);
    }

    @POST
    @RolesAllowed("admin")
    @Path("/{customer}")
    public Response create(Orders order, @PathParam("customer") Long customerId) {
        Customer c = customerRepository.findCustomerById(customerId);
        orderRepository.createOrder(order,c);
        return Response.status(201).build();

    }

    @PUT
    @RolesAllowed("admin")
    public Response update(Orders order) {
        orderRepository.updateOrder(order);
        return Response.status(204).build();
    }
    @DELETE
    @RolesAllowed("admin")
    @Path("/{order}")
    public Response delete(@PathParam("order") Long orderId) {
        orderRepository.deleteOrder(orderId);
        return Response.status(204).build();
    }

}
