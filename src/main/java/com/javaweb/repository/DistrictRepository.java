package com.javaweb.repository;

import com.javaweb.repository.Entity.DistrictEntity;

import java.util.List;

public interface DistrictRepository {
    DistrictEntity findByID(Long id);
}
