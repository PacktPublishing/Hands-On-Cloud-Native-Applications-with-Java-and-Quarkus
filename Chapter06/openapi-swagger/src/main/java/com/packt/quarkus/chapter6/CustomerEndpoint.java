package com.packt.quarkus.chapter6;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("customers")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
@Tag(name = "OpenAPI Example", description = "Quarkus CRUD Example")
public class CustomerEndpoint {

    @Inject CustomerRepository customerRepository;

    @Operation(operationId = "all", description = "Getting All customers")
    @APIResponse(responseCode = "200", description = "Successful response.")
    @GET
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @POST
    public Response create( @Parameter(description = "The new customer.", required = true) Customer customer) {

        customerRepository.createCustomer(customer);
        return Response.status(201).build();

    }

    @PUT
    public Response update(@Parameter(description = "The customer to update.", required = true) Customer customer) {
        customerRepository.updateCustomer(customer);
        return Response.status(204).build();
    }
    @DELETE
    public Response delete(@Parameter(description = "The customer to delete.", required = true) @QueryParam("id") Long customerId) {
        customerRepository.deleteCustomer(customerId);
        return Response.status(204).build();
    }

}
