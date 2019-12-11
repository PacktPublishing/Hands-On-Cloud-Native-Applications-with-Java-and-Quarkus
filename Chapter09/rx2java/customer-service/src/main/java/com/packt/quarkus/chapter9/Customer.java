package com.packt.quarkus.chapter9;


import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Customer {

    private Integer id;

    private String name;

    private String surname;

    public Customer(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Customer() {
    }

    @Override
    public String toString() {

        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}