package com.sensiblemetrics.api.sqoola.common.producer.rider;

import com.sensiblemetrics.api.sqoola.common.producer.event.RiderOperationEvent;
import com.sensiblemetrics.api.sqoola.common.producer.OperationEventProducer;

public interface RiderOperationEventProducer extends OperationEventProducer<RiderOperationEvent> {

    // Default service ID
    String SERVICE_ID = "riderOperationEventProducer";
}
