package com.prgrms.voucher.voucher.controller;

import com.prgrms.voucher.voucher.model.Voucher;
import com.prgrms.voucher.voucher.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/vouchers")
@RequiredArgsConstructor
public class VoucherApiController {

    private final VoucherService voucherService;

    @GetMapping
    public List<Voucher> findVouchers() {
        return voucherService.getVouchers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voucher> findVoucher(@PathVariable("id") UUID id) {
        Optional<Voucher> voucher = voucherService.findVoucher(id);
        return voucher.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<?> saveVoucher(@RequestBody VoucherForm form) {
        Voucher voucher = form.getType().create(form.getValue());
        voucherService.save(voucher);
        return new ResponseEntity<>(form, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateVoucher(@PathVariable("id") UUID id, @RequestBody VoucherForm form) {
        voucherService.updateVoucher(id, form);
        return new ResponseEntity<>(form, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVoucher(@PathVariable("id") UUID id) {
        voucherService.deleteVoucher(id);
        return new ResponseEntity<>("삭제성공", HttpStatus.OK);
    }


}
