package com.packt.quarkus.chapter9;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class CustomerService {

        @ConsumeEvent("callcustomer")
        public String reply(Customer c) {
            return "Hello! I am " + c.getName() + " " +c.getSurname() + ". How are you doing?";
        }


}
