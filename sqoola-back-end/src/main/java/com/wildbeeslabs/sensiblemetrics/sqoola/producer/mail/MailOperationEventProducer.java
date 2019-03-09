package com.wildbeeslabs.sensiblemetrics.sqoola.producer.mail;

import com.dinamexoft.carol.triggers.messages.mail.MailOperationEvent;
import com.dinamexoft.carol.triggers.producers.OperationEventProducer;

public interface MailOperationEventProducer extends OperationEventProducer<MailOperationEvent> {

    // Default service ID
    String SERVICE_ID = "mailOperationEventProducer";
}
