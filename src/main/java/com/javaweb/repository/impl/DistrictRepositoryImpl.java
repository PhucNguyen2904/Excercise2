package com.javaweb.repository.impl;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.Entity.DistrictEntity;
import com.javaweb.util.ConnectJDBC_Util;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {


    @Override
    public DistrictEntity findByID(Long id) {
        String sql = "select d.name from district d where d.id = " + id ;
        DistrictEntity district = new DistrictEntity();
        try(Connection conn = ConnectJDBC_Util.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                district.setName(rs.getString("name"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return district;
    }

}
