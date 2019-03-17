package com.sensiblemetrics.api.sqoola.common.producer.mail;

import com.sensiblemetrics.api.sqoola.common.producer.OperationEventProducer;
import com.sensiblemetrics.api.sqoola.common.producer.event.MailOperationEvent;

public interface MailOperationEventProducer extends OperationEventProducer<MailOperationEvent> {

    // Default service ID
    String SERVICE_ID = "mailOperationEventProducer";
}
