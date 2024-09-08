package com.javaweb.api;


import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController

public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;



    @GetMapping(value = "/api/buildings/")
    public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object>  params,
                                         @RequestParam (value = "typeCode", required = false) List<String> typeCode){
        List<BuildingDTO> result = buildingService.findAll(params, typeCode);
        return result;
    }
//    @DeleteMapping(value = "/api/buildings/{id}")
//    public void deleteBuilding(@PathVariable Integer id){
//        System.out.println(data);
//    }
}
