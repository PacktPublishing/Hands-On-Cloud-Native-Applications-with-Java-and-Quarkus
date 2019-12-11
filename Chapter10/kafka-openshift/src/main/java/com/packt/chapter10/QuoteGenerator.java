package com.packt.chapter10;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * A bean producing random prices every 2 seconds.
 * The prices are written to a Kafka topic (prices). The Kafka configuration is specified in the application configuration.
 */
@ApplicationScoped
public class QuoteGenerator {

    private Random random = new Random();

    @Outgoing("stock-quote")
    public Flowable<String> generate() {
        return Flowable.interval(2, TimeUnit.SECONDS)
                .map(tick -> generateQuote(random.nextInt(2), random.nextInt(5), random.nextInt(100)));
    }

    private String generateQuote(int type, int company, int amount) {
        System.out.println("<!>> QuoteGenerator wants to create " +type + " "+company+ ""+amount);
        Jsonb jsonb = JsonbBuilder.create();
        /*
        MyOperation o = new MyOperation();
        o.setAmount(11);
        o.setCompany("zaza");
        o.setType(1);
        String j = jsonb.toJson(o);
        System.out.println(">>>>generateQuote "+j);
        Operation operation = new Operation(type, amount);
        */
        Operation operation = new Operation(type, Company.values()[company], amount);
       // String quote="{ \"type\":1,\"company\":\"Acme\",\"amount\":50}";
        System.out.println("<>> QuoteGenerator " +jsonb.toJson(operation));
        return jsonb.toJson(operation);

    }

}
