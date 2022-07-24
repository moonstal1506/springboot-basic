package com.prgrms.voucher.model;

import java.util.UUID;

public class PercentDiscountVoucher implements Voucher {

    private final UUID voucherId;
    private final long percent;

    public PercentDiscountVoucher(UUID voucherId, long percent) {
        this.voucherId = voucherId;
        this.percent = percent;
    }

    @Override
    public UUID getId() {
        return voucherId;
    }

    @Override
    public long discount(long beforeDiscount) {
        return (long) (beforeDiscount - beforeDiscount * (percent * 0.01));
    }

    public static PercentDiscountVoucher create(long percent) {
        return new PercentDiscountVoucher(UUID.randomUUID(), percent);
    }
}
