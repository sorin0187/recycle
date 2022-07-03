package no.sonat.assignment.boundary;

import no.sonat.assignment.entity.Voucher;
import no.sonat.assignment.service.VoucherService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/voucher")
@ApplicationScoped
public class VoucherResource {

    @Inject
    VoucherService service;

    @POST
    public Voucher createVoucher() {
        return service.createVoucher();
    }

    @GET
    public List<Voucher> getAllVouchers() {
        return service.getAllVouchers();
    }

}
