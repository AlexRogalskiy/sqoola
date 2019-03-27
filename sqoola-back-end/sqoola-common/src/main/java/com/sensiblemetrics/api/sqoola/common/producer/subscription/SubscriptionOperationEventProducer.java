package com.sensiblemetrics.api.sqoola.common.producer.subscription;

import com.sensiblemetrics.api.sqoola.common.producer.event.SubscriptionOperationEvent;
import com.sensiblemetrics.api.sqoola.common.producer.iface.OperationEventProducer;

public interface SubscriptionOperationEventProducer extends OperationEventProducer<SubscriptionOperationEvent> {

    // Default service ID
    String SERVICE_ID = "subscriptionOperationEventProducer";
}
