package no.sonat.assignment.service;

import no.sonat.assignment.entity.RecycledObject;
import no.sonat.assignment.entity.Voucher;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class VoucherService {

    @Transactional
    public Voucher createVoucher() {
        final List<RecycledObject> objectsNotYetPayed = RecycledObject.findAllNotPaid();
        if (objectsNotYetPayed.isEmpty()) {
            return null;
        }
        final Voucher voucher = new Voucher()
                .setCode(UUID.randomUUID().toString())
                .setGeneratedAt(OffsetDateTime.now())
                .setValue(objectsNotYetPayed.stream().mapToLong(RecycledObject::getPrice).sum());
        voucher.persistAndFlush();
        objectsNotYetPayed.forEach(object -> object.setVoucher(voucher).persistAndFlush());
        return voucher;
    }

    public List<Voucher> getAllVouchers() {
        return Voucher.listAll();
    }

    public List<Voucher> findPaidVouchers() {
        return Voucher.list("#" + Voucher.FIND_PAID_VOUCHERS);
    }

    public List<Voucher> findUnpaidVouchers() {
        return Voucher.list("#" + Voucher.FIND_UNPAID_VOUCHERS);
    }

}
