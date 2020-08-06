package com.fundomate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundomate.entity.Payment;
import io.quarkus.arc.Arc;
import io.quarkus.security.identity.SecurityIdentity;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.hibernate.Filter;
import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Collection;

@RequestScoped
public class PaymentService {
    @Claim(standard = Claims.preferred_username)
    String username;

    @Claim(standard = Claims.sub)
    String subject;

    @Claim(standard = Claims.iss)
    String issuer;

    @Inject
    SecurityIdentity identity;

    @Inject
    ObjectMapper objectMapper;

    public Collection<Payment> listAll(){
        return Payment.listAll();
    }

    @PostConstruct
    public void hibernateFilter() {
        Session session = Arc.container().instance(EntityManager.class).get().unwrap(Session.class);
        Filter filter = session.enableFilter("tenantFilter");
        filter.setParameter("tenantId", issuer);
        filter.validate();
    }
}
