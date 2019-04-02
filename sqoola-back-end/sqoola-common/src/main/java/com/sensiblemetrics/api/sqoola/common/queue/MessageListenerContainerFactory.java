package com.sensiblemetrics.api.sqoola.common.queue;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListenerContainerFactory {

    @Autowired
    private ConnectionFactory connectionFactory;

    public MessageListenerContainer createMessageListenerContainer(final String queueName) {
        final SimpleMessageListenerContainer mlc = new SimpleMessageListenerContainer(this.connectionFactory);
        mlc.addQueueNames(queueName);
        mlc.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return mlc;
    }
}
