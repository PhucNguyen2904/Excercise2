package com.javaweb.repository;

import com.javaweb.repository.Entity.RentAreaEntity;

import java.util.List;

public interface RentAreaRepository {
    List<RentAreaEntity> getValueByBuilding(Long buildingId);
}
