package com.prgrms.voucher.view;

import com.prgrms.voucher.model.Voucher;

import java.util.List;

public class OutputView {

    public static void showVouchers(List<Voucher> vouchers) {
        vouchers.forEach(System.out::println);
    }
}
