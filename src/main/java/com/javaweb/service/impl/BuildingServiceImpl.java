package com.javaweb.service.impl;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.Entity.BuildingEntity;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public List<BuildingDTO> findAll(String name) {
        List<BuildingEntity> buildEntities = buildingRepository.findAll(name);
        List<BuildingDTO> result = new ArrayList<>();
        for (BuildingEntity building : buildEntities) {
            BuildingDTO dto = new BuildingDTO();
            dto.setName(building.getName());
            dto.setAddress(building.getStreet() + ", " + building.getWard());
            dto.setNumberofbasement(building.getNumberofbasement());
            result.add(dto);
        }
        return result;
    }
}
