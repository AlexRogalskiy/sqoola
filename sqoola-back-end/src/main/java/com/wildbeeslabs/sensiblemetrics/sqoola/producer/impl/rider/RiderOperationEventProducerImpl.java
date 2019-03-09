package com.wildbeeslabs.sensiblemetrics.sqoola.producer.impl.rider;

import com.dinamexoft.carol.triggers.messages.rider.RiderOperationEvent;
import com.dinamexoft.carol.triggers.producers.EventProducer;
import com.dinamexoft.carol.triggers.producers.impl.OperationEventProducerImpl;
import com.dinamexoft.carol.triggers.producers.rider.RiderOperationEventProducer;
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
