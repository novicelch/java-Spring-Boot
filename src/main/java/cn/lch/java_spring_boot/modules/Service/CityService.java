package cn.lch.java_spring_boot.modules.Service;

import cn.lch.java_spring_boot.modules.common.vo.SearchVo;
import cn.lch.java_spring_boot.modules.entity.City;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CityService {

    List<City> getCitiesByCountryId(int countryId);
    PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo);
}
