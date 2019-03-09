package com.wildbeeslabs.sensiblemetrics.sqoola.producer.mail;

import com.wildbeeslabs.sensiblemetrics.sqoola.producer.OperationEventProducer;
import com.wildbeeslabs.sensiblemetrics.sqoola.producer.event.MailOperationEvent;

public interface MailOperationEventProducer extends OperationEventProducer<MailOperationEvent> {

    // Default service ID
    String SERVICE_ID = "mailOperationEventProducer";
}
