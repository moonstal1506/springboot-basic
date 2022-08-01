package com.prgrms.voucher.voucher.repository;

import com.prgrms.voucher.voucher.model.FixedAmountVoucher;
import com.prgrms.voucher.voucher.model.Voucher;
import com.prgrms.voucher.voucher.repository.MemoryVoucherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryVoucherRepositoryTest {

    MemoryVoucherRepository repository = new MemoryVoucherRepository();

    @AfterEach
    public void afterEach(){
        repository.deleteAll();
    }

    Voucher createVoucher(){
        UUID voucherId = UUID.randomUUID();
        long amount = 1000;
        return new FixedAmountVoucher(amount);
    }

    @Test
    void save() {
        Voucher voucher = createVoucher();
        repository.save(voucher);
        Voucher result = repository.findById(voucher.getId()).get();
        assertThat(voucher).isEqualTo(result);
    }

    @Test
    void findAll() {
        Voucher voucher1 = createVoucher();
        Voucher voucher2 = createVoucher();
        repository.save(voucher1);
        repository.save(voucher2);

        List<Voucher> vouchers = repository.findAll();
        assertThat(vouchers.size()).isEqualTo(2);
    }
}