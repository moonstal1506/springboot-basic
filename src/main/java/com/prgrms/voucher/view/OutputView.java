package com.prgrms.voucher.view;

import com.prgrms.voucher.model.Customer;
import com.prgrms.voucher.model.Voucher;

import java.util.List;

public class OutputView {

    private static final String EXIT_MESSAGE = "프로그램을 종료합니다.";
    private static final String CREATE_MESSAGE = "가 생성되었습니다.";

    public static void showVouchers(List<Voucher> vouchers) {
        vouchers.forEach(System.out::println);
    }

    public static void showBlackList(List<Customer> blackList) {
        blackList.forEach(System.out::println);
    }

        public static void showExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    public static void showCreateMessage(Voucher voucher) {
        System.out.println(voucher + CREATE_MESSAGE);
    }

    public static void showErrorMessage(String message) {
        System.out.println(message);
    }
}
