
package it.redhat.orm;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

import java.util.List;
import io.quarkus.panache.common.Sort;

@Path("regioni")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class RegioneResource {

    @GET
    public List<Regione> get() {
        return Regione.listAll(Sort.by("nome"));
    }

    @GET
    @Path("{id}")
    public Regione getSingle(@PathParam("id") Long id) {
        Regione entity = Regione.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Regione with id of " + id + " does not exist.", 404);
        }
        return entity;
    }
}