package no.fintlabs.consumer.model.applikasjonsressurs;

import no.fint.model.resource.ressurs.eiendel.ApplikasjonsressursResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class ApplikasjonressursKafkaConsumer extends EntityKafkaConsumer<ApplikasjonsressursResource> {

    public ApplikasjonressursKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            ApplikasjonressursConfig applikasjonressursConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, applikasjonressursConfig);
    }
}
