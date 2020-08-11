package cn.lch.java_spring_boot.modules.Service.impl;

import cn.lch.java_spring_boot.modules.Service.CountryService;
import cn.lch.java_spring_boot.modules.dao.CountryDao;
import cn.lch.java_spring_boot.modules.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryDao countryDao;
    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }
}
