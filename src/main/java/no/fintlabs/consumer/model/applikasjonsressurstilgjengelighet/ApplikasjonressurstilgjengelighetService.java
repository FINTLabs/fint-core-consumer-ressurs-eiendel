package no.fintlabs.consumer.model.applikasjonsressurstilgjengelighet;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.ressurs.eiendel.ApplikasjonResource;
import no.fint.model.resource.ressurs.eiendel.ApplikasjonsressurstilgjengelighetResource;
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
public class ApplikasjonressurstilgjengelighetService extends CacheService<ApplikasjonsressurstilgjengelighetResource> {

    private final EntityKafkaConsumer<ApplikasjonsressurstilgjengelighetResource> entityKafkaConsumer;

    private final ApplikasjonressurstilgjengelighetLinker linker;

    public ApplikasjonressurstilgjengelighetService(
            ApplikasjonressurstilgjengelighetConfig consumerConfig,
            CacheManager cacheManager,
            ApplikasjonressurstilgjengelighetKafkaConsumer entityKafkaConsumer,
            ApplikasjonressurstilgjengelighetLinker linker) {
        super(consumerConfig, cacheManager, entityKafkaConsumer);
        this.entityKafkaConsumer = entityKafkaConsumer;
        this.linker = linker;
    }

    @Override
    protected Cache<ApplikasjonsressurstilgjengelighetResource> initializeCache(CacheManager cacheManager, ConsumerConfig<ApplikasjonsressurstilgjengelighetResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retention = entityKafkaConsumer.registerListener(ApplikasjonsressurstilgjengelighetResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retention);
    }

    private void addResourceToCache(ConsumerRecord<String, ApplikasjonsressurstilgjengelighetResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        if (consumerRecord.value() == null) {
            getCache().remove(consumerRecord.key());
        } else {
            ApplikasjonsressurstilgjengelighetResource applikasjonResource = consumerRecord.value();
            linker.mapLinks(applikasjonResource);
            getCache().put(consumerRecord.key(), applikasjonResource, linker.hashCodes(applikasjonResource));
        }
    }

    @Override
    public Optional<ApplikasjonsressurstilgjengelighetResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(ApplikasjonsressurstilgjengelighetResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
