package com.prgrms.voucher.voucher.controller;

import com.prgrms.voucher.voucher.model.Voucher;
import com.prgrms.voucher.voucher.service.VoucherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class VoucherController {

    private final VoucherService voucherService;

    @GetMapping
    public String findVouchers(Model model) {
        List<Voucher> vouchers = voucherService.getVouchers();
        model.addAttribute("vouchers", vouchers);
        return "vouchers/voucherList";
    }

    @GetMapping("/vouchers/{id}")
    public String findVoucher(@PathVariable("id") UUID id, Model model) {
        Optional<Voucher> voucher = voucherService.findVoucher(id);
        if (voucher.isPresent()) {
            model.addAttribute("voucher", voucher.get());
            return "vouchers/details";
        }else {
            return "error/404";
        }
    }
}
