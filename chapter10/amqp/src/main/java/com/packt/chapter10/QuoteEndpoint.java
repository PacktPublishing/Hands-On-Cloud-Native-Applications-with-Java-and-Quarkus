package com.packt.chapter10;

import io.smallrye.reactive.messaging.annotations.Stream;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/prices")
public class QuoteEndpoint {

    @Inject
    @Stream("in-memory-stream") Publisher<String> quote;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<String> stream() {
        return quote;
    }
}
