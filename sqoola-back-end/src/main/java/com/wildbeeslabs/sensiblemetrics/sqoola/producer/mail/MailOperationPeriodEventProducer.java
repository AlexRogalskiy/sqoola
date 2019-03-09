package com.wildbeeslabs.sensiblemetrics.sqoola.producer.mail;

import com.dinamexoft.carol.triggers.messages.mail.MailOperationPeriodEvent;
import com.dinamexoft.carol.triggers.producers.OperationEventProducer;

public interface MailOperationPeriodEventProducer extends OperationEventProducer<MailOperationPeriodEvent> {

    // Default service ID
    String SERVICE_ID = "mailOperationPeriodEventProducer";
}
