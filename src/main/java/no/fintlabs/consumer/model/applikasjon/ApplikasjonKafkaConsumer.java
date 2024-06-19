package no.fintlabs.consumer.model.applikasjon;

import no.fint.model.resource.ressurs.eiendel.ApplikasjonResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class ApplikasjonKafkaConsumer extends EntityKafkaConsumer<ApplikasjonResource> {

    public ApplikasjonKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            ApplikasjonConfig applikasjonConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, applikasjonConfig);
    }
}
