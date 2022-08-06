package com.prgrms.voucher.voucher.model;

import java.util.UUID;

public class FixedAmountVoucher implements Voucher {

    private static final int MAX_VOUCHER_AMOUNT = 10000;
    private static final VoucherType voucherType = VoucherType.FIXED;
    private long amount;
    private UUID id;

    public FixedAmountVoucher(long amount) {
        if(amount<0) {
            throw new IllegalArgumentException("Amount should be positive");
        }
        if(amount==0) {
            throw new IllegalArgumentException("Amount should not be zero");
        }
        if(amount> MAX_VOUCHER_AMOUNT){
            throw new IllegalArgumentException(String.format("Amount should be less than %d", MAX_VOUCHER_AMOUNT));
        }
        this.amount = amount;
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
        long discountedAmount = beforeDiscount - amount;
        return (discountedAmount < 0) ? 0: discountedAmount;
    }

    @Override
    public long getValue() {
        return amount;
    }

    @Override
    public void changeValue(long value) {
        this.amount = value;
    }

    @Override
    public VoucherType getType() {
        return voucherType;
    }

    public static FixedAmountVoucher create(long amount) {
        return new FixedAmountVoucher(amount);
    }

    @Override
    public String toString() {
        return "FixedAmountVoucher{" +
                "voucherId=" + id +
                ", amount=" + amount +
                '}';
    }
}
