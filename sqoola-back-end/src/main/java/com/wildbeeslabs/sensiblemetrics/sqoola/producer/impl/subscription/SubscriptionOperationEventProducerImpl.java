package com.wildbeeslabs.sensiblemetrics.sqoola.producer.impl.subscription;

import com.wildbeeslabs.sensiblemetrics.sqoola.producer.EventProducer;
import com.wildbeeslabs.sensiblemetrics.sqoola.producer.event.SubscriptionOperationEvent;
import com.wildbeeslabs.sensiblemetrics.sqoola.producer.impl.OperationEventProducerImpl;
import com.wildbeeslabs.sensiblemetrics.sqoola.producer.subscription.SubscriptionOperationEventProducer;
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
