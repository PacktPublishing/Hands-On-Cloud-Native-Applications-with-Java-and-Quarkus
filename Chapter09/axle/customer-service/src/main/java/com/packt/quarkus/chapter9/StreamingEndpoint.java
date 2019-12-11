package com.packt.quarkus.chapter9;

import io.vertx.axle.core.Vertx;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/streaming")
public class StreamingEndpoint {

    @Inject
    Vertx vertx;

    @Inject CustomerRepository customerRepository;
    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<String> stream() {

        return
                ReactiveStreams.fromPublisher(vertx.periodicStream(2000).toPublisher())
                        .map(l -> String.format("Number of Customers %s . Last one added: %s %n", customerRepository.findAll().size(),
                                customerRepository.findAll().size() > 0
                                        ? (customerRepository.findAll().get(customerRepository.findAll().size() -1)).toString()  : "N/A"))
                        .buildRs();


    }
}