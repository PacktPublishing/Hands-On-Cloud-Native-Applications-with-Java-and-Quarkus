package com.packt.quarkus.chapter6.restclient;


import org.eclipse.microprofile.rest.client.inject.RestClient;

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

    @Inject
    @RestClient
    OrderEndpointItf order;


    @GET
    public List<Orders> getAll(@QueryParam("customerId") Long customerId) {
        return order.getAll(customerId);
    }

    @POST
    @Path("/{customer}")
    public Response create(Orders o, @PathParam("customer") Long customerId) {

        return order.create(o, customerId);

    }

    @PUT
    public Response update(Orders o) {

        return order.update(o);
    }

    @DELETE
    @Path("/{order}")
    public Response delete(@PathParam("order") Long orderId) {

        return order.delete(orderId);
    }



}
