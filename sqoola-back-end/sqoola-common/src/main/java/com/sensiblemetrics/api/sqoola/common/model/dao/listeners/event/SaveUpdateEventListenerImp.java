package com.sensiblemetrics.api.sqoola.common.model.dao.listeners.event;

import com.sensiblemetrics.api.sqoola.common.model.dao.BaseModelEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

@Slf4j
public class SaveUpdateEventListenerImp implements SaveOrUpdateEventListener {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -6625750526033104563L;

    @Override
    public void onSaveOrUpdate(final SaveOrUpdateEvent event) throws HibernateException {
        final Object obj = event.getEntity();
        if (obj instanceof BaseModelEntity) {
            final BaseModelEntity<?> entity = (BaseModelEntity<?>) obj;
            log.info("Refreshing entity with name={%s}, id={%s}", event.getEntityName(), entity.getId());
        }
    }
}
