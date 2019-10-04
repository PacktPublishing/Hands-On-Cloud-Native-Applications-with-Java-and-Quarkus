package com.packt.quarkus.chapter9;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ChatMessage {

    @NotEmpty
    String name;

    @NotEmpty
    String surname;

    public ChatMessage() {
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