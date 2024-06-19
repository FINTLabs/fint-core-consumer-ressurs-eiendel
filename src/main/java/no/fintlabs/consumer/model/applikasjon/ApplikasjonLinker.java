package no.fintlabs.consumer.model.applikasjon;

import no.fint.model.resource.ressurs.eiendel.ApplikasjonResource;
import no.fint.model.resource.ressurs.eiendel.ApplikasjonResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class ApplikasjonLinker extends FintLinker<ApplikasjonResource> {

    public ApplikasjonLinker() {
        super(ApplikasjonResource.class);
    }

    public void mapLinks(ApplikasjonResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public ApplikasjonResources toResources(Collection<ApplikasjonResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public ApplikasjonResources toResources(Stream<ApplikasjonResource> stream, int offset, int size, int totalItems) {
        ApplikasjonResources resources = new ApplikasjonResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(ApplikasjonResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(ApplikasjonResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(ApplikasjonResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}