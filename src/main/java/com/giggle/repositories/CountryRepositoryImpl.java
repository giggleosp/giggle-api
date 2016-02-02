package com.giggle.repositories;

import com.giggle.JinqSource;
import com.giggle.models.Country;
import com.giggle.models.County;
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
    public List<County> allCountiesOfCountry(long id) {
        List<County> counties = source.counties(em)
                .where(c -> c.getCountry().getId() == id)
                .toList();
        return counties.isEmpty() ? null : counties;
    }

//    @Override
//    public Country getCountryByName(String country) {
//        List<Country> countries = source.countries(em)
//                .where(c -> c.getNiceName().toLowerCase().equals(country.toLowerCase()))
//                .toList();
//        return countries.isEmpty() ? null : countries.get(0);
//    }
}
