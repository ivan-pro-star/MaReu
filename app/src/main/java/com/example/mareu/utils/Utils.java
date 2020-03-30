package com.example.mareu.utils;

import java.text.SimpleDateFormat;

public abstract class Utils {

    public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("HH:mm");

    public static final int convertStringRoomToInt(String stringRoom){
        int intRoom = 0;
        switch (stringRoom){
            case "A":
                intRoom = 0;
                break;
            case "B":
                intRoom = 1;
                break;
            case "C":
                intRoom = 2;
                break;
            case "D":
                intRoom = 3;
                break;
            case "E":
                intRoom = 4;
                break;
            case "F":
                intRoom = 5;
                break;
            case "G":
                intRoom = 6;
                break;
            case "H":
                intRoom = 7;
                break;
            case "I":
                intRoom = 8;
                break;
            case "J":
                intRoom = 9;
                break;
        }
        return intRoom;
    }
    public static final String convertIntRoomToStringRoom(int intRoom){
        String stringRoom = "A";
        switch ( intRoom){
            case 0:
                stringRoom = "A";
                break;
            case 1:
                stringRoom = "B";
                break;
            case 2:
                stringRoom = "C";
                break;
            case 3:
                stringRoom = "D";
                break;
            case 4:
                stringRoom = "E";
                break;
            case 5:
                stringRoom = "F";
                break;
            case 6:
                stringRoom = "G";
                break;
            case 7:
                stringRoom = "H";
                break;
            case 8:
                stringRoom = "I";
                break;
            case 9:
                stringRoom = "J";
                break;
        }
        return stringRoom;
    }

}
