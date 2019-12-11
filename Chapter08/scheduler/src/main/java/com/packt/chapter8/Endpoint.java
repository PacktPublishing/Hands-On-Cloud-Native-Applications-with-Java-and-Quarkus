package com.packt.chapter8;

import io.quarkus.scheduler.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/token")
public class Endpoint {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    TokenGenerator token;

    @Inject
    Scheduler scheduler;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getToken() {

        return token.getToken();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String oneTimeEvnt() {


        scheduler.startTimer(300, () -> event());
        return "started!";
    }

    public void event() {
        log.info("Called One Time Event!");
    }
}