package com.prgrms.voucher.customer.model;

import com.prgrms.voucher.customer.repository.CustomerRepository;
import com.prgrms.voucher.voucher.model.Voucher;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {

    private UUID id;
    private String name;
    private CustomerType type;

    public Customer(String name, CustomerType type) {
        this.name = name;
        this.type = type;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CustomerType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
