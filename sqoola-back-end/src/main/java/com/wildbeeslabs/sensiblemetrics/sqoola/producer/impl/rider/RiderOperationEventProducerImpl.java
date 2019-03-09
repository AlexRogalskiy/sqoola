package com.wildbeeslabs.sensiblemetrics.sqoola.producer.impl.rider;

import com.wildbeeslabs.sensiblemetrics.sqoola.producer.EventProducer;
import com.wildbeeslabs.sensiblemetrics.sqoola.producer.event.RiderOperationEvent;
import com.wildbeeslabs.sensiblemetrics.sqoola.producer.impl.OperationEventProducerImpl;
import com.wildbeeslabs.sensiblemetrics.sqoola.producer.rider.RiderOperationEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(RiderOperationEventProducer.SERVICE_ID)
public class RiderOperationEventProducerImpl extends OperationEventProducerImpl<RiderOperationEvent> implements RiderOperationEventProducer {

    /**
     * Default rider event producer {@link EventProducer}
     */
    private final EventProducer<RiderOperationEvent> riderEventProducer;

    @Autowired
    public RiderOperationEventProducerImpl(final EventProducer<RiderOperationEvent> riderEventProducer) {
        this.riderEventProducer = riderEventProducer;
    }

    @Override
    protected EventProducer<RiderOperationEvent> getEventProducer() {
        return riderEventProducer;
    }
}
