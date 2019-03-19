package com.sensiblemetrics.api.sqoola.common.model.dao.listeners;

import com.sensiblemetrics.api.sqoola.common.model.dao.BaseModelEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Slf4j
@Component(ModelEventListener.COMPONENT_ID)
public class ModelEventListener {

    /**
     * Default component ID
     */
    @Transient
    public static final String COMPONENT_ID = "operationEventListener";

    @PrePersist
    public void prePersist(final BaseModelEntity<?> entity) {
        log.debug("@PrePersist: entity = {}, id = {}", entity.getClass().getCanonicalName(), entity.getId());
    }

    @PreUpdate
    public void preUpdate(final BaseModelEntity<?> entity) {
        log.debug("@PreUpdate: entity = {}, id = {}", entity.getClass().getCanonicalName(), entity.getId());
    }

    @PreRemove
    public void preRemove(final BaseModelEntity<?> entity) {
        log.debug("@PreRemove: entity = {}, state = {}, id = {}", entity.getClass().getCanonicalName(), entity.getId());
    }

    @PostPersist
    public void postPersist(final BaseModelEntity<?> entity) {
        log.debug("@PostPersist: entity = {}, id = {}", entity.getClass().getCanonicalName(), entity.getId());
    }

    @PostUpdate
    public void postUpdate(final BaseModelEntity<?> entity) {
        log.debug("@PostUpdate: entity = {}, state = {}, id = {}", entity.getClass().getCanonicalName(), entity.getId());
    }

    @PostRemove
    public void postDelete(final BaseModelEntity<?> entity) {
        log.debug("@PostRemove: entity = {}, state = {}, id = {}", entity.getClass().getCanonicalName(), entity.getId());
    }

    @PostLoad
    public void postLoad(final BaseModelEntity<?> entity) {
        log.debug("@PostLoad: entity = {}, state = {}, id = {}", entity.getClass().getCanonicalName(), entity.getId());
    }
}
