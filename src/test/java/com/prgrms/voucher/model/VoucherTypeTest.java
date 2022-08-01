package com.prgrms.voucher.model;

import com.prgrms.voucher.voucher.model.Voucher;
import com.prgrms.voucher.voucher.model.VoucherType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class VoucherTypeTest {

    @Test
    void createFixedAmountVoucher() throws Exception {
        VoucherType fixed = VoucherType.of("fixed");
        Voucher voucher = fixed.create(2000L);

//        타입따라서 생성은 확인했는데
//        voucher.getClass()하면 java.lang.Class 나옴 어떻게 테스트 하지
//        assertThat(voucher.getClass()).isInstanceOf(FixedAmountVoucher.class);

        long discount = voucher.discount(10000L);
        assertThat(discount).isEqualTo(8000L);
    }

    @Test
    void PercentDiscountVoucher() throws Exception {
        VoucherType percent = VoucherType.of("percent");
        Voucher voucher = percent.create(30);
        long discount = voucher.discount(10000L);
        assertThat(discount).isEqualTo(7000L);
    }

    @Test
    @DisplayName("없는 VoucherType 조회시 예외발생")
    void noVoucher() throws Exception {
        assertThatThrownBy(() -> VoucherType.of("xxx"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}