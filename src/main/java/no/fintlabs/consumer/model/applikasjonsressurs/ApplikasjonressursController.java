package no.fintlabs.consumer.model.applikasjonsressurs;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.ressurs.eiendel.ApplikasjonsressursResource;
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
@RequestMapping(name = "Applikasjonressurs", value = RestEndpoints.APPLIKASJONSRESSURS, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class ApplikasjonressursController extends ConsumerRestController<ApplikasjonsressursResource> {

    public ApplikasjonressursController(ApplikasjonressursService service, ApplikasjonressursLinker linker, FintFilterService oDataFilterService) {
        super(service, linker, oDataFilterService);
    }

    @PostConstruct
    private void registerIdentificators() {
        super.registerIdenficatorHandler("systemid", ApplikasjonsressursResource::getSystemId);
    }
}
