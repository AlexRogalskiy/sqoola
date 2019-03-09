package com.wildbeeslabs.sensiblemetrics.sqoola.producer.rider;

import com.wildbeeslabs.sensiblemetrics.sqoola.producer.OperationEventProducer;
import com.wildbeeslabs.sensiblemetrics.sqoola.producer.event.RiderOperationEvent;

public interface RiderOperationEventProducer extends OperationEventProducer<RiderOperationEvent> {

    // Default service ID
    String SERVICE_ID = "riderOperationEventProducer";
}
