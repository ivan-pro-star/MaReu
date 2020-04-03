package com.example.mareu.utils;

import com.example.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FilterReunion {
    public static final List<Reunion> byRoom(List<Reunion> reunions, int roomMin){
        List<Reunion> byRoom = new ArrayList<>();
            for(Reunion reunion : reunions) {
                if(reunion.getRoom() == roomMin ){
                    byRoom.add(reunion);
                }
            }

        return byRoom;
    }
    public static final List<Reunion> byCalendar(List<Reunion> reunions, Calendar start, Calendar end){
        List<Reunion> byCalendar = new ArrayList<>();
        for(Reunion  reunion : reunions) {

            if(isAfter(reunion.getCalendar(),start) && isBefore(reunion.getCalendar(),end)){
                byCalendar.add(reunion);
            }
        }
        return byCalendar;
    }
    private static boolean isAfter(Calendar date1, Calendar date2){

        date1 = getDateWithoutTime(date1);
        date2 = getDateWithoutTime(date2);

        boolean isAfter = false;
        int isAfterInt = date1.compareTo(date2);
        if(isAfterInt>=0)
        {
            isAfter =true;
        }
        return isAfter;
    }
     private static boolean isBefore(Calendar date1, Calendar date2){
        boolean isBefore = false;
        int isBeforeInt = date1.compareTo(date2);
        if(isBeforeInt<=0)
        {
            isBefore =true;
        }
        return isBefore;
    }
    private static Calendar getDateWithoutTime(Calendar calendarGiven){
        calendarGiven.set(Calendar.HOUR_OF_DAY,0);
        calendarGiven.set(Calendar.MINUTE,0);
        calendarGiven.set(Calendar.SECOND,0);
        calendarGiven.set(Calendar.MILLISECOND,0);
        return calendarGiven;
    }
}
