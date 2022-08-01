package com.prgrms.voucher.voucher.service;

import com.prgrms.voucher.voucher.model.Voucher;
import com.prgrms.voucher.voucher.repository.VoucherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VoucherService {

    private final VoucherRepository voucherRepository;

    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public UUID save(Voucher voucher){
        voucherRepository.save(voucher);
        return voucher.getId();
    }

    public List<Voucher> getVouchers(){
        return voucherRepository.findAll();
    }

    public Voucher findVoucher(UUID voucherID){
        return voucherRepository.findById(voucherID).get();
    }
}
