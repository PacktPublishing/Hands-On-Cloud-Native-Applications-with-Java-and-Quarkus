package com.packt.quarkus.chapter9;

import io.vertx.axle.core.eventbus.EventBus;
import io.vertx.axle.core.eventbus.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.CompletionStage;

@Path("customers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CustomerEndpoint {

    @Inject CustomerRepository customerRepository;

    @Inject
    EventBus bus;

    @GET
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @POST
    public Response create(Customer customer) {
        customerRepository.createCustomer(customer);
        return Response.status(201).build();

    }

    @PUT
    public Response update(Customer customer) {
        customerRepository.updateCustomer(customer);
        return Response.status(204).build();
    }
    @DELETE
    public Response delete(@QueryParam("id") Integer customerId) {
        customerRepository.deleteCustomer(customerId);
        return Response.status(204).build();
    }

    @GET
    @Path("/call")
    @Produces("text/plain")
    public CompletionStage<String> call(@QueryParam("id") Integer customerId) {
        return bus.<String>send("callcustomer", customerRepository.findCustomerById(customerId))
                .thenApply(Message::body)
                .exceptionally(Throwable::getMessage);
    }
}