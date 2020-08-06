package com.fundomate.service;

import com.fundomate.entity.Payment;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@ApplicationScoped
public class PaymentService {
    public Collection<Payment> findAllToTenant(String tenantId) {
        return Payment.findAllToTenant(tenantId);
    }
}
