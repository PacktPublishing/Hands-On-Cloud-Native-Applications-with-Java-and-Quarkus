package com.packt.quarkus.chapter4;


import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.List;

@ServerEndpoint(value="/customers", encoders = {MessageEncoder.class})



public class WebsocketEndpoint {
    @Inject
    CustomerRepository customerRepository;

    @OnMessage
    public List<Customer>  addCustomer(String message, Session session) {
        Jsonb jsonb = JsonbBuilder.create();

        Customer customer = jsonb.fromJson(message, Customer.class);
        customerRepository.createCustomer(customer);
        return customerRepository.findAll();
    }
    @OnOpen
    public void myOnOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
    }
    @OnClose
    public void myOnClose(CloseReason reason) {
        System.out.println("Closing a due to " + reason.getReasonPhrase());
    }
    @OnError
    public void error(Throwable t) {

    }


}
