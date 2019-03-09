package com.wildbeeslabs.sensiblemetrics.sqoola.producer.impl.mail;

import com.dinamexoft.carol.triggers.messages.mail.MailOperationPeriodEvent;
import com.dinamexoft.carol.triggers.producers.EventProducer;
import com.dinamexoft.carol.triggers.producers.impl.OperationEventProducerImpl;
import com.dinamexoft.carol.triggers.producers.mail.MailOperationPeriodEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(MailOperationPeriodEventProducer.SERVICE_ID)
public class MailOperationPeriodEventProducerImpl extends OperationEventProducerImpl<MailOperationPeriodEvent> implements MailOperationPeriodEventProducer {

    /**
     * Default mail period event producer {@link EventProducer}
     */
    private final EventProducer<MailOperationPeriodEvent> mailPeriodEventProducer;

    @Autowired
    public MailOperationPeriodEventProducerImpl(final EventProducer<MailOperationPeriodEvent> mailPeriodEventProducer) {
        this.mailPeriodEventProducer = mailPeriodEventProducer;
    }

    @Override
    protected EventProducer<MailOperationPeriodEvent> getEventProducer() {
        return mailPeriodEventProducer;
    }
}
