package no.fintlabs.consumer.model.applikasjonsressurstilgjengelighet;

import no.fint.model.resource.ressurs.eiendel.ApplikasjonsressurstilgjengelighetResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class ApplikasjonressurstilgjengelighetKafkaConsumer extends EntityKafkaConsumer<ApplikasjonsressurstilgjengelighetResource> {

    public ApplikasjonressurstilgjengelighetKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            ApplikasjonressurstilgjengelighetConfig applikasjonressurstilgjengelighetConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, applikasjonressurstilgjengelighetConfig);
    }
}
