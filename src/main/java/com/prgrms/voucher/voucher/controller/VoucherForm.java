package com.prgrms.voucher.voucher.controller;

import com.prgrms.voucher.voucher.model.Voucher;
import com.prgrms.voucher.voucher.model.VoucherType;
import lombok.Data;

import java.util.UUID;

@Data
public class VoucherForm {
    private long value;
    private VoucherType type;
}
