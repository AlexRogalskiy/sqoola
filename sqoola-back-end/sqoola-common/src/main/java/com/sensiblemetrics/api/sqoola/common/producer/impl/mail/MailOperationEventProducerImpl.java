package com.sensiblemetrics.api.sqoola.common.producer.impl.mail;

import com.sensiblemetrics.api.sqoola.common.producer.event.MailOperationEvent;
import com.sensiblemetrics.api.sqoola.common.producer.mail.MailOperationEventProducer;
import com.sensiblemetrics.api.sqoola.common.producer.impl.OperationEventProducerImpl;
import com.sensiblemetrics.api.sqoola.common.producer.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(MailOperationEventProducer.SERVICE_ID)
public class MailOperationEventProducerImpl extends OperationEventProducerImpl<MailOperationEvent> implements MailOperationEventProducer {

    /**
     * Default mail event producer {@link EventProducer}
     */
    private final EventProducer<MailOperationEvent> mailEventProducer;

    @Autowired
    public MailOperationEventProducerImpl(final EventProducer<MailOperationEvent> mailEventProducer) {
        this.mailEventProducer = mailEventProducer;
    }

    @Override
    protected EventProducer<MailOperationEvent> getEventProducer() {
        return mailEventProducer;
    }
}
