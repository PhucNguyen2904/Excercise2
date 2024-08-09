package com.javaweb.repository.impl;


import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.Entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/real_estate1";
    static final String USER = "root";
    static final String PASS = "Binbin29042004";

    @Override
    public List<BuildingEntity> findAll(String name) {
        List<BuildingEntity> result = new ArrayList<>();
        String sql = "select * from building where name like '%" + name + "%'";

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                BuildingEntity building = new BuildingEntity();
                building.setName(rs.getString("name"));
                building.setWard(rs.getString("ward"));
                building.setStreet(rs.getString("street"));
                building.setNumberofbasement(rs.getInt("numberofbasement"));
                result.add(building);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}
