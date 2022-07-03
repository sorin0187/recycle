package no.sonat.assignment.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;
import lombok.experimental.Accessors;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "voucher")
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = Voucher.FIND_PAID_VOUCHERS, query = "SELECT v FROM Voucher v WHERE paid IS NULL"),
        @NamedQuery(name = Voucher.FIND_UNPAID_VOUCHERS, query = "SELECT v FROM Voucher v WHERE paid IS NOT NULL"),
})
@NoArgsConstructor
@Accessors(chain = true)
public class Voucher extends PanacheEntity {

    public static final String FIND_PAID_VOUCHERS = "Voucher.findPaid";
    public static final String FIND_UNPAID_VOUCHERS = "Voucher.findUnpaid";

    @Column(name = "code")
    private String code;

    @Column(name = "generated_at", nullable = false)
    private OffsetDateTime generatedAt;

    @Column
    private long value;

}


