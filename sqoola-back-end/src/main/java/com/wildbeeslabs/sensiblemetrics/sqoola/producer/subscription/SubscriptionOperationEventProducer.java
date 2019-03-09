package com.wildbeeslabs.sensiblemetrics.sqoola.producer.subscription;

import com.dinamexoft.carol.triggers.messages.subscription.SubscriptionOperationEvent;
import com.dinamexoft.carol.triggers.producers.OperationEventProducer;

public interface SubscriptionOperationEventProducer extends OperationEventProducer<SubscriptionOperationEvent> {

    // Default service ID
    String SERVICE_ID = "subscriptionOperationEventProducer";
}
