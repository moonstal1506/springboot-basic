package com.prgrms.voucher.voucher.model;

import java.util.UUID;

public class PercentDiscountVoucher implements Voucher {

    private static final VoucherType voucherType = VoucherType.PERCENT;
    private long percent;
    private UUID id;

    public PercentDiscountVoucher(long percent) {
        this.percent = percent;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID voucherId) {
        this.id = voucherId;
    }

    @Override
    public long discount(long beforeDiscount) {
        return (long) (beforeDiscount - beforeDiscount * (percent * 0.01));
    }

    @Override
    public long getValue() {
        return percent;
    }

    @Override
    public void changeValue(long value) {
        this.percent = value;
    }

    @Override
    public VoucherType getType() {
        return voucherType;
    }

    public static PercentDiscountVoucher create(long percent) {
        return new PercentDiscountVoucher(percent);
    }

    @Override
    public String toString() {
        return "PercentDiscountVoucher{" +
                "voucherId=" + id +
                ", percent=" + percent +
                '}';
    }
}
