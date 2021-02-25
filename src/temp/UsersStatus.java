package ru.vkchan.bot.temp;

import java.util.HashMap;

public class UsersStatus {
    private static HashMap<Integer,String> cache=new HashMap<Integer,String>();
    public static void setStatus(String str, int ID){
        cache.put(ID, str);
    }
    public static String getStatus(int ID){
        return cache.get(ID);
    }
    public static boolean checkExist(int ID){
        return cache.containsKey(ID);
    }
    public static void clear(){
        cache.clear();
    }
}
