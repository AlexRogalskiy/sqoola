package com.wildbeeslabs.sensiblemetrics.sqoola.producer.impl.mail;

import com.dinamexoft.carol.triggers.messages.mail.MailOperationEvent;
import com.dinamexoft.carol.triggers.producers.EventProducer;
import com.dinamexoft.carol.triggers.producers.impl.OperationEventProducerImpl;
import com.dinamexoft.carol.triggers.producers.mail.MailOperationEventProducer;
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
