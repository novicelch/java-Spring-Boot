package cn.lch.java_spring_boot.modules.Service;

import cn.lch.java_spring_boot.modules.entity.Country;

public interface CountryService {
    Country getCountryByCountryId(int countryId);
    Country getCountryByCountryName(String countryName);
    Country migrateCountryByRedis(int countryId);
}
