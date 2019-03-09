package com.wildbeeslabs.sensiblemetrics.sqoola.producer.impl.rider;

import com.dinamexoft.carol.triggers.messages.rider.period.RiderOperationPeriodEvent;
import com.dinamexoft.carol.triggers.producers.EventProducer;
import com.dinamexoft.carol.triggers.producers.impl.OperationEventProducerImpl;
import com.dinamexoft.carol.triggers.producers.rider.RiderOperationPeriodEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(RiderOperationPeriodEventProducer.SERVICE_ID)
public class RiderOperationPeriodEventProducerImpl extends OperationEventProducerImpl<RiderOperationPeriodEvent> implements RiderOperationPeriodEventProducer {

    /**
     * Default rider period event producer {@link EventProducer}
     */
    private final EventProducer<RiderOperationPeriodEvent> riderPeriodEventProducer;

    @Autowired
    public RiderOperationPeriodEventProducerImpl(final EventProducer<RiderOperationPeriodEvent> riderPeriodEventProducer) {
        this.riderPeriodEventProducer = riderPeriodEventProducer;
    }

    @Override
    protected EventProducer<RiderOperationPeriodEvent> getEventProducer() {
        return riderPeriodEventProducer;
    }
}
