package com.prgrms.voucher.voucher.model;

import java.util.UUID;

public interface Voucher {
    long discount(long beforeDiscount);

    void changeValue(long value);

    UUID getId();

    long getValue();

    VoucherType getType();

    void setId(UUID voucherId);


}
