package com.prgrms.voucher.model;

import java.util.UUID;

public class FixedAmountVoucher implements Voucher{

    private final UUID voucherId;
    private final long amount;

    public FixedAmountVoucher(UUID voucherId, long amount) {
        this.voucherId = voucherId;
        this.amount = amount;
    }

    @Override
    public UUID getId() {
        return voucherId;
    }

    @Override
    public long discount(long beforeDiscount) {
        return beforeDiscount - amount;
    }

    public static FixedAmountVoucher create(long amount) {
        return new FixedAmountVoucher(UUID.randomUUID(), amount);
    }
}
