package com.javaweb.repository.impl;


import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.Entity.BuildingEntity;
import com.javaweb.util.ConnectJDBC_Util;
import com.javaweb.util.NumberUtil;
import com.javaweb.util.StringUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {


    public void joinTable(Map<String, Object> params, StringBuilder sql, List<String> typeCode){
        String staffId = (String)params.get("staffId");
        if(StringUtil.CheckString(staffId)){
            sql.append("inner join assignmentbuilding ab on b.id = ab.building_id ");
            sql.append("inner join user u on ab.staff_id = u.id ");
        }
        if(params.get("typeCode") != null && typeCode.size() != 0){
            sql.append( "inner join buildingrenttype br on b.id = br.buildingid ");
            sql.append( "inner join renttype rt on br.renttypeid = rt.id");
        }
        String rentAreaFrom = (String)params.get("rentAreaFrom");
        String rentAreaTo = (String)params.get("rentAreaTo");
        if(StringUtil.CheckString(rentAreaFrom) == true || StringUtil.CheckString(rentAreaTo) == true){
            sql.append("inner join rentarea rt on b.id = rt.buildingid ");
        }
        sql.append(")");
    }

    public void queryNormal(Map<String, Object> params, StringBuilder where){
        for(Map.Entry<String, Object> entry : params.entrySet()) {
            if(!entry.getKey().equals("staffId") && !entry.getKey().equals("typeCode") &&
                    !entry.getKey().startsWith("area") && !entry.getKey().startsWith("rentPrice")){
                String value = entry.getValue().toString();
                if(StringUtil.CheckString(value)){
                    if(NumberUtil.isNumber(value)){
                        where.append(" and b." + entry.getKey() + " = " + value);
                    }
                    else{
                    where.append(" and b." + entry.getKey() + " like '%" + value + "%'");
                    }
                }
            }
        }
    }

    public void querySpecial(Map<String, Object> params, StringBuilder where,List<String> typeCode){
        String staffId = (String)params.get("staffId");
        if(StringUtil.CheckString(staffId)){
            where.append(" and ab.staff_id = " + staffId );
        }
//        if(typeCode != null && typeCode.size() != 0){
//            List<String> code = new ArrayList<>();
//            for(String item : typeCode){
//                code.add("'" + item + "'");
//            }
//            where.append(" and rt.code in ("+ String.join(",",code)+ ") ");
//        }
        if(typeCode != null && typeCode.size() != 0){
            where.append(" and (");
            String sql = typeCode.stream().map(it -> "rt.code like '" + "%" + it + "%'").collect(Collectors.joining(" OR "));
            where.append(sql);
            where.append(")");
        }
        String areaMin = (String)params.get("areaMin");
        String areaMax = (String)params.get("areaMax");
        if(StringUtil.CheckString(areaMin)){
            where.append(" and r.value >= " + areaMin );
        }
        if(StringUtil.CheckString(areaMax)){
            where.append(" and r.value <= " + areaMax );
        }
        String rentPriceMin = (String)params.get("rentPriceMin");
        String rentPriceMax = (String)params.get("rentPriceMax");
        if(StringUtil.CheckString(rentPriceMin)){
            where.append(" and b.rentprice >= " + rentPriceMin );
        }
        if(StringUtil.CheckString(rentPriceMax)){
            where.append(" and b.rentprice <= " + rentPriceMax );
        }
    }

    @Override
    public List<BuildingEntity> findAll(Map<String, Object> params,List<String> typeCode) {
        List<BuildingEntity> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select * from (building b ");
        StringBuilder where = new StringBuilder(" where 1 = 1 ");
        joinTable(params, sql,typeCode);
        queryNormal(params,where);
        querySpecial(params,where,typeCode);
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
