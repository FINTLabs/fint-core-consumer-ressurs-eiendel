package no.fintlabs.consumer.config;

import com.google.common.collect.ImmutableMap;
import no.fint.model.ressurs.eiendel.Applikasjon;
import no.fint.model.ressurs.eiendel.Applikasjonsressurs;
import no.fint.model.ressurs.eiendel.Applikasjonsressurstilgjengelighet;

import java.util.Map;

public class LinkMapper {

    public static Map<String, String> linkMapper() {
        return ImmutableMap.<String, String>builder()
                .put(Applikasjon.class.getName(), "/ressurs/eiendel/applikasjon")
                .put(Applikasjonsressurs.class.getName(), "/ressurs/eiendel/applikasjonsressurs")
                .put(Applikasjonsressurstilgjengelighet.class.getName(), "/ressurs/eiendel/applikasjonsressurstilgjengelighet")
                .build();
    }

}