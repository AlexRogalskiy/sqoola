package com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dao;

import javax.persistence.*;

@Entity
@Table(name = "sqoola_permissions")
public class Permission {
    @Id
    @Column(name = "permission")
    private String permission;

    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;

}
