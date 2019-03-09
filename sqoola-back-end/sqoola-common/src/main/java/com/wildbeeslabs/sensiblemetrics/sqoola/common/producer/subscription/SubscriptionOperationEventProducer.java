package com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.subscription;

import com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.OperationEventProducer;
import com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.event.SubscriptionOperationEvent;

public interface SubscriptionOperationEventProducer extends OperationEventProducer<SubscriptionOperationEvent> {

    // Default service ID
    String SERVICE_ID = "subscriptionOperationEventProducer";
}
