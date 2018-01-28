package com.example.restwsejbdemo.rest;

import com.example.restwsejbdemo.domain.Warranty;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("warranty")
@Stateless
public class WarrantyRESTService {

    @PersistenceContext
    EntityManager entityManager;

    @GET
    @Path("/{warrantyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Warranty getWarranty(@PathParam("warrantyId") Long id) {
        return entityManager.find(Warranty.class, id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addWarranty(Warranty warranty) {
        entityManager.persist(warranty);
        return Response.status(201).entity("Warranty").build();
    }

    @DELETE
    @Path("/remove/{id}")
    public void deleteBook(@PathParam("id") Long id) {
        Warranty warranty = entityManager.find(Warranty.class, id);
        entityManager.remove(warranty);
    }
}
