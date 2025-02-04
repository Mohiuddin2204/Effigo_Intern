package com.springboot.manytoone.repository;

import java.util.ArrayList;

import com.springboot.manytoone.model.Country;


public interface CountryRepository {
  ArrayList<Country> getCountries();
  Country addCountry(Country country);
  Country getCountryById(int id);
  Country updateCountry(Country country, int id);
  void deleteCountry(int id);
} 