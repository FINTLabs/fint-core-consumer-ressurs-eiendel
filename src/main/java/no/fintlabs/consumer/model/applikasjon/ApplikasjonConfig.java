package no.fintlabs.consumer.model.applikasjon;

import no.fint.model.resource.ressurs.eiendel.ApplikasjonResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class ApplikasjonConfig extends ConsumerConfig<ApplikasjonResource> {

    public ApplikasjonConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "applikasjon";
    }
}
