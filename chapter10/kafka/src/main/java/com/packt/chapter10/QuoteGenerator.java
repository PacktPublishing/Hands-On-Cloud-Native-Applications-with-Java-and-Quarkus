package com.packt.chapter10;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class QuoteGenerator {

    private Random random = new Random();

    @Outgoing("stock-quote")
    public Flowable<String> generate() {
        return Flowable.interval(2, TimeUnit.SECONDS)
                .map(tick -> generateQuote(random.nextInt(2), random.nextInt(5), random.nextInt(100)));
    }

    private String generateQuote(int type, int company, int amount) {
        Jsonb jsonb = JsonbBuilder.create();
        Operation operation = new Operation(type, Company.values()[company], amount);
        return jsonb.toJson(operation);

    }

}
