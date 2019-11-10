package com.packt.quarkus.chapter7;

import io.quarkus.security.identity.SecurityIdentity;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Path("customers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")

public class CustomerEndpoint {
    private static final Logger LOGGER = Logger.getLogger(CustomerEndpoint.class.getName());

    @Inject
    SecurityIdentity securityContext;

    @Inject CustomerRepository customerRepository;

    @GET
    @RolesAllowed("user")
    public List<Customer> getAll() {

        LOGGER.info("Connected with User "+securityContext.getPrincipal().getName());
        Iterator<String> roles = securityContext.getRoles().iterator();
        while (roles.hasNext()) {
           LOGGER.info("Role: "+roles.next());
        }
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
