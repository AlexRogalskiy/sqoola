package com.sensiblemetrics.api.sqoola.common.producer.mail;

import com.sensiblemetrics.api.sqoola.common.producer.iface.OperationEventProducer;
import com.sensiblemetrics.api.sqoola.common.producer.event.MailOperationEvent;

public interface MailOperationEventProducer extends OperationEventProducer<MailOperationEvent> {

    // Default service ID
    String SERVICE_ID = "mailOperationEventProducer";
}
