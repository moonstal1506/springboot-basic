package com.prgrms.voucher.voucher.model;

import java.util.UUID;

public interface Voucher {

    UUID getId();

    void setId(UUID voucherId);

    long discount(long beforeDiscount);

    long getValue();

    VoucherType getVoucherType();
}
