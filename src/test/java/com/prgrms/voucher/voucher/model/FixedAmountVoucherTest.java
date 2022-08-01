package com.prgrms.voucher.voucher.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedAmountVoucherTest {

    @Test
    @DisplayName("주어진 금액만큼 할인을 해야한다.")
    void discount() {
        FixedAmountVoucher voucher = new FixedAmountVoucher(100);
        assertEquals(900, voucher.discount(1000));
    }

    @Test
    @DisplayName("디스카운트된 금액은 마이너스가 될 수 없다.")
    void minusDiscountedAmount() {
        FixedAmountVoucher voucher = new FixedAmountVoucher(1000);
        assertEquals(0, voucher.discount(900));
    }

    @Test
    @DisplayName("할인금액은 마이너스가 될 수 없다.")
    void withMinus() {
        assertThrows(IllegalArgumentException.class,
                () -> new FixedAmountVoucher(-100));
    }
}