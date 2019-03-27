//package com.wildbeeslabs.sensiblemetrics.sqoola.configuration;
//
//import com.dinamexoft.carol.triggers.services.endpoint.EndpointService;
//import com.dinamexoft.carol.triggers.services.impl.endpoint.EndpointServiceImpl;
//import DeferredEventProducer;
//import MailOperationEvent;
//import RiderOperationEvent;
//import SubscriptionOperationEvent;
//import DeferredEventProducerImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import static com.dinamexoft.carol.triggers.states.mail.MailOperationStatesGuid.MAIL_GUID;
//import static com.dinamexoft.carol.triggers.states.rider.RiderOperationStatesGuid.RIDER_GUID;
//import static com.dinamexoft.carol.triggers.states.subscription.SubscriptionOperationStatesGuid.SUBSCRIPTION_GUID;
//
//@Configuration
//public class ProducerConfig {
//
//    @Bean
//    public EndpointService endpointService() {
//        return new EndpointServiceImpl();
//    }
//
//    @Bean
//    public DeferredEventProducer<RiderOperationEvent, Object> riderEventProducer(final EndpointService endpointService) {
//        return new DeferredEventProducerImpl<>(endpointService.getEndpointNameByGuid(RIDER_GUID));
//    }
//
//    @Bean
//    public DeferredEventProducer<MailOperationEvent, Object> mailEventProducer(final EndpointService endpointService) {
//        return new DeferredEventProducerImpl<>(endpointService.getEndpointNameByGuid(MAIL_GUID));
//    }
//
//    @Bean
//    public DeferredEventProducer<SubscriptionOperationEvent, Object> subscriptionEventProducer(final EndpointService endpointService) {
//        return new DeferredEventProducerImpl<>(endpointService.getEndpointNameByGuid(SUBSCRIPTION_GUID));
//    }
//}
