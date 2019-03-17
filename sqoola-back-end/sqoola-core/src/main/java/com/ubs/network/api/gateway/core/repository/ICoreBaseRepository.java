package com.ubs.network.api.gateway.core.repository;

import com.ubs.network.api.rest.common.model.interfaces.IBaseDTO;
import com.ubs.network.api.rest.common.model.interfaces.IBaseEntity;
import com.ubs.network.api.gateway.common.repository.IBaseCrudRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface ICoreBaseRepository<E extends IBaseEntity, D extends IBaseDTO> extends IBaseCrudRepository<E, D> {

}
