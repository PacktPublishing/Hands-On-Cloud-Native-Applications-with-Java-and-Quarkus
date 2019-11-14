package com.packt.chapter10;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.HashMap;
import java.util.Random;

@ApplicationScoped
public class QuoteConverter {
    HashMap<String,Double> quotes;


    private Random random = new Random();
    @PostConstruct
    public void init() {
        quotes = new HashMap<>();
        for (Company company: Company.values())
        quotes.put(company.name(), new Double(random.nextInt(100) + 50));

    }

    @Incoming("stocks")
    @Outgoing("in-memory-stream")
    @Broadcast
    public String newQuote(String quoteJson) {

        Jsonb jsonb = JsonbBuilder.create();

        Operation operation = jsonb.fromJson(quoteJson, Operation.class);
        double currentQuote = quotes.get(operation.getCompany().name());
        double newQuote;
        double change = (operation.getAmount() / 25);

        if (operation.getType() == Operation.BUY) {
              newQuote = currentQuote + change;
        }
        else  {
            newQuote = currentQuote - change;
        }
        if (newQuote < 0) newQuote = 0;

        quotes.replace(operation.getCompany().name(), newQuote);
        Quote quote = new Quote(operation.getCompany().name(), newQuote);

        return jsonb.toJson(quote);

    }

}
