package com.wildbeeslabs.sensiblemetrics.sqoola.producer.subscription;

import com.wildbeeslabs.sensiblemetrics.sqoola.producer.OperationEventProducer;
import com.wildbeeslabs.sensiblemetrics.sqoola.producer.event.SubscriptionOperationEvent;

public interface SubscriptionOperationEventProducer extends OperationEventProducer<SubscriptionOperationEvent> {

    // Default service ID
    String SERVICE_ID = "subscriptionOperationEventProducer";
}
