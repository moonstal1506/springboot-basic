package com.prgrms.voucher.controller;

import com.prgrms.voucher.model.Voucher;
import com.prgrms.voucher.model.VoucherType;
import com.prgrms.voucher.service.VoucherService;
import com.prgrms.voucher.view.InputView;
import com.prgrms.voucher.view.OutputView;
import org.springframework.stereotype.Component;

@Component
public class VoucherController {

    public static final String COMMAND_INPUT_ERROR = "잘못된 입력입니다. 다시 입력해주세요.";
    public static final String EXIT = "exit";
    public static final String CREATE = "create";
    public static final String LIST = "list";

    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            execute(InputView.inputCommand());
        }
    }

    private void execute(String command) {
        switch (command) {
            case EXIT:
                System.exit(0);
                break;
            case CREATE:
                create();
                break;
            case LIST:
                OutputView.showVouchers(voucherService.findVouchers());
                break;
            default:
                throw new IllegalArgumentException(COMMAND_INPUT_ERROR);
        }
    }

    private void create() {
        VoucherType voucherType = InputView.inputVoucherType();
        long discount = InputView.inputDiscount();
        Voucher voucher = voucherType.create(discount);
        voucherService.save(voucher);
    }
}
