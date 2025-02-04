package com.springboot.manytoone.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.manytoone.model.City;
import com.springboot.manytoone.model.Country;
import com.springboot.manytoone.repository.CityJpaRepository;
import com.springboot.manytoone.repository.CountryJpaRepository;
import com.springboot.manytoone.repository.CountryRepository;


@Service
public class CountryService  implements CountryRepository{
    @Autowired
    private CountryJpaRepository countryJpaRepository;
    @Autowired
    private CityJpaRepository cityJpaRepository;
    @Override
    public ArrayList<Country> getCountries(){
        List<Country> countries=countryJpaRepository.findAll();
        ArrayList<Country> countryList=new ArrayList<>(countries);
        return countryList;
    }

    @Override
    public Country addCountry(Country country){
           return countryJpaRepository.save(country);
    }
    @Override
    public Country getCountryById(int id){
        try{
            Country country=countryJpaRepository.findById(id).get();
            return country;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override 
    public Country updateCountry(Country country,int id){
        try{
            Country orginal=countryJpaRepository.findById(id).get();
            if(country.getCountryName()!=null){
                orginal.setCountryName(country.getCountryName());
            }
            if(country.getCurrency()!=null){
                orginal.setCurrency(country.getCurrency());
            }
            if(country.getLatitude()!=null){
                orginal.setLatitude(country.getLatitude());
            }
            if(country.getLongitude()!=null){
                orginal.setLongitude(country.getLongitude());
            }
             if(country.getPopulation()!=0){
                orginal.setPopulation(country.getPopulation());
             }
             countryJpaRepository.save(orginal);
             return orginal;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override 
    public void  deleteCountry(int id){
        try{
            Country country=countryJpaRepository.findById(id).get();
            ArrayList<City> cities=cityJpaRepository.findByCountry(country);
            for(City city:cities){
                city.setCountry(null);
            }
            cityJpaRepository.saveAll(cities);
            countryJpaRepository.deleteById(id);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}