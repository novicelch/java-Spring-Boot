package cn.lch.java_spring_boot.modules.Service.impl;

import cn.lch.java_spring_boot.modules.Service.CountryService;
import cn.lch.java_spring_boot.modules.dao.CountryDao;
import cn.lch.java_spring_boot.modules.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lch.java_spring_boot.utils.RedisUtils;
@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryDao countryDao;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }

    @Override
    public Country migrateCountryByRedis(int countryId) {
        Country country = countryDao.getCountryByCountryId(countryId);

        String countryKey = String.format("country%d", countryId);
        redisUtils.set(countryKey, country);

        return (Country) redisUtils.get(countryKey);
    }
}
