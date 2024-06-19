package no.fintlabs.consumer.model.applikasjonsressurstilgjengelighet;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.ressurs.eiendel.ApplikasjonsressurstilgjengelighetResource;
import no.fint.relations.FintRelationsMediaType;
import no.fintlabs.consumer.config.RestEndpoints;
import no.fintlabs.core.consumer.shared.resource.ConsumerRestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(name = "Applikasjonressurstilgjengelighet", value = RestEndpoints.APPLIKASJONSRESSURSTILGJENGELIGHET, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class ApplikasjonressurstilgjengelighetController extends ConsumerRestController<ApplikasjonsressurstilgjengelighetResource> {

    public ApplikasjonressurstilgjengelighetController(ApplikasjonressurstilgjengelighetService service, ApplikasjonressurstilgjengelighetLinker linker, FintFilterService oDataFilterService) {
        super(service, linker, oDataFilterService);
    }

    @PostConstruct
    private void registerIdentificators() {
        super.registerIdenficatorHandler("systemId", ApplikasjonsressurstilgjengelighetResource::getSystemId);
    }
}
