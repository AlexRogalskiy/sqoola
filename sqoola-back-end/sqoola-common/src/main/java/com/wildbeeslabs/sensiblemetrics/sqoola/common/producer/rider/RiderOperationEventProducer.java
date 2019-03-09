package com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.rider;

import com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.OperationEventProducer;
import com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.event.RiderOperationEvent;

public interface RiderOperationEventProducer extends OperationEventProducer<RiderOperationEvent> {

    // Default service ID
    String SERVICE_ID = "riderOperationEventProducer";
}
