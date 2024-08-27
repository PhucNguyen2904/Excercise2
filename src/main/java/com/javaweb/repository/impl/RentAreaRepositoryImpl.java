package com.javaweb.repository.impl;

import com.javaweb.repository.Entity.RentAreaEntity;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.util.ConnectJDBC_Util;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {

    @Override
    public List<RentAreaEntity> getValueByBuilding(Long buildingId) {
        String sql = "select * from rentarea r where r.buildingid = " + buildingId;
        List<RentAreaEntity> rentAreas = new ArrayList<>();
        try(Connection conn = ConnectJDBC_Util.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                RentAreaEntity rentArea = new RentAreaEntity();
                rentArea.setValue(rs.getString("value"));
                rentAreas.add(rentArea);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return rentAreas;
    }
}
