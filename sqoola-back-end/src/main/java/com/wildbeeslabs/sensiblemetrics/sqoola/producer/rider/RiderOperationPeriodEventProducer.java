package com.wildbeeslabs.sensiblemetrics.sqoola.producer.rider;

import com.dinamexoft.carol.triggers.messages.rider.period.RiderOperationPeriodEvent;
import com.dinamexoft.carol.triggers.producers.OperationEventProducer;

public interface RiderOperationPeriodEventProducer extends OperationEventProducer<RiderOperationPeriodEvent> {

    // Default service ID
    String SERVICE_ID = "riderOperationPeriodEventProducer";
}
