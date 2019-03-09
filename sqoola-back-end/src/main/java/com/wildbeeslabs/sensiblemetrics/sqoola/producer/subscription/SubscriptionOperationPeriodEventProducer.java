package com.wildbeeslabs.sensiblemetrics.sqoola.producer.subscription;

import com.dinamexoft.carol.triggers.messages.subscription.SubscriptionOperationPeriodEvent;
import com.dinamexoft.carol.triggers.producers.OperationEventProducer;

public interface SubscriptionOperationPeriodEventProducer extends OperationEventProducer<SubscriptionOperationPeriodEvent> {

    // Default service ID
    String SERVICE_ID = "subscriptionOperationPeriodEventProducer";
}
