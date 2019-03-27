package com.sensiblemetrics.api.sqoola.common.producer.impl.subscription;

import com.sensiblemetrics.api.sqoola.common.producer.event.SubscriptionOperationEvent;
import com.sensiblemetrics.api.sqoola.common.producer.impl.OperationEventProducerImpl;
import com.sensiblemetrics.api.sqoola.common.producer.iface.EventProducer;
import com.sensiblemetrics.api.sqoola.common.producer.subscription.SubscriptionOperationEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(SubscriptionOperationEventProducer.SERVICE_ID)
public class SubscriptionOperationEventProducerImpl extends OperationEventProducerImpl<SubscriptionOperationEvent> implements SubscriptionOperationEventProducer {

    /**
     * Default subscription event producer {@link EventProducer}
     */
    private final EventProducer<SubscriptionOperationEvent> subscriptionEventProducer;

    @Autowired
    public SubscriptionOperationEventProducerImpl(final EventProducer<SubscriptionOperationEvent> subscriptionEventProducer) {
        this.subscriptionEventProducer = subscriptionEventProducer;
    }

    @Override
    protected EventProducer<SubscriptionOperationEvent> getEventProducer() {
        return subscriptionEventProducer;
    }
}
