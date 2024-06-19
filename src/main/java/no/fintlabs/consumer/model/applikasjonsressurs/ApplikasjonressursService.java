package no.fintlabs.consumer.model.applikasjonsressurs;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.ressurs.eiendel.ApplikasjonResource;
import no.fint.model.resource.ressurs.eiendel.ApplikasjonsressursResource;
import no.fintlabs.cache.Cache;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ApplikasjonressursService extends CacheService<ApplikasjonsressursResource> {

    private final EntityKafkaConsumer<ApplikasjonsressursResource> entityKafkaConsumer;

    private final ApplikasjonressursLinker linker;

    public ApplikasjonressursService(
            ApplikasjonressursConfig consumerConfig,
            CacheManager cacheManager,
            ApplikasjonressursKafkaConsumer entityKafkaConsumer,
            ApplikasjonressursLinker linker) {
        super(consumerConfig, cacheManager, entityKafkaConsumer);
        this.entityKafkaConsumer = entityKafkaConsumer;
        this.linker = linker;
    }

    @Override
    protected Cache<ApplikasjonsressursResource> initializeCache(CacheManager cacheManager, ConsumerConfig<ApplikasjonsressursResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retention = entityKafkaConsumer.registerListener(ApplikasjonsressursResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retention);
    }

    private void addResourceToCache(ConsumerRecord<String, ApplikasjonsressursResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        if (consumerRecord.value() == null) {
            getCache().remove(consumerRecord.key());
        } else {
            ApplikasjonsressursResource resource = consumerRecord.value();
            linker.mapLinks(resource);
            getCache().put(consumerRecord.key(), resource, linker.hashCodes(resource));
        }
    }

    @Override
    public Optional<ApplikasjonsressursResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(ApplikasjonsressursResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
