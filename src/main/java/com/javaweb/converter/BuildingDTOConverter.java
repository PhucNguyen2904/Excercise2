package com.javaweb.converter;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.Entity.BuildingEntity;
import com.javaweb.repository.Entity.DistrictEntity;
import com.javaweb.repository.Entity.RentAreaEntity;
import com.javaweb.repository.RentAreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingDTOConverter {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO convert(BuildingEntity building) {
        BuildingDTO buildingdto = modelMapper.map(building, BuildingDTO.class);
        DistrictEntity districtEntity = districtRepository.findByID(building.getDistrict());
        buildingdto.setAddress(building.getStreet() + ", " + building.getWard()+ "," +districtEntity.getName() );
        List<RentAreaEntity> rentAreas = rentAreaRepository.getValueByBuilding(building.getId());
        String areaResult = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        buildingdto.setRentArea(areaResult);
        return buildingdto;
    }


}
