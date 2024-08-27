package com.javaweb.util;

public class StringUtil {
    public static boolean CheckString(String data) {
        if(data != null && !data.equals("")){
            return true;
        }
        return false;
    }
}
