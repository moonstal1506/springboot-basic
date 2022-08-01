package com.prgrms.voucher.voucher.repository;

import com.prgrms.voucher.voucher.model.FixedAmountVoucher;
import com.prgrms.voucher.voucher.model.Voucher;
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

@SpringJUnitConfig
class JdbcVoucherRepositoryTest {

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
    JdbcVoucherRepository jdbcVoucherRepository;

    @BeforeEach
    void beforeEach() {
        jdbcVoucherRepository.deleteAll();
    }

    Voucher createVoucher() {
        Voucher voucher = new FixedAmountVoucher(1000L);
        voucher.setId(UUID.randomUUID());
        return voucher;
    }

    @Test
    void save() {
        Voucher savedVoucher = createVoucher();
        UUID savedId = jdbcVoucherRepository.save(savedVoucher);
        Voucher findVoucher = jdbcVoucherRepository.findById(savedId).get();
        assertThat(findVoucher.getId()).isEqualTo(savedVoucher.getId());
    }

    @Test
    void findAll() {
        Voucher voucher1 = createVoucher();
        Voucher voucher2 = createVoucher();
        jdbcVoucherRepository.save(voucher1);
        jdbcVoucherRepository.save(voucher2);

        List<Voucher> all = jdbcVoucherRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    void updateValue() {
        Voucher voucher = createVoucher();
        jdbcVoucherRepository.save(voucher);
        voucher.changeValue(2000L);
        Voucher updatedVoucher = jdbcVoucherRepository.updateValue(voucher);
        assertThat(updatedVoucher.getValue()).isEqualTo(2000L);
    }

}