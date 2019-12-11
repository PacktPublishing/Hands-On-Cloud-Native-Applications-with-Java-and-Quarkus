package com.packt.chapter8;


import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class TokenGenerator {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    private String token;

    public String getToken() {
        return token;
    }

    @Scheduled(every="1h")
    void generateToken() {
        token= UUID.randomUUID().toString();
        log.info("Token generated");
    }

}