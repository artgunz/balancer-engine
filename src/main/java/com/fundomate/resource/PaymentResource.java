package com.fundomate.resource;

import com.fundomate.service.PaymentService;
import io.quarkus.security.identity.SecurityIdentity;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Path("/{tenantId}")
public class PaymentResource {

    @Inject
    SecurityIdentity identity;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    PaymentService paymentService;

    @GET
    @Path("/payments")
    public Response getPayments(@PathParam("tenantId") String tenantId) {
        return Response.ok(paymentService.listAll()).build();
    }

}
