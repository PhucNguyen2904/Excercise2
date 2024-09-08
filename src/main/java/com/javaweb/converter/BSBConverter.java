package com.javaweb.converter;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.util.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class BSBConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String,Object> params, List<String> typeCode){
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                                                         .setName(MapUtil.getObject(params,"name", String.class))
                                                         .setFloorArea(MapUtil.getObject(params,"floorarea", Long.class))
                                                         .setWard(MapUtil.getObject(params,"ward", String.class))
                                                         .setStreet(MapUtil.getObject(params,"district", String.class))
                                                         .setTypeCode(typeCode)
                                                         .setDistrictId(MapUtil.getObject(params,"districtid", Long.class))
                                                         .setNumberOfBasement(MapUtil.getObject(params,"numberofbasement", Integer.class))
                                                         .setManagerName(MapUtil.getObject(params,"managername", String.class))
                                                         .setPhoneNumber(MapUtil.getObject(params,"managerphonenumber", String.class))
                                                         .setAreaFrom(MapUtil.getObject(params,"areafrom", Long.class))
                                                         .setAreaTo(MapUtil.getObject(params,"areato", Long.class))
                                                         .setRentPriceFrom(MapUtil.getObject(params,"rentpricefrom", Long.class))
                                                         .setRentPriceTo(MapUtil.getObject(params,"rentpriceto", Long.class))
                                                         .setStaffId(MapUtil.getObject(params,"staffid", Long.class))
                                                         .build();
        return buildingSearchBuilder;
    }





}
