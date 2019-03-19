package com.sensiblemetrics.api.sqoola.common.model.dao.listeners.event;

import com.sensiblemetrics.api.sqoola.common.model.dao.BaseModelEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.RefreshEvent;
import org.hibernate.event.spi.RefreshEventListener;

import java.util.Map;

@Slf4j
public class RefreshEventListenerImp implements RefreshEventListener {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 8603892619424226458L;

    @Override
    public void onRefresh(final RefreshEvent event) throws HibernateException {
        final Object obj = event.getObject();
        if (obj instanceof BaseModelEntity) {
            final BaseModelEntity<?> entity = (BaseModelEntity<?>) obj;
            log.info("Refreshing entity with name={%s}, id={%s}", event.getEntityName(), entity.getId());
        }
    }

    @Override
    public void onRefresh(final RefreshEvent event, final Map refreshedAlready) throws HibernateException {
        this.onRefresh(event);
    }
}
