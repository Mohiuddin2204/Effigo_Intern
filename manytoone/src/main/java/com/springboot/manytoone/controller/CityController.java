package com.springboot.manytoone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.manytoone.model.City;
import com.springboot.manytoone.model.Country;
import com.springboot.manytoone.service.CityService;

import java.util.*;




@RestController
public class CityController {
   @Autowired
    private CityService s;
    @GetMapping("/countries/cities")
    public ArrayList<City> getCitiescountry(){
           return s.getCities();
    }
     @PostMapping("/countries/cities")
     public City postCity(@RequestBody City city){
        return s.addCity(city);
     }
     @GetMapping("/countries/cities/{cityId}")
     public City getCityById(@PathVariable("cityId") int id){
        return s.getCityById(id);
     }
     @PutMapping("/countries/cities/{cityId}")
     public City updateCity(@PathVariable("cityId") int id,@RequestBody City city){
        return s.updateCityById(id, city);
     }
     @GetMapping("/cities/{cityId}/country")
     public Country getCountryByCityId(@PathVariable("cityId") int id) {
        return s.getCountryByCityId(id);
     }
     @DeleteMapping("/countries/cities/{cityId}")
     public void deleteCity(@PathVariable("cityId") int id)
     {
        s.deleteCity(id);
     }
}