package no.sonat.assignment.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "recycled_object")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "object_type")
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@NamedQuery(name = RecycledObject.FIND_ALL_NOT_PAID, query = "SELECT o FROM RecycledObject o WHERE voucher IS NULL")
public abstract class RecycledObject extends PanacheEntity {

    public static final String FIND_ALL_NOT_PAID = "RecycledObject.findAllNotPaid";

    private static final String GENERATOR = "object_id_generator";

    @Column(name = "inserted_at", nullable = false)
    private OffsetDateTime insertedAt;

    @Column(name = "price", nullable = false)
    private long price;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    @JsonbTransient
    private Voucher voucher;

    public static List<RecycledObject> findAllNotPaid() {
        return list("#" + RecycledObject.FIND_ALL_NOT_PAID);
    }

    public boolean isPaid() {
        return voucher != null;
    }
}



