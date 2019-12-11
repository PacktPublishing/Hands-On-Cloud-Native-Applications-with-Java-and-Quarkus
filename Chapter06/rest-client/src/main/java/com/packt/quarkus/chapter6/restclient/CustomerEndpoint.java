package com.packt.quarkus.chapter6.restclient;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("customers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")

public class CustomerEndpoint {

    @Inject
    @RestClient
    CustomerEndpointItf customer;


    @GET
    public List<Customer> getAll() {
        return customer.getAll();
    }


    @POST
    public Response create(Customer c) {
        return customer.create(c);
    }


    @PUT
    public Response update(Customer c) {
        return customer.update(c);
    }

    @DELETE
    public Response delete(@QueryParam("id") Long customerId) {
        return customer.delete(customerId);
    }

}
