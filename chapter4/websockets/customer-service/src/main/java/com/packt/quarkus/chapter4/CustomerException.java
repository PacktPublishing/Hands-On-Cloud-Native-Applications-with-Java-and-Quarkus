package com.packt.quarkus.chapter4;

public class CustomerException extends RuntimeException{

    public CustomerException() {
        super();
    }
    public CustomerException(String exc) {
        super(exc);
    }

}
