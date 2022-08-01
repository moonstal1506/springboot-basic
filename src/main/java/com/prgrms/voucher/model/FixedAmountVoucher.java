package com.prgrms.voucher.model;

import java.io.Serializable;
import java.util.UUID;

public class FixedAmountVoucher implements Voucher {

    private static final VoucherType voucherType = VoucherType.FIXED;
    private final long amount;
    private UUID voucherId;

    public FixedAmountVoucher(long amount) {
        this.amount = amount;
    }

    @Override
    public UUID getId() {
        return voucherId;
    }

    @Override
    public void setId(UUID voucherId) {
        this.voucherId = voucherId;
    }

    @Override
    public long discount(long beforeDiscount) {
        return beforeDiscount - amount;
    }

    @Override
    public long getValue() {
        return amount;
    }

    @Override
    public VoucherType getVoucherType() {
        return voucherType;
    }

    public static FixedAmountVoucher create(long amount) {
        return new FixedAmountVoucher(amount);
    }

    @Override
    public String toString() {
        return "FixedAmountVoucher{" +
                "voucherId=" + voucherId +
                ", amount=" + amount +
                '}';
    }
}
