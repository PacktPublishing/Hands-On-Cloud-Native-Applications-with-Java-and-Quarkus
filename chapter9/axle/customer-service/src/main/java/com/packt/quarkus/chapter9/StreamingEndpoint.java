package com.packt.quarkus.chapter9;

import io.vertx.axle.core.Vertx;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.Date;

@Path("/streaming")
public class StreamingEndpoint {

    @Inject
    Vertx vertx;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<String> stream() {
        String filename = "/home/francesco/write.csv";
        File file = new File(filename);
        if (file.exists()) {
            return ReactiveStreams.fromPublisher(vertx.periodicStream(2000).toPublisher())
                    .map(l -> String.format("File created with size %s bytes!%n", file.length()))
                    .buildRs();
        }
        else {
            return ReactiveStreams.fromPublisher(vertx.periodicStream(2000).toPublisher())
                    .map(l -> String.format("File %s not created!%n", filename))
                    .buildRs();
        }


    }
}