package com.prgrms.voucher.voucher.model;

import java.util.Arrays;
import java.util.function.Function;

public enum VoucherType {

    FIXED("fixed",  FixedAmountVoucher::create),
    PERCENT("percent", PercentDiscountVoucher::create);

    private final String type;
    private final Function<Long, Voucher> factory;

    VoucherType(String type, Function<Long, Voucher> factory) {
        this.type = type;
        this.factory = factory;
    }

    public static VoucherType of(String type) {
        return Arrays.stream(VoucherType.values())
                .filter(v -> v.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(type+"을 조회할 수 없습니다."));
    }

    public String getType() {
        return type;
    }

    public Voucher create(long value) {
        return factory.apply(value);
    }
}
