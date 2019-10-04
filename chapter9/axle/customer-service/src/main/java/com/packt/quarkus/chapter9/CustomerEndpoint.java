package com.packt.quarkus.chapter9;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import io.vertx.axle.core.eventbus.EventBus;
import io.vertx.axle.core.eventbus.Message;
import javax.validation.Valid;

@Path("customers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class CustomerEndpoint {

    @Inject CustomerRepository customerRepository;
    @Inject
    EventBus bus; //https://medium.com/@alexey.soshin/understanding-vert-x-event-bus-c31759757ce8

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
    @Path("writefile")
    @Produces("text/plain")
    public CompletionStage<String> writeFile() {
        return customerRepository.writeFile();

    }

    @GET
    @Path("readfile")
    @Produces("text/csv")
    public CompletionStage<String> readFile() {
        return customerRepository.readFile();

    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("chat")
    public CompletionStage<String> chat(@Valid ChatMessage message) {
        System.out.println(message);
        return bus.<String>send("chat", message)
                .thenApply(Message::body)
                .exceptionally(Throwable::getMessage);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/bus/{name}")
    public CompletionStage<String> greeting(@PathParam("name") String name) {
        return bus.<String>send("greeting", name)
                .thenApply(Message::body);
    }
}