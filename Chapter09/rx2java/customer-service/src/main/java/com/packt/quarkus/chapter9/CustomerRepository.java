package com.packt.quarkus.chapter9;

import io.reactivex.Observable;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;



@ApplicationScoped
public class CustomerRepository {
    @Inject
    Vertx vertx;

    @ConfigProperty(name = "file.path" )
    String path;

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

    /*
    returns an Observable that is the result of a function applied to an item emitted by a Single
    https://github.com/ReactiveX/RxJava/wiki/Transforming-Observables#flatmapobservable
     */
    public CompletionStage<String> readFile() {

        CompletableFuture<String> future = new CompletableFuture<>();
        StringBuffer sb = new StringBuffer();

        vertx.fileSystem().rxReadFile(path)
                .flatMapObservable(buffer -> Observable.fromArray(buffer.toString().split("\n")))
                .skip(1)
                .map(s -> s.split(","))
                .map(data-> new Customer(Integer.parseInt(data[0]),data[1],data[2]))
                .subscribe(
                        data ->  sb.append(data.toString()),
                        error -> System.err.println(error),
                        () ->    future.complete(sb.toString()));


        return future;

    }

    public CompletionStage<String> writeFile() {
        CompletableFuture<String> future = new CompletableFuture<>();
        StringBuffer sb = new StringBuffer("id,name,surname");
        sb.append("\n");

        Observable.fromIterable(customerList)
                .map(c -> c.getId() + "," + c.getName() + "," + c.getSurname() + "\n")
                .subscribe(
                        data ->   sb.append(data),
                        error -> System.err.println(error),
                        () ->  vertx.fileSystem().writeFile(path, Buffer.buffer(sb.toString()), handler -> {

                            if (handler.succeeded()) {
                                future.complete("File written in "+path);
                            } else {
                                System.err.println("Error while writing in file: " + handler.cause().getMessage());

                            }
                        }));

        return future;
    }


}