package no.fintlabs.consumer.model.applikasjonsressurs;

import no.fint.model.resource.ressurs.eiendel.ApplikasjonsressursResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class ApplikasjonressursConfig extends ConsumerConfig<ApplikasjonsressursResource> {

    public ApplikasjonressursConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "applikasjonressurs";
    }
}
