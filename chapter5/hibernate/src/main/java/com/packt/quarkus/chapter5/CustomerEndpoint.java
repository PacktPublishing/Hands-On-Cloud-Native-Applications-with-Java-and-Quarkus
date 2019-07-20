package com.packt.quarkus.chapter5;

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

    @Inject CustomerRepository customerRepository;

    @GET
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @POST
    public Response create(Customer customer) {
<<<<<<< HEAD
        System.out.println("Creare customer "+customer);
=======
>>>>>>> fc099194d947fb486088b902a424566c9b9fa3a7
        customerRepository.createCustomer(customer);
        return Response.status(201).build();

    }

    @PUT
    public Response update(Customer customer) {
        customerRepository.updateCustomer(customer);
        return Response.status(204).build();
    }
    @DELETE
    public Response delete(@QueryParam("id") Long customerId) {
        customerRepository.deleteCustomer(customerId);
        return Response.status(204).build();
    }

}