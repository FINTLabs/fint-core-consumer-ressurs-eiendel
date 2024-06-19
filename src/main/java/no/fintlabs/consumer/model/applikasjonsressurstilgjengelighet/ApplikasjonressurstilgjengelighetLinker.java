package no.fintlabs.consumer.model.applikasjonsressurstilgjengelighet;

import no.fint.model.resource.ressurs.eiendel.ApplikasjonsressurstilgjengelighetResource;
import no.fint.model.resource.ressurs.eiendel.ApplikasjonsressurstilgjengelighetResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class ApplikasjonressurstilgjengelighetLinker extends FintLinker<ApplikasjonsressurstilgjengelighetResource> {

    public ApplikasjonressurstilgjengelighetLinker() {
        super(ApplikasjonsressurstilgjengelighetResource.class);
    }

    public void mapLinks(ApplikasjonsressurstilgjengelighetResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public ApplikasjonsressurstilgjengelighetResources toResources(Collection<ApplikasjonsressurstilgjengelighetResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public ApplikasjonsressurstilgjengelighetResources toResources(Stream<ApplikasjonsressurstilgjengelighetResource> stream, int offset, int size, int totalItems) {
        ApplikasjonsressurstilgjengelighetResources resources = new ApplikasjonsressurstilgjengelighetResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(ApplikasjonsressurstilgjengelighetResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(ApplikasjonsressurstilgjengelighetResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(ApplikasjonsressurstilgjengelighetResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}