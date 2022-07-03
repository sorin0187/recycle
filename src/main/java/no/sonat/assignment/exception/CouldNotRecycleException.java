package no.sonat.assignment.exception;

import no.sonat.assignment.entity.RecycledObject;

public class CouldNotRecycleException extends Exception {

    private static final String MESSAGE = "Could not recycle %s";

    public CouldNotRecycleException(final RecycledObject object) {
        super(computeMessage(object));
    }

    public CouldNotRecycleException(final RecycledObject object, final Throwable cause) {
        super(computeMessage(object), cause);
    }

    private static String computeMessage(final RecycledObject object) {
        return String.format(MESSAGE, object.getClass().getName());
    }
}
