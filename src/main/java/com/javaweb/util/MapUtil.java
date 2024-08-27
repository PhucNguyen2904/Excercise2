package com.javaweb.util;

import java.util.Map;

public class MapUtil {
    public static <T> T  getObject(Map<String, Object> params, String key, Class<T> clazz) {
        Object obj = params.getOrDefault(key,null);
        if(obj !=null){
            if(clazz.getTypeName().equals("java.lang.Long")){
               obj = obj != null ? Long.parseLong(obj.toString()) : null;
            }
            else if(clazz.getTypeName().equals("java.lang.Integer")){
                obj = obj != null ? Integer.parseInt(obj.toString()) : null;
            }
            else if(clazz.getTypeName().equals("java.lang.String")){
                obj = obj.toString();
            }
            return clazz.cast(obj);
        }
        return null;
    }
}
