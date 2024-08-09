package com.javaweb.api;


import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
    @GetMapping(value = "/api/buildings/")
    public List<BuildingDTO> getBuilding(@RequestParam(value = "name",required = false)String name,
                                            @RequestParam(value = "districid",required = false)String districid){
        List<BuildingDTO> result = buildingService.findAll(name);
        return result;
    }
}
