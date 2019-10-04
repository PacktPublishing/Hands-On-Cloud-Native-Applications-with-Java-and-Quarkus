package com.packt.quarkus.chapter9;

import io.reactivex.Flowable;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import io.vertx.reactivex.core.Vertx;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

@ApplicationScoped
public class CustomerRepository {
    @Inject Vertx vertx;

    List<Customer> customerList = new ArrayList();
    int counter;

    public int getNextCustomerId() {
        return counter++;
    }

    public List<Customer> findAll() {
        return customerList;
    }

    public Customer findCustomerById(Integer id) {
        for (Customer c:customerList) {
            if (c.getId().equals(id))  {
                return c;
            }
        }
        throw new CustomerException("Customer not found!");
    }

    public void updateCustomer(Customer customer) {
        Customer customerToUpdate = findCustomerById(customer.getId());
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setSurname(customer.getSurname());
    }

    public void createCustomer(Customer customer) {


        customer.setId(getNextCustomerId());
        findAll().add(customer);
    }

    public void deleteCustomer(Integer customerId) {

        Customer c = findCustomerById(customerId);
        findAll().remove(c);
    }

    public CompletionStage<String>  writeFile() {
        CompletableFuture<String> future = new CompletableFuture<>();
        StringBuffer sb = new StringBuffer();
        vertx.fileSystem().rxReadFile("/home/francesco/customer.json")


                .map(buffer -> buffer.toString())
                .map(content -> new JsonArray(content))
                .flatMapPublisher(array -> Flowable.fromIterable(array))
                .cast(JsonObject.class)
                .map(json -> json.mapTo(Customer.class))
                .subscribe(users -> {
                    java.nio.file.Files.write(Paths.get("/home/francesco/file.log"), users.toString().getBytes(), StandardOpenOption.APPEND);
                    System.out.println(users);
                    sb.append(users);
                }, err -> System.out.println("Cannot read the file: " + err.getMessage()));


        vertx.fileSystem().writeFile("/home/francesco/file.log", Buffer.buffer(sb.toString()), handler -> {
            if (handler.succeeded()) {
                System.out.println("File written with ");
                future.complete("File written");
            } else {
                System.err.println("Error while writing in file: " + handler.cause().getMessage());

            }
        });




        return future;


    }
    public CompletionStage<String>  writeFileOriginal() {
        CompletableFuture<String> future = new CompletableFuture<>();
        StringBuffer sb = new StringBuffer();
        vertx.fileSystem().rxReadFile("/home/francesco/customer.json")


                .map(buffer -> buffer.toString())
                .map(content -> new JsonArray(content))
                .flatMapPublisher(array -> Flowable.fromIterable(array))
                .cast(JsonObject.class)
                .map(json -> json.mapTo(Customer.class))
                .subscribe(users -> {
                    java.nio.file.Files.write(Paths.get("/home/francesco/file.log"), users.toString().getBytes(), StandardOpenOption.APPEND);
                    System.out.println(users);
                    sb.append(users);
                }, err -> System.out.println("Cannot read the file: " + err.getMessage()));
        future.complete("file written");
        return future;


    }
    public CompletionStage<String> readFile() {
        // When complete, return the content to the client
        CompletableFuture<String> future = new CompletableFuture<>();

        long start = System.nanoTime();

        // Delay reply by 10ms
        vertx.setTimer(10, l -> {
            // Compute elapsed time in milliseconds
            long duration = MILLISECONDS.convert(System.nanoTime() - start, NANOSECONDS);

            vertx.fileSystem().readFile("/home/francesco/file.log", ar -> {
                if (ar.succeeded()) {
                    String response = ar.result().toString("UTF-8");
                    System.out.println("------------>"+response);
                    future.complete(response);
                } else {
                    future.complete("Cannot read the file: " + ar.cause().getMessage());
                }
            });

        });




        return future;
    }
}
