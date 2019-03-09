package com.wildbeeslabs.sensiblemetrics.sqoola.producer.rider;

import com.dinamexoft.carol.triggers.messages.rider.RiderOperationEvent;
import com.dinamexoft.carol.triggers.producers.OperationEventProducer;

public interface RiderOperationEventProducer extends OperationEventProducer<RiderOperationEvent> {

    // Default service ID
    String SERVICE_ID = "riderOperationEventProducer";
}
