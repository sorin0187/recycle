package no.sonat.assignment.boundary;

import no.sonat.assignment.entity.RecycledCan;
import no.sonat.assignment.entity.RecycledObject;
import no.sonat.assignment.exception.CouldNotRecycleException;
import no.sonat.assignment.qualifier.Can;
import no.sonat.assignment.service.RecyclingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("can")
public class CanResource {

    @Inject
    @Can
    RecyclingService<RecycledCan> canService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RecycledObject recycleCan() throws CouldNotRecycleException {
        return canService.recycle();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecycledCan> getRecycledCans() {
        return canService.getAll();
    }
}