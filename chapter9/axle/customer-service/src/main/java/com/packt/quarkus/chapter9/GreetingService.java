package com.packt.quarkus.chapter9;

import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class GreetingService {

    @ConsumeEvent("greeting")
    public String greeting(String name) {
        return "Hello " + name;
    }

    @ConsumeEvent("chat")
    public String chat(ChatMessage message) {
        return "Hello " + message.getName();
    }
}
