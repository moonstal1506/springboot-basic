package com.prgrms.voucher.voucher.model;

import com.prgrms.voucher.voucher.model.FixedAmountVoucher;
import com.prgrms.voucher.voucher.model.PercentDiscountVoucher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class VoucherTest {

    @Test
    void fixedAmountVoucher() throws Exception {
        UUID voucherId = UUID.randomUUID();
        long amount = 1000;
        long beforeDiscount = 10000;

        FixedAmountVoucher fixedAmountVoucher = new FixedAmountVoucher(amount);
        long afterDiscount = fixedAmountVoucher.discount(beforeDiscount);

        Assertions.assertThat(afterDiscount).isEqualTo(9000L);
    }

    @Test
    void PercentDiscountVoucher() throws Exception {
        long percent = 10;
        long beforeDiscount = 10000;

        PercentDiscountVoucher percentDiscountVoucher = new PercentDiscountVoucher(percent);
        long afterDiscount = percentDiscountVoucher.discount(beforeDiscount);

        Assertions.assertThat(afterDiscount).isEqualTo(9000L);
    }
}