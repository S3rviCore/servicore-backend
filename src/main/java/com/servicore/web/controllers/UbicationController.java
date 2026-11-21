package com.servicore.web.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.models.Country;
import com.servicore.web.models.Departament;
import com.servicore.web.models.Municipality;
import com.servicore.web.services.UbicationService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UbicationController {

    private final UbicationService ubicationService;

    public UbicationController(UbicationService ubicationService) {
        this.ubicationService = ubicationService;
    }

    @GetMapping("/countries")
    public List<Country> getCountries() {
        return ubicationService.getCountries();
    }

    @GetMapping("/countries/{countryId}/departaments")
    public List<Departament> getDepartamentsByCountryId(@PathVariable Long countryId) {
        return ubicationService.getDepartaments(countryId);
    }

    @GetMapping("/departaments/{departamentId}/municipalities")
    public List<Municipality> getMunicipalitiesByDepartamentId(@PathVariable Long departamentId) {
        return ubicationService.getMunicipalities(departamentId);
    }

}