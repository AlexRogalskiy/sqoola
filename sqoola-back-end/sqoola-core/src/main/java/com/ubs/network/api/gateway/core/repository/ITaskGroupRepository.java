package com.ubs.network.api.gateway.core.repository;

import com.ubs.network.api.rest.core.model.dto.TaskGroupDTO;
import com.ubs.network.api.gateway.core.model.entities.TaskGroupEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "taskunit", path = "taskunit")
public interface ITaskGroupRepository<E extends TaskGroupEntity, D extends TaskGroupDTO> extends ICoreBaseRepository<E, D>, PagingAndSortingRepository<E, Long> {
    @Query(value="select v.* from Option o, Vote v where o.POLL_ID = ?1 and v.OPTION_ID = o.OPTION_ID", nativeQuery = true)
    Iterable<E> findByStatus(final Long id);

    @Query(value="select v.* from Option o, Vote v where o.POLL_ID = ?1 and v.OPTION_ID = o.OPTION_ID", nativeQuery = true)
    Iterable<E> findByDate(final Long pollId);

    Iterable<E> findByTaskUnitName(final @Param("name") String name);
}
