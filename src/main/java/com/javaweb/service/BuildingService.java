package com.javaweb.service;

import com.javaweb.model.BuildingDTO;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode);
}
