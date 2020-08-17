package cn.lch.java_spring_boot.modules.controller;

import cn.lch.java_spring_boot.aspect.ServiceAnnotation;
import cn.lch.java_spring_boot.modules.Service.CountryService;
import cn.lch.java_spring_boot.modules.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {
    @Autowired
    private CountryService countryService;

    /**
     *  http://localhost/country/522   GET
     */
    @GetMapping("/country/{countryId}")
    public Country getCountryByCountryId(@PathVariable int countryId){
        return countryService.getCountryByCountryId(countryId);
    }

    /**
     *  http://localhost/country?countryName=China   GET
     */
    @GetMapping("/country")
    @ServiceAnnotation(value = "ccc")
    public Country getCountryByCountryName(@RequestParam String countryName) {
        return countryService.getCountryByCountryName(countryName);
    }

    /**
     * http://localhost/redis/country/522 ---- get
     */
    @GetMapping("/redis/country/{countryId}")
    public Country mograteCountryByRedis(@PathVariable int countryId) {
        return countryService.migrateCountryByRedis(countryId);
    }

}