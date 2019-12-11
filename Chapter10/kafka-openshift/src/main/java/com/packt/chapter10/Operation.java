package com.packt.chapter10;

import io.quarkus.runtime.annotations.RegisterForReflection;


@RegisterForReflection
public class Operation   {

    public static final int SELL = 0;
    public static final int BUY = 1;
    private int amount;
    private Company company;
    private int type;

    public Operation(int type, Company company, int amount) {
        this.amount = amount;
        this.company = company;
        this.type = type;
    }

    public Operation(int type, int amount) {
        this.amount = amount;
        this.company = Company.Initech;
        this.type = type;
    }

    public Operation() {

    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
