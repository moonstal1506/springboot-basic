package com.prgrms.voucher.service;

import com.prgrms.voucher.model.Customer;
import com.prgrms.voucher.repository.BlackListRepository;
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
