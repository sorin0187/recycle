package no.sonat.assignment.service;

import lombok.AccessLevel;
import lombok.Getter;
import no.sonat.assignment.entity.RecycledBottle;
import no.sonat.assignment.entity.RecycledObject;
import no.sonat.assignment.exception.CouldNotRecycleException;
import no.sonat.assignment.qualifier.Bottle;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Bottle
@ApplicationScoped
public class BottleRecyclingService extends RecyclingService<RecycledBottle> {

    @Inject
    @ConfigProperty(name = "recycle.bottle.duration", defaultValue = "2000")
    long recyclingDuration;

    @ConfigProperty(name = "recycle.bottle.price", defaultValue = "10")
    @Getter(AccessLevel.PACKAGE)
    long price;

    @Override
    void doRecycle(final RecycledObject object) throws CouldNotRecycleException {
        try {
            Thread.sleep(recyclingDuration);
        } catch (InterruptedException e) {
            throw new CouldNotRecycleException(object);
        }
    }

    RecycledBottle newObject() {
        return new RecycledBottle();
    }

    @Override
    public List<RecycledBottle> getAll() {
        return RecycledBottle.listAll();
    }

}
