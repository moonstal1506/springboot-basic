package com.prgrms.voucher.customer.service;

import com.prgrms.voucher.customer.model.Customer;
import com.prgrms.voucher.customer.repository.BlackListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final BlackListRepository blackListRepository;

    public CustomerService(BlackListRepository blackListRepository) {
        this.blackListRepository = blackListRepository;
    }

    public List<Customer> getBlackList() {
        return blackListRepository.findAll();
    }
}
