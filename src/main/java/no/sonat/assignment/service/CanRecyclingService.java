package no.sonat.assignment.service;

import lombok.AccessLevel;
import lombok.Getter;
import no.sonat.assignment.entity.RecycledBottle;
import no.sonat.assignment.entity.RecycledCan;
import no.sonat.assignment.entity.RecycledObject;
import no.sonat.assignment.exception.CouldNotRecycleException;
import no.sonat.assignment.qualifier.Can;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;

@Can
@ApplicationScoped
public class CanRecyclingService extends RecyclingService<RecycledCan> {

    @ConfigProperty(name = "recycle.can.duration", defaultValue = "3000")
    @Getter(AccessLevel.PACKAGE)
    long recyclingDuration;

    @ConfigProperty(name = "recycle.can.price", defaultValue = "10")
    @Getter(AccessLevel.PACKAGE)
    long price;

    @Override
    void doRecycle(RecycledObject object) throws CouldNotRecycleException {
        try {
            Thread.sleep(recyclingDuration);
        } catch (InterruptedException e) {
            throw new CouldNotRecycleException(object);
        }
    }

    RecycledCan newObject() {
        return new RecycledCan();
    }

    @Override
    public List<RecycledCan> getAll()  {
        return RecycledCan.listAll();
    }


}
