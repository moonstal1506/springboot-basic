package com.prgrms.voucher;

import com.prgrms.voucher.configuration.AppConfiguration;
import com.prgrms.voucher.controller.VoucherController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CommandLineApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
                AppConfiguration.class);
        VoucherController controller = ac.getBean(VoucherController.class);
        controller.run();
    }
}
