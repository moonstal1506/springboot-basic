package com.prgrms.voucher.voucher.controller;

import com.prgrms.voucher.voucher.model.Voucher;
import com.prgrms.voucher.voucher.model.VoucherType;
import com.prgrms.voucher.voucher.service.VoucherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class VoucherController {

    private final VoucherService voucherService;

    @ModelAttribute("voucherTypes")
    public VoucherType[] voucherTypes() {
        return VoucherType.values();
    }

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

    @GetMapping("/vouchers/new")
    public String viewVoucherPage(Model model) {
        model.addAttribute("voucher", new VoucherForm());
        return "vouchers/newForm";
    }

    @PostMapping("/vouchers/new")
    public String addNewVoucher(VoucherForm voucherForm) {
        Voucher voucher = voucherForm.getType().create(voucherForm.getValue());
        voucherService.save(voucher);
        return "redirect:/";
    }
}
