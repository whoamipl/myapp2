package com.example.restwsejbdemo.rest;

import com.example.restwsejbdemo.domain.Computer;
import com.example.restwsejbdemo.domain.Producer;
import com.example.restwsejbdemo.domain.Owner;
import com.example.restwsejbdemo.domain.Warranty;
import com.example.restwsejbdemo.service.ComputerManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("computer")
@Stateless
public class ComputerRESTService {

    @EJB
    private ComputerManager computerManager;

    @GET
    @Path("/{computerId}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Computer getComputer(@PathParam("computerId") Long id) {
        Computer computer = computerManager.getComputer(id);
        return computer;
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addComputer(Computer computer) {
        computerManager.addComputer(computer);
        return Response.status(201).entity("Computer").build();
    }

    @DELETE
    @Path("/remove/{id}")
    @Transactional
    public void deleteComputer(@PathParam("id") Long id) {
        computerManager.deleteComputer(computerManager.getComputer(id));

    }

    @DELETE
    @Path("/removeall")
    @Transactional
    public Response clearComputers() {
        computerManager.deleteAll();
        return Response.status(200).build();
    }

    @GET
    @Path("/bycity/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getComputersAuthor(@PathParam("city") String city) {

        List<Object[]> ownerCities = computerManager.getComputersByOwnerCity(city);
        JsonArrayBuilder authors = Json.createArrayBuilder();

        for (Object[] o : ownerCities) {
            String name  = (String) o[0];
            String surname = (String) o[1];
            String model = (String) o[2];

            authors.add(Json.createObjectBuilder()
                    .add("name", name)
                    .add("surname", surname)
                    .add("model", model));

        }
        JsonObject json = Json.createObjectBuilder().add("result", authors).build();
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/bymodel/{model}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Computer> getComputersByPrice(@PathParam("model") String model) {
        return computerManager.getComputersByModel(model);
    }


    @GET
    @Path("/manytomany")
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String testManyToManyRelation() {

        Owner owner1 = new Owner("Janek", "Dzbanek","Sopot");
        Owner owner2 = new Owner("Franek", "Poranek","Gdynia");
        Computer computer1 = new Computer("MSI Razor X", 2999.99);

        computerManager.addComputer(computer1);

        List<Owner> owners = new ArrayList<>();
        owners.add(owner1);
        owners.add(owner2);

        computer1.addOwners(owners);
        computerManager.updateComputer(computer1);

        return "Test many to many relation";
    }

    @GET
    @Path("/lazyexp")
    @Produces(MediaType.TEXT_PLAIN)
    public Boolean lazyInitialization() {

        Owner owner1 = new Owner("Ewa", "Mewa","Gdynia");
        Owner owner2 = new Owner("Franek", "Dzbanek","Sopot");

        List<Owner> owners = new ArrayList<>();
        owners.add(owner1);
        owners.add(owner2);

        Producer producer = new Producer("Dell");
        Warranty warranty = new Warranty("basic");
        Computer computer = new Computer("XPS11", owners, 2000.00, producer, warranty);

        computerManager.addComputer(computer);

        Computer addedComputer = computerManager
                                        .getComputer((long) computerManager
                                        .getAllComputers().size());
        try {
            System.out.println(addedComputer.getProducer().getName());
        } catch (org.hibernate.LazyInitializationException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
