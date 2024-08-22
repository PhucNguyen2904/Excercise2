package com.javaweb.util;

public class NumberUtil {
    public static boolean isNumber(String value) {
        try{
            Long.parseLong(value);
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }

}
