package com.fundomate.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@FilterDef(name = "tenantFilter", parameters = @ParamDef(name="tenantId", type = "string"))
@Filter(name = "tenantFilter", condition = "tenantId = :tenantId")
public class BaseEntity extends PanacheEntityBase { //Move to repository based approach

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Version
    private int version;

    private String tenantId;
}
