package com.packt.quarkus.chapter7;


import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonString;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Path("customers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")

public class CustomerEndpoint {
    private static final Logger LOGGER = Logger.getLogger(CustomerEndpoint.class.getName());

    @Inject
    JsonWebToken jwt;


    @Inject
    @Claim(standard = Claims.groups)
    Optional<JsonString> groups;

    @Inject
    @Claim(standard = Claims.preferred_username)
    Optional<JsonString> currentUsername;


    @Inject CustomerRepository customerRepository;

    @GET
    @RolesAllowed("user")
    public List<Customer> getAll() {

        LOGGER.info("Username: "+currentUsername);
        LOGGER.info("Group claim: "+groups);
        return customerRepository.findAll();
    }

    @POST
    @RolesAllowed("admin")
    public Response create(Customer customer) {

        customerRepository.createCustomer(customer);
        return Response.status(201).build();

    }

    @PUT
    @RolesAllowed("admin")
    public Response update(Customer customer) {
        customerRepository.updateCustomer(customer);
        return Response.status(204).build();
    }
    @DELETE
    @RolesAllowed("admin")
    public Response delete(@QueryParam("id") Long customerId) {
        customerRepository.deleteCustomer(customerId);
        return Response.status(204).build();
    }

}
