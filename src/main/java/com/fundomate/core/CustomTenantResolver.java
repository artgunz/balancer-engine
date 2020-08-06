package com.fundomate.core;

import io.quarkus.arc.Unremovable;
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver;
import io.vertx.ext.web.RoutingContext;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

// TODO: 05.08.2020 Use DB approach
// TODO: 06.08.2020 Use header approach
@RequestScoped
@Unremovable
public class CustomTenantResolver implements TenantResolver {

    @Inject
    RoutingContext context;

    @ConfigProperty(name = "tenants")
    List<String> tenants;

    @Override
    public String getDefaultTenantId() {
        return "base";
    }

    @Override
    public String resolveTenantId() {
        String path = context.request().path();
        String[] parts = path.split("/");

        if (parts.length == 0) {
            // resolve to default tenant config
            return getDefaultTenantId();
        }

        if (tenants.contains(parts[1])) {
            return parts[1];
        }

        return getDefaultTenantId();
    }

}
