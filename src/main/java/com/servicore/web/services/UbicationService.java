package com.servicore.web.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.models.Country;
import com.servicore.web.models.Departament;
import com.servicore.web.models.Municipality;
import com.servicore.web.repositories.CountryRepository;
import com.servicore.web.repositories.DepartamentRepository;
import com.servicore.web.repositories.MunicipalityRepository;

@Service
public class UbicationService {

    private final CountryRepository countryRepository;
    private final DepartamentRepository departamentRepository;
    private final MunicipalityRepository municipalityRepository;

    public UbicationService(
        CountryRepository countryRepository,
        DepartamentRepository departamentRepository,
        MunicipalityRepository municipalityRepository
    ) {
        this.countryRepository = countryRepository;
        this.departamentRepository = departamentRepository;
        this.municipalityRepository = municipalityRepository;
    }

    @Transactional(readOnly = true)
    public List<Country> getCountries() {
        return countryRepository.findAll(Sort.by("name"));
    }

    @Transactional(readOnly = true)
    public List<Departament> getDepartaments(Long countryId) {
        return departamentRepository.findByCountryIdOrderByNameAsc(countryId);
    }

    @Transactional(readOnly = true)
    public List<Municipality> getMunicipalities(Long departamentId) {
        return municipalityRepository.findByDepartamentIdOrderByNameAsc(departamentId);
    }

}