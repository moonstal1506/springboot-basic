package com.prgrms.voucher.customer.repository;

import com.prgrms.voucher.customer.model.Customer;
import com.prgrms.voucher.customer.model.CustomerType;
import com.prgrms.voucher.voucher.model.FixedAmountVoucher;
import com.prgrms.voucher.voucher.model.Voucher;
import com.prgrms.voucher.voucher.repository.JdbcVoucherRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
class CustomerRepositoryTest {

    @Configuration
    @ComponentScan(basePackages = {"com.prgrms.voucher"})
    static class Config{

        @Bean
        public DataSource dataSource() {
            HikariDataSource dataSource = DataSourceBuilder.create()
                    .url("jdbc:mysql://localhost/voucher")
                    .username("root")
                    .password("1234")
                    .type(HikariDataSource.class)
                    .build();
            return dataSource;
        }

        @Bean
        public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
            return new NamedParameterJdbcTemplate(dataSource);
        }
    }

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void beforeEach() {
        customerRepository.deleteAll();
    }

    Customer createCustomer() {
        return new Customer("a", CustomerType.NORMAL);
    }

    @Test
    void save() {
        Customer customer = createCustomer();
        UUID savedId = customerRepository.save(customer);
        Customer findCustomer = customerRepository.findById(savedId).get();
        assertThat(findCustomer.getId()).isEqualTo(savedId);
    }

    @Test
    void findAll() {
        Customer customer1 = createCustomer();
        Customer customer2 = createCustomer();
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        List<Customer> all = customerRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    void updateValue() {
        Customer customer = createCustomer();
        customerRepository.save(customer);
        customer.changeName("b");
        Customer updatedCustomer = customerRepository.update(customer);
        assertThat(updatedCustomer.getName()).isEqualTo("b");
    }

}