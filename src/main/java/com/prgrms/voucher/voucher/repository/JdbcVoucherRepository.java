package com.prgrms.voucher.voucher.repository;


import com.prgrms.voucher.voucher.model.Voucher;
import com.prgrms.voucher.voucher.model.VoucherType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.util.*;


@Repository
public class JdbcVoucherRepository implements VoucherRepository{

    private static final Logger logger = LoggerFactory.getLogger(JdbcVoucherRepository.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcVoucherRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID save(Voucher voucher) {
        int update = jdbcTemplate.update("insert into voucher(voucher_id, type, value) values (UUID_TO_BIN(:voucherId),:type,:value)", toPramMap(voucher));
        if (update != 1) {
            throw new RuntimeException("Noting was inserted");
        }
        return voucher.getId();
    }

    @Override
    public Optional<Voucher> findById(UUID voucherId) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from voucher where voucher_id = UUID_TO_BIN(:voucherId)",
                    Collections.singletonMap("voucherId",voucherId.toString().getBytes()), rowMapper));
        } catch (EmptyResultDataAccessException e) {
            logger.error("Got empty result", e);
            return Optional.empty();
        }
    }

    @Override
    public List<Voucher> findAll() {
        return jdbcTemplate.query("select * from voucher", rowMapper);
    }

    @Override
    public Voucher updateValue(Voucher voucher) {
        int update = jdbcTemplate.update("update voucher set value = :value where voucher_id = UUID_TO_BIN(:voucherId)", toPramMap(voucher));
        if (update != 1) {
            throw new RuntimeException("Noting was updated");
        }
        return voucher;
    }

    @Override
    public void deleteById(UUID voucherId) {
        jdbcTemplate.getJdbcTemplate().update("delete from voucher where voucher_id = UUID_TO_BIN(:voucherId)");
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.getJdbcTemplate().update("delete from voucher");
    }

    private UUID toUUID(byte[] bytes) throws SQLException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
    }

    private final RowMapper<Voucher> rowMapper = (resultSet, i) -> {
        VoucherType voucherType = VoucherType.of(resultSet.getString("type"));
        Voucher voucher = voucherType.create(resultSet.getLong("value"));
        voucher.setId(toUUID(resultSet.getBytes("voucher_id")));
        return voucher;
    };

    private Map<String, Object> toPramMap(Voucher voucher) {
        return new HashMap<>() {{
            put("voucherId", voucher.getId().toString().getBytes());
            put("type", voucher.getVoucherType().getType());
            put("value", voucher.getValue());
        }};
    }
}
