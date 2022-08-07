package com.prgrms.voucher.voucher.service;

import com.prgrms.voucher.voucher.controller.VoucherForm;
import com.prgrms.voucher.voucher.model.Voucher;
import com.prgrms.voucher.voucher.repository.VoucherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class VoucherService {

    private final VoucherRepository voucherRepository;

    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public UUID save(Voucher voucher) {
        voucherRepository.save(voucher);
        return voucher.getId();
    }

    @Transactional(readOnly = true)
    public List<Voucher> getVouchers() {
        return voucherRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Voucher> findVoucher(UUID id) {
        return voucherRepository.findById(id);
    }

    public Voucher updateVoucher(UUID id, VoucherForm form) {
        Voucher voucher = findVoucher(id).orElseThrow(() -> {
            throw new IllegalArgumentException("바우처를 찾을 수 없습니다.");
        });
        voucher.changeValue(form.getValue());
        return voucherRepository.updateValue(voucher);
    }

    public void deleteVoucher(UUID id) {
        voucherRepository.deleteById(id);
    }
}
