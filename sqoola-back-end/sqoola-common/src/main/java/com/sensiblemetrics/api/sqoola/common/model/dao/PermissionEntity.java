package com.sensiblemetrics.api.sqoola.common.model.dao;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sq_permissions")
public class PermissionEntity {

    @Id
    @Column(name = "permission")
    private String permission;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<RoleEntity> roles = new HashSet<>();
}
