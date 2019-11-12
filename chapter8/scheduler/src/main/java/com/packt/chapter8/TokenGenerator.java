package com.packt.chapter8;


import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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