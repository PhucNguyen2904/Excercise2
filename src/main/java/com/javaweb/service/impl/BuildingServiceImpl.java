package com.javaweb.service.impl;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.converter.BSBConverter;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.Entity.BuildingEntity;
import com.javaweb.repository.Entity.DistrictEntity;
import com.javaweb.repository.Entity.RentAreaEntity;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingDTOConverter buildingDTOConverter;
    @Autowired
    private BSBConverter bsbConverter;

    @Override
    public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
        BuildingSearchBuilder buildingSearchBuilder = bsbConverter.toBuildingSearchBuilder(params,typeCode);
        List<BuildingEntity> buildEntities = buildingRepository.findAll(buildingSearchBuilder);
        List<BuildingDTO> result = new ArrayList<>();
        for (BuildingEntity building : buildEntities) {
            BuildingDTO buildingDTO = buildingDTOConverter.convert(building);
            result.add(buildingDTO);
        }
        return result;
    }
}
