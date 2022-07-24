package com.prgrms.voucher.controller;

import com.prgrms.voucher.model.Voucher;
import com.prgrms.voucher.model.VoucherType;
import com.prgrms.voucher.service.VoucherService;
import com.prgrms.voucher.view.InputView;
import com.prgrms.voucher.view.OutputView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class VoucherController {

    private static final Logger log = LoggerFactory.getLogger(VoucherController.class);
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
            try {
                execute(InputView.inputCommand());
            } catch (Exception e) {
                log.error(e.getMessage());
                OutputView.showErrorMessage(e.getMessage());
            }
        }
    }

    private void execute(String command) {
        switch (command) {
            case EXIT:
                OutputView.showExitMessage();
                System.exit(0);
                break;
            case CREATE:
                create();
                break;
            case LIST:
                OutputView.showVouchers(voucherService.findVouchers());
                break;
        }
    }

    private void create() {
        VoucherType voucherType = InputView.inputVoucherType();
        long discount = InputView.inputDiscount(voucherType.getType());
        Voucher voucher = voucherType.create(discount);
        voucherService.save(voucher);
        OutputView.showCreateMessage(voucher);
    }
}