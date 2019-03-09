package com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dao.listeners;

import com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dao.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Slf4j
@Component(OperationEventListener.COMPONENT_ID)
public class OperationEventListener {

    /**
     * Default component ID
     */
    @Transient
    public static final String COMPONENT_ID = "operationEventListener";

    @PostPersist
    public <ID extends Serializable> void postPersist(final BaseModel<ID> entity) {
        log.debug("@PostPersist: entity = {}, id = {}", entity.getClass().getCanonicalName(), entity.getId());
    }

    @PostUpdate
    public <ID extends Serializable> void postUpdate(final BaseModel<ID> entity) {
        log.debug("@PostUpdate: entity = {}, state = {}, id = {}", entity.getClass().getCanonicalName(), entity.getId());
    }

    @PostRemove
    public <ID extends Serializable> void postDelete(final BaseModel<ID> entity) {
        log.debug("@PostRemove: entity = {}, state = {}, id = {}", entity.getClass().getCanonicalName(), entity.getId());
    }

    @PostLoad
    public <ID extends Serializable> void postLoad(final BaseModel<ID> entity) {
        log.debug("@PostLoad: entity = {}, state = {}, id = {}", entity.getClass().getCanonicalName(), entity.getId());
    }
}
