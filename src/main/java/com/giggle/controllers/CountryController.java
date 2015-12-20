package com.giggle.controllers;

import com.giggle.exceptions.NotFoundException;
import com.giggle.models.Country;
import com.giggle.models.County;
import com.giggle.repositories.CountryRepository;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by enda on 19/12/15.
 */
@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryRepository repo;

    @Inject
    public CountryController(CountryRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public @ResponseBody List<Country> allCountries() {
        List<Country> countries = repo.allCountries();

        if (countries.isEmpty())
            throw new NotFoundException();
        else {
            return countries;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/counties")
    public @ResponseBody List<County> getCountiesOfCountry(@PathVariable long id) {
        List<County> counties = repo
                .allCountiesOfCountry(id);

        if (counties.isEmpty())
            throw new NotFoundException();
        else {
            return counties;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody Country getCountryById(@PathVariable long id) {

        Country country = repo
                .getCountryById(id);
        if (country == null)
            throw new NotFoundException();
        else {
            return country;
        }
    }
}