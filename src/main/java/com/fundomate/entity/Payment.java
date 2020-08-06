package com.fundomate.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Payment extends PanacheEntity {

    @NotEmpty
    private String tenantId;

    @NotEmpty
    private String value;

    public static Collection<Payment> findAllToTenant(String tenantId) {
        return Payment.list("tenantId", tenantId);
    }
}
