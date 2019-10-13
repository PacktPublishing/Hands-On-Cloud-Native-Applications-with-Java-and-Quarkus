package com.packt.quarkus.chapter9;

import io.vertx.axle.pgclient.PgPool;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import java.util.concurrent.CompletionStage;

@Path("customers")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerEndpoint {


    @Inject
    PgPool client;

    @PostConstruct
    private void initdb() {

        client.query("DROP TABLE IF EXISTS CUSTOMER")
                .thenCompose(r -> client.query("CREATE SEQUENCE IF NOT EXISTS  customerId_seq"))
                .thenCompose(r -> client.query("CREATE TABLE CUSTOMER (id SERIAL PRIMARY KEY, name TEXT NOT NULL,surname TEXT NOT NULL)"))
                .thenCompose(r -> client.query("INSERT INTO CUSTOMER (id, name, surname) VALUES ( nextval('customerId_seq'), 'John','Doe')"))
                .thenCompose(r -> client.query("INSERT INTO CUSTOMER (id, name, surname) VALUES ( nextval('customerId_seq'), 'Fred','Smith')"))
                .toCompletableFuture()
                .join();
    }

    @GET
    public CompletionStage<Response> getAll() {
        return Customer.findAll(client).thenApply(Response::ok)
                .thenApply(ResponseBuilder::build);
    }


    @POST
    public CompletionStage<Response> create(Customer customer) {

        return customer.create(client).thenApply(Response::ok)
                .thenApply(ResponseBuilder::build);
     }

    @PUT
    public CompletionStage<Response> update(Customer customer) {
        return customer.update(client)
                .thenApply(updated -> updated ? Status.OK : Status.NOT_FOUND)
                .thenApply(status -> Response.status(status).build());
    }

    @DELETE
    public CompletionStage<Response> delete(@QueryParam("id") Long customerId) {
        return Customer.delete(client, customerId)
                .thenApply(deleted -> deleted ? Status.NO_CONTENT : Status.NOT_FOUND)
                .thenApply(status -> Response.status(status).build());
    }

}