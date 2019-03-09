package com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.mail;

import com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.OperationEventProducer;
import com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.event.MailOperationEvent;

public interface MailOperationEventProducer extends OperationEventProducer<MailOperationEvent> {

    // Default service ID
    String SERVICE_ID = "mailOperationEventProducer";
}
