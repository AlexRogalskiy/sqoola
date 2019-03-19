package com.sensiblemetrics.api.sqoola.common.model.dao.listeners.event;

import com.sensiblemetrics.api.sqoola.common.model.dao.BaseModelEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;

@Slf4j
public class LoadEventListenerImp implements LoadEventListener {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 901830393897397996L;

    @Override
    public void onLoad(final LoadEvent event, final LoadType type) throws HibernateException {
        final Object obj = event.getResult();
        if (obj instanceof BaseModelEntity) {
            final BaseModelEntity<?> entity = (BaseModelEntity<?>) obj;
            log.info("Loading entity with name={%s}, type={%s}, id={%s}", event.getEntityClassName(), type.getName(), entity.getId());
        }
    }
}
