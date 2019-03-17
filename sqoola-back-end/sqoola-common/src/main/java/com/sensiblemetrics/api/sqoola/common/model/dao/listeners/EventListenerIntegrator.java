package com.sensiblemetrics.api.sqoola.common.model.dao.listeners;

import com.sensiblemetrics.api.sqoola.common.model.dao.listeners.event.LoadEventListenerImp;
import com.sensiblemetrics.api.sqoola.common.model.dao.listeners.event.RefreshEventListenerImp;
import com.sensiblemetrics.api.sqoola.common.model.dao.listeners.event.SaveUpdateEventListenerImp;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class EventListenerIntegrator implements Integrator {

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {

        final EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);
        eventListenerRegistry.getEventListenerGroup(EventType.SAVE).appendListener(new SaveUpdateEventListenerImp());
        eventListenerRegistry.getEventListenerGroup(EventType.LOAD).appendListener(new LoadEventListenerImp());
        eventListenerRegistry.getEventListenerGroup(EventType.REFRESH).appendListener(new RefreshEventListenerImp());
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
    }
}
