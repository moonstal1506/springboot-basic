package com.prgrms.voucher.voucher.repository;

import com.prgrms.voucher.voucher.model.Voucher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VoucherRepository {

    UUID save(Voucher voucher);

    Optional<Voucher> findById(UUID voucherId);

    List<Voucher> findAll();

    Voucher updateValue(Voucher voucher);

    void deleteById(UUID voucherId);

    void deleteAll();
}
