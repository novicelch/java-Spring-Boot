package cn.lch.java_spring_boot.modules.Service.impl;

import cn.lch.java_spring_boot.modules.Service.CityService;
import cn.lch.java_spring_boot.modules.common.vo.SearchVo;
import cn.lch.java_spring_boot.modules.dao.CityDao;
import cn.lch.java_spring_boot.modules.entity.City;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;
    @Override
    public List<City> getCitiesByCountryId(int countryId) {
        return Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<City>(Optional.ofNullable(cityDao.getCitiesByCountryId(countryId))
                .orElse(Collections.emptyList()));
    }
}
