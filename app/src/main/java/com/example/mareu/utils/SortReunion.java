package com.example.mareu.utils;

import com.example.mareu.model.Reunion;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortReunion {

    public static List<Reunion> byRoom(List<Reunion> reunions){
        Collections.sort(reunions, new Comparator<Reunion>() {
            @Override
            public int compare(Reunion reunion1, Reunion reunion2) {

                int isLower = -1;
                if(reunion2.getRoom() < reunion1.getRoom()){
                    isLower = 0;
                }
                return isLower;
            }
        });
        return reunions;
    }

    public static List<Reunion> byCalendar(List<Reunion> reunions){
        Collections.sort(reunions, new Comparator<Reunion>() {
            @Override
            public int compare(Reunion reunion1, Reunion reunion2) {
                return reunion1.getCalendar().getTime().compareTo(reunion2.getCalendar().getTime());
            }
        });
        return reunions;
    }
}

