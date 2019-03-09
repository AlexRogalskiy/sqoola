package com.wildbeeslabs.sensiblemetrics.sqoola.producer.impl.subscription;

import com.dinamexoft.carol.triggers.messages.subscription.SubscriptionOperationPeriodEvent;
import com.dinamexoft.carol.triggers.producers.EventProducer;
import com.dinamexoft.carol.triggers.producers.impl.OperationEventProducerImpl;
import com.dinamexoft.carol.triggers.producers.subscription.SubscriptionOperationPeriodEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(SubscriptionOperationPeriodEventProducer.SERVICE_ID)
public class SubscriptionOperationPeriodEventProducerImpl extends OperationEventProducerImpl<SubscriptionOperationPeriodEvent> implements SubscriptionOperationPeriodEventProducer {

    /**
     * Default subscription period event producer {@link EventProducer}
     */
    private final EventProducer<SubscriptionOperationPeriodEvent> subscriptionPeriodEventProducer;

    @Autowired
    public SubscriptionOperationPeriodEventProducerImpl(final EventProducer<SubscriptionOperationPeriodEvent> subscriptionPeriodEventProducer) {
        this.subscriptionPeriodEventProducer = subscriptionPeriodEventProducer;
    }

    @Override
    protected EventProducer<SubscriptionOperationPeriodEvent> getEventProducer() {
        return subscriptionPeriodEventProducer;
    }
}
