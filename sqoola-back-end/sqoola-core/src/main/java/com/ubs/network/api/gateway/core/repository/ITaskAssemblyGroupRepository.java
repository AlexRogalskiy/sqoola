package com.ubs.network.api.gateway.core.repository;

import com.ubs.network.api.rest.core.model.dto.TaskAssemblyDTO;
import com.ubs.network.api.gateway.core.model.entities.TaskAssemblyEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ITaskAssemblyGroupRepository<E extends TaskAssemblyEntity, D extends TaskAssemblyDTO> extends ICoreBaseRepository<E, D>, PagingAndSortingRepository<E, Long> {
}
