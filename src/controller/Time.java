package ru.vkchan.bot.controller;


import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class Time {
    static int day, month, year;
    static int min,how;
    public static void timeInit() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        TimeZone timezone = TimeZone.getTimeZone("Europe/Moscow");
        formatter.setTimeZone(timezone);
        Date date = new Date();
        day= Integer.parseInt(formatter.format(date));
        formatter.applyPattern("MM");
        month=Integer.parseInt(formatter.format(date));
        formatter.applyPattern("yyyy");
        year=Integer.parseInt(formatter.format(date));
        formatter.applyPattern("HH");
        how=Integer.parseInt(formatter.format(date));
        formatter.applyPattern("mm");
        min=Integer.parseInt(formatter.format(date));
    }

    public static String getTime() {
        return "Month-"+month+"Day-"+day+"How-"+how+"Min-"+min;
    }
    public static int getPassTime(int timeScoreUser, int currentTime){
        return currentTime-timeScoreUser;
    }
}
