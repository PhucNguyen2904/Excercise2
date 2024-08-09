package com.javaweb.repository;

import com.javaweb.repository.Entity.BuildingEntity;

import java.util.List;

public interface BuildingRepository {
    List<BuildingEntity> findAll(String name);
}
