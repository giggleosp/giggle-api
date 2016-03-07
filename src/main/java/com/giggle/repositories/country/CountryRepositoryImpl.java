package com.giggle.repositories.country;

import com.giggle.JinqSource;
import com.giggle.models.City;
import com.giggle.models.Country;
import com.giggle.models.County;
import com.giggle.repositories.country.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by enda on 20/12/15.
 */
@Repository
public class CountryRepositoryImpl implements CountryRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JinqSource source;

    @Override
    public List<Country> allCountries() {
        return source.countries(em)
                .toList();
    }

    @Override
    public Country getCountryById(long id) {
        List<Country> countries = source.countries(em)
                .where(c -> c.getId() == id)
                .toList();
        return countries.isEmpty() ? null : countries.get(0);
    }

    @Override
    public List<County> getCountiesForCountry(long id) {
        List<County> counties = source.counties(em)
                .where(c -> c.getCountry().getId() == id)
                .toList();
        return counties.isEmpty() ? null : counties;
    }

    @Override
    public County getCountyById(long id) {
        List<County> counties = source.counties(em)
                .where(c -> c.getId() == id)
                .toList();
        return counties.isEmpty() ? null : counties.get(0);
    }

    @Override
    public List<City> getCitiesForCounty(long id) {
        List<City> cities = source.cities(em)
                .where(c -> c.getCounty().getId() == id)
                .toList();
        return cities.isEmpty() ? null : cities;
    }

}
