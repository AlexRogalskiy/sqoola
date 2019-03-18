package com.sensiblemetrics.api.sqoola.common.model.dao;

import javax.persistence.*;

@Entity
@Table(name = "sqoola_permissions")
public class PermissionEntity {
    @Id
    @Column(name = "permission")
    private String permission;

    @ManyToOne
    @JoinColumn(name = "role")
    private RoleEntity role;

}
