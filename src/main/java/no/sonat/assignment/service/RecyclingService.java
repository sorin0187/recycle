package no.sonat.assignment.service;

import lombok.extern.jbosslog.JBossLog;
import no.sonat.assignment.entity.RecycledCan;
import no.sonat.assignment.entity.RecycledObject;
import no.sonat.assignment.exception.CouldNotRecycleException;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@JBossLog
public abstract class RecyclingService<T extends RecycledObject> {

    @Transactional
    public RecycledObject recycle() throws CouldNotRecycleException {
        final Instant startedAt = Instant.now();
        final RecycledObject recycledObject = newObject()
                .setInsertedAt(OffsetDateTime.now())
                .setPrice(getPrice());
        recycledObject.persist();
        doRecycle(recycledObject);
        return recycledObject;
    }

    abstract void doRecycle(final RecycledObject object) throws CouldNotRecycleException;

    abstract T newObject();

    abstract long getPrice();

    public abstract List<T> getAll();
}
