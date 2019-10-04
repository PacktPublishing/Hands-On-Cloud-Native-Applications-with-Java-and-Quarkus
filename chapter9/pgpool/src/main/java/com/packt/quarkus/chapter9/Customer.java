package com.packt.quarkus.chapter9;

import io.vertx.axle.pgclient.PgPool;
import io.vertx.axle.sqlclient.Row;
import io.vertx.axle.sqlclient.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class Customer {
    private Long id;
    private String name;
    private String surname;

    public Customer(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
    public Customer( ) {

    }
    @Override
    public String toString() {
        return id+","+name+","+surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public static CompletionStage<List<Customer>> findAll(PgPool client) {
        return client.query("SELECT id, name, surname FROM CUSTOMER ORDER BY name ASC").thenApply(pgRowSet -> {
            List<Customer> list = new ArrayList<>(pgRowSet.size());
            for (Row row : pgRowSet) {
                list.add(from(row));
            }
            return list;
        });
    }

    public CompletionStage<Long> create(PgPool client) {
        return client.preparedQuery("INSERT INTO CUSTOMER (id, name, surname) VALUES ( nextval('customerId_seq'), $1,$2) RETURNING (id)", Tuple.of(name,surname))
                .thenApply(pgRowSet -> pgRowSet.iterator().next().getLong("id"));
    }
    public CompletionStage<Boolean> update(PgPool client) {
        return client.preparedQuery("UPDATE CUSTOMER SET name = $1, surname = $2 WHERE id = $3", Tuple.of(name, surname, id))
                .thenApply(pgRowSet -> pgRowSet.rowCount() == 1);
    }

    public static CompletionStage<Boolean> delete(PgPool client, Long id) {
        return client.preparedQuery("DELETE FROM CUSTOMER WHERE id = $1", Tuple.of(id))
                .thenApply(pgRowSet -> pgRowSet.rowCount() == 1);
    }
    private static Customer from(Row row) {
        return new Customer(row.getLong("id"), row.getString("name"), row.getString("surname"));
    }
}