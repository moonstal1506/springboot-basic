package com.prgrms.voucher.view;

import com.prgrms.voucher.model.VoucherType;

import java.util.Scanner;

public class InputView {

    private static final String MENU = "=== Voucher Program ===";
    private static final String EXIT = "Type exit to exit the program.";
    private static final String CREATE = "Type create to create a new voucher.";
    private static final String LIST = "Type list to list all vouchers.";
    private static Scanner scanner = new Scanner(System.in);

    public static String inputCommand() {
        System.out.println(MENU);
        System.out.println(EXIT);
        System.out.println(CREATE);
        System.out.println(LIST);
        return scanner.nextLine();
    }

    public static VoucherType inputVoucherType() {
        System.out.println("Type fixed or percent");
        String type = scanner.nextLine();
        return VoucherType.of(type);
    }

    public static long inputDiscount(String type) {
        if(type.equals(VoucherType.FIXED.getType())){
            System.out.println("Type discount amount");
        }
        if(type.equals(VoucherType.PERCENT.getType())){
            System.out.println("Type percent rate");
        }
        return Integer.parseInt(scanner.nextLine());
    }
}
