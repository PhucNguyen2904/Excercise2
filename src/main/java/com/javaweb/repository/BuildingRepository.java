package com.javaweb.repository;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.repository.Entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepository {
    List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
}
