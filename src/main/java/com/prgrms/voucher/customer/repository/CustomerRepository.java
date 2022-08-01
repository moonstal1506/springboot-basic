package com.prgrms.voucher.customer.repository;


import com.prgrms.voucher.customer.model.Customer;
import com.prgrms.voucher.customer.model.CustomerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.util.*;

@Repository
public class CustomerRepository {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CustomerRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    UUID save(Customer customer) {
        customer.setId(UUID.randomUUID());
        int update = jdbcTemplate.update("insert into customer(customer_id, type, name) values (UUID_TO_BIN(:customerId),:type,:name)", toPramMap(customer));
        if (update != 1) {
            throw new RuntimeException("Noting was inserted");
        }
        return customer.getId();
    }

    Optional<Customer> findById(UUID customerId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from customer where customer_id = UUID_TO_BIN(:customerId)",
                    Collections.singletonMap("customerId",customerId.toString().getBytes()), rowMapper));
        } catch (EmptyResultDataAccessException e) {
            logger.error("Got empty result", e);
            return Optional.empty();
        }
    }

    List<Customer> findAll() {
        return jdbcTemplate.query("select * from customer", rowMapper);
    }

    Customer update(Customer customer) {
        int update = jdbcTemplate.update("update customer set name = :name, type = :type where customer_id = UUID_TO_BIN(:customerId)", toPramMap(customer));
        if (update != 1) {
            throw new RuntimeException("Noting was updated");
        }
        return customer;
    }

    void deleteById(UUID customerId) {
        jdbcTemplate.getJdbcTemplate().update("delete from customer where customer_id = UUID_TO_BIN(:customerId)");
    }

    void deleteAll() {
        jdbcTemplate.getJdbcTemplate().update("delete from customer");
    }

    private UUID toUUID(byte[] bytes) throws SQLException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
    }

    private final RowMapper<Customer> rowMapper = (resultSet, i) -> {
        String name = resultSet.getString("name");
        CustomerType type = CustomerType.valueOf(resultSet.getString("type"));
        Customer customer = new Customer(name, type);
        customer.setId(toUUID(resultSet.getBytes("customer_id")));
        return customer;
    };

    private Map<String, Object> toPramMap(Customer customer) {
        return new HashMap<>() {{
            put("customerId", customer.getId().toString().getBytes());
            put("type", customer.getType().name());
            put("name", customer.getName());
        }};
    }
}
