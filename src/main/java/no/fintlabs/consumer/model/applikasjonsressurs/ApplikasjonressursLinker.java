package no.fintlabs.consumer.model.applikasjonsressurs;

import no.fint.model.resource.ressurs.eiendel.ApplikasjonsressursResource;
import no.fint.model.resource.ressurs.eiendel.ApplikasjonsressursResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class ApplikasjonressursLinker extends FintLinker<ApplikasjonsressursResource> {

    public ApplikasjonressursLinker() {
        super(ApplikasjonsressursResource.class);
    }

    public void mapLinks(ApplikasjonsressursResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public ApplikasjonsressursResources toResources(Collection<ApplikasjonsressursResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public ApplikasjonsressursResources toResources(Stream<ApplikasjonsressursResource> stream, int offset, int size, int totalItems) {
        ApplikasjonsressursResources resources = new ApplikasjonsressursResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(ApplikasjonsressursResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(ApplikasjonsressursResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(ApplikasjonsressursResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}