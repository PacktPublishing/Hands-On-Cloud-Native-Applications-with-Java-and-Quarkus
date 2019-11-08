package com.packt.chapter10;

import io.reactivex.Flowable;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.smallrye.reactive.messaging.annotations.Channel;

import java.util.concurrent.TimeUnit;

@Path("/quotes")
public class QuoteEndpoint {



    @Inject
    @Channel("in-memory-stream") Publisher<String> quote;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    public Publisher<String> stream() {

        return quote;
    }

}
