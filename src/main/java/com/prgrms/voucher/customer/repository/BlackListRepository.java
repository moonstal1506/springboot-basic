package com.prgrms.voucher.customer.repository;

import com.prgrms.voucher.customer.model.Customer;
import com.prgrms.voucher.customer.model.CustomerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class BlackListRepository {

    private static final Logger logger = LoggerFactory.getLogger(BlackListRepository.class);
    private final File file = new File("customer_blackList.csv");

    public List<Customer> findAll() {
        List<Customer> blackList = new ArrayList<>();
        String line;

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(",");
                Customer customer = new Customer( arr[1], CustomerType.BLACK);
                customer.setId(UUID.fromString(arr[0]));
                blackList.add(customer);
            }
        } catch (IOException | NullPointerException e) {
            logger.error("findAll() IOException  error ", e);
        }
        return List.copyOf(blackList);
    }
}
