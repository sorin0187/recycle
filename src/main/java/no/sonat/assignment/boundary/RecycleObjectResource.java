package no.sonat.assignment.boundary;

import no.sonat.assignment.entity.RecycledBottle;
import no.sonat.assignment.entity.RecycledCan;
import no.sonat.assignment.entity.RecycledObject;
import no.sonat.assignment.exception.CouldNotRecycleException;
import no.sonat.assignment.qualifier.Bottle;
import no.sonat.assignment.qualifier.Can;
import no.sonat.assignment.service.RecyclingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/recycle")
@ApplicationScoped
public class RecycleObjectResource {

    @Inject
    @Bottle
    RecyclingService<RecycledBottle> bottleService;

    @Inject
    @Can
    RecyclingService<RecycledCan> canService;

    @POST
    @Path("bottle")
    @Produces(MediaType.APPLICATION_JSON)
    public RecycledObject recycleBottle() throws CouldNotRecycleException {
        return bottleService.recycle();
    }

    @POST
    @Path("can")
    @Produces(MediaType.APPLICATION_JSON)
    public RecycledObject recycleCan() throws CouldNotRecycleException {
        return canService.recycle();
    }

    @GET
    @Path("can")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecycledCan> getRecycledCans() {
        return canService.getAll();
    }

    @GET
    @Path("bottle")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecycledBottle> getRecycledBottles() {
        return bottleService.getAll();
    }
}