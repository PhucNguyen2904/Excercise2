package com.javaweb.repository;

import com.javaweb.repository.Entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepository {
    List<BuildingEntity> findAll(Map<String, Object> params,List<String> typeCode);
}
