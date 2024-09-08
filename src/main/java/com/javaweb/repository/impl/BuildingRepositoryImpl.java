package com.javaweb.repository.impl;


import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.Entity.BuildingEntity;
import com.javaweb.util.ConnectJDBC_Util;
import com.javaweb.util.NumberUtil;
import com.javaweb.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    public static void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql){
        Long staffId = buildingSearchBuilder.getStaffId();
        if(staffId != null){
            sql.append("inner join assignmentbuilding ab on b.id = ab.building_id ");
            sql.append("inner join user u on ab.staff_id = u.id ");
        }
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if(typeCode != null && typeCode.size() != 0){
            sql.append( "inner join buildingrenttype br on b.id = br.buildingid ");
            sql.append( "inner join renttype rt on br.renttypeid = rt.id");
        }
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        if(rentAreaFrom != null || rentAreaTo != null){
            sql.append("inner join rentarea rt on b.id = rt.buildingid ");
        }
        sql.append(")");
    }

    public void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where){
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                String fieldName = field.getName();
                if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area") && !fieldName.startsWith("rent")){
                    Object value = field.get(buildingSearchBuilder);
                    if(value != null){
                        if(field.getType().getName().equals("java.lang.Long") || field.getType().getName().equals("java.lang.Integer")){
                            where.append(" AND b." + fieldName + " = " + value);
                        }
                        else if(field.getType().getName().equals("java.lang.String")){
                            where.append(" AND b." + fieldName + " = '" + value + "'");
                        }
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where){
        Long staffId = buildingSearchBuilder.getStaffId();
        if(staffId != null){
            where.append(" and ab.staff_id = " + staffId );
        }

        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if(typeCode != null && typeCode.size() != 0){
            where.append(" and (");
            String sql = typeCode.stream().map(it -> "rt.code like '" + "%" + it + "%'").collect(Collectors.joining(" OR "));
            where.append(sql);
            where.append(")");
        }
        Long areaMin = buildingSearchBuilder.getAreaFrom();
        Long areaMax = buildingSearchBuilder.getAreaTo();
        if(areaMin != null){
            where.append(" and r.value >= " + areaMin );
        }
        if(areaMax != null){
            where.append(" and r.value <= " + areaMax );
        }
        Long rentPriceMin = buildingSearchBuilder.getRentPriceFrom();
        Long rentPriceMax = buildingSearchBuilder.getRentPriceTo();
        if(rentPriceMin != null){
            where.append(" and b.rentprice >= " + rentPriceMin );
        }
        if(rentPriceMax != null){
            where.append(" and b.rentprice <= " + rentPriceMax );
        }
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        List<BuildingEntity> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select * from (building b ");
        StringBuilder where = new StringBuilder(" where 1 = 1 ");
        joinTable(buildingSearchBuilder, sql);
        queryNormal(buildingSearchBuilder,where);
        querySpecial(buildingSearchBuilder,where);
        sql.append(where.toString());
        try(Connection conn = ConnectJDBC_Util.getConnection()){
            Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString());
            while(rs.next()){
                BuildingEntity building = new BuildingEntity();
                building.setId(rs.getLong("b.id"));
                building.setName(rs.getString("b.name"));
                building.setWard(rs.getString("ward"));
                building.setStreet(rs.getString("street"));
                building.setDistrict(rs.getLong("b.districtid"));
                building.setNumberOfBasement(rs.getInt("numberofbasement"));
                building.setManagerName(rs.getString("managername"));
                building.setPhoneNumber(rs.getString("managerphonenumber"));
                building.setFloorArea(rs.getInt("floorarea"));
                building.setRentPrice(rs.getInt("rentprice"));
                building.setServiceFee(rs.getInt("servicefee"));
                building.setBrokerageFee(rs.getInt("brokeragefee"));
                result.add(building);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
