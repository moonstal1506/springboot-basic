package com.prgrms.voucher.model;

import java.util.UUID;

public interface Voucher {

    UUID getId();

    long discount(long beforeDiscount);
}
