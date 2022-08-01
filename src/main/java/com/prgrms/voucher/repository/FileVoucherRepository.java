package com.prgrms.voucher.repository;

import com.prgrms.voucher.model.Voucher;
import com.prgrms.voucher.model.VoucherType;
import com.prgrms.voucher.view.InputView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile("dev")
@Repository
public class FileVoucherRepository implements VoucherRepository {

    private static final Logger log = LoggerFactory.getLogger(FileVoucherRepository.class);
    private final File file = new File("voucher.txt");

    @Override
    public UUID save(Voucher voucher) {
        try {
            voucher.setId(UUID.randomUUID());
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String line = voucher.getVoucherType() + "," + voucher.getId() + "," + voucher.getValue() + "\n";
            bufferedWriter.write(line);
            bufferedWriter.flush();
        } catch (IOException e) {
            log.error("파일 저장 실패", e);
        }
        return voucher.getId();
    }

    @Override
    public Optional<Voucher> findById(UUID voucherId) {
        return Optional.empty();
    }

    @Override
    public List<Voucher> findAll() {
        List<Voucher> vouchers = new ArrayList<>();
        String line;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] savedVoucher = line.split(",");
                VoucherType voucherType = VoucherType.valueOf(savedVoucher[0]);
                long discount = Integer.parseInt(savedVoucher[2]);
                Voucher voucher = voucherType.create(discount);
                voucher.setId(UUID.fromString(savedVoucher[1]));
                vouchers.add(voucher);
            }
        } catch (IOException | NullPointerException e) {
            log.error("파일 읽기 실패", e);
        }
        return List.copyOf(vouchers);
    }
}
