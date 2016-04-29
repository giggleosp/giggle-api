package com.giggle.controllers;

import com.giggle.exceptions.BadRequestException;
import com.giggle.exceptions.NotFoundException;
import com.giggle.models.City;
import com.giggle.models.Country;
import com.giggle.models.County;
import com.giggle.repositories.country.CountryRepository;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by enda on 19/12/15.
 */
@RestController
@RequestMapping("/")
public class CountryController {

    private final CountryRepository repo;

    @Inject
    public CountryController(CountryRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(method = RequestMethod.GET, value = "countries")
    public @ResponseBody List<Country> allCountries() {
        List<Country> countries = repo.allCountries();

        if (countries == null)
            throw new NotFoundException();
        else {
            return countries;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "countries/{id}/counties")
    public @ResponseBody List<County> getCountiesOfCountry(@PathVariable long id) {

        if (getCountryById(id) == null)
            throw new NotFoundException();

        List<County> counties = repo.getCountiesForCountry(id);

        if (counties == null)
            throw new NotFoundException();
        else {
            return counties;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "countries/{id}")
    public @ResponseBody Country getCountryById(@PathVariable long id) {
        if (id < 1) throw new BadRequestException();

        Country country = repo.getCountryById(id);

        if (country == null)
            throw new NotFoundException();
        else {
            return country;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "countries/counties/{id}")
    public @ResponseBody County getCountyById(@PathVariable long id) {

        County county = repo.getCountyById(id);

        if (county == null) {
            throw new NotFoundException();
        } else {
            return county;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "countries/counties/{id}/cities")
    public @ResponseBody List<City> getCitiesForCountry(@PathVariable long id) {

        List<City> cities = repo.getCitiesForCounty(id);

        if (cities == null) {
            throw new NotFoundException();
        } else {
            return cities;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "cities")
    public @ResponseBody List<City> getCities() {
        List<City> cities = repo.getCities();

        if (cities.isEmpty()) {
            throw new NotFoundException();
        }
        return cities;
    }

}
