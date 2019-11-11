package com.packt.quarkus.chapter3;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContainerService {

    public String getContainerId() {
        return System.getenv().getOrDefault("HOSTNAME", "unknown");
    }
}