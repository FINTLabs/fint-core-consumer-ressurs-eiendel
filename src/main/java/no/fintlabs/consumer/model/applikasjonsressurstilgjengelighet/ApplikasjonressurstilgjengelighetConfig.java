package no.fintlabs.consumer.model.applikasjonsressurstilgjengelighet;

import no.fint.model.resource.ressurs.eiendel.ApplikasjonsressurstilgjengelighetResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class ApplikasjonressurstilgjengelighetConfig extends ConsumerConfig<ApplikasjonsressurstilgjengelighetResource> {

    public ApplikasjonressurstilgjengelighetConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "applikasjonressurstilgjengelighet";
    }
}
