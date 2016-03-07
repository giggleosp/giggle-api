package com.giggle.repositories.country;

import com.giggle.models.City;
import com.giggle.models.Country;
import com.giggle.models.County;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by enda on 19/12/15.
 */
@Singleton
public interface CountryRepository {
    List<Country> allCountries();
    Country getCountryById(long id);
    List<County> getCountiesForCountry(long id);
    County getCountyById(long id);
    List<City> getCitiesForCounty(long id);
}