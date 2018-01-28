package com.example.restwsejbdemo.rest;


import com.example.restwsejbdemo.domain.Owner;
import com.example.restwsejbdemo.service.OwnerManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("owner")
@Stateless
public class OwnerRESTService {

    @EJB
    private OwnerManager ownerManager;

    @GET
    @Path("/{ownerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Owner getOwner(@PathParam("ownerId") Long id) {
        Owner p = ownerManager.getOwner(id);
        return p;
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOwner(Owner person) {
        ownerManager.addOwner(person);
        return Response.status(201).entity("Person").build();
    }

    @DELETE
    @Path("/remove/{id}")
    public void deleteOwner(@PathParam("id") Long id) {
        ownerManager.deleteOwner(ownerManager.getOwner(id));
    }

    @DELETE
    public Response deleteOwner() {
        ownerManager.clearOwners();
        return Response.status(200).build();
    }

}
