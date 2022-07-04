package no.sonat.assignment.boundary;

import no.sonat.assignment.entity.RecycledBottle;
import no.sonat.assignment.entity.RecycledObject;
import no.sonat.assignment.exception.CouldNotRecycleException;
import no.sonat.assignment.qualifier.Bottle;
import no.sonat.assignment.service.RecyclingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("bottle")
public class BottleResource {

    @Inject
    @Bottle
    RecyclingService<RecycledBottle> bottleService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RecycledObject recycleBottle() throws CouldNotRecycleException {
        return bottleService.recycle();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecycledBottle> getRecycledBottles() {
        return bottleService.getAll();
    }
}