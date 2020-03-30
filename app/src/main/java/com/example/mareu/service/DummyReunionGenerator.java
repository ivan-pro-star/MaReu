package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class DummyReunionGenerator {

    public static List<Reunion> generateReunionsForDemo(){
        List<Reunion> dummy_reunions =  new ArrayList<>();
        String subject = "Mario Peach Luigi et Bower et BobBomb, Mario Peach Luigi et Bower et BobBomb, ";
         dummy_reunions.add(new Reunion(4, 1, subject, "leoMessi@gmail.com, cristianoRonaldo@gamil.com, griezmann@gmail.com, mbappe@gmail.com, paulPogba@gmail.com", new GregorianCalendar( 2020,  2, 27,  18, 10) {}));
        dummy_reunions.add(new Reunion(3, 3, "Peach", "anthony@gmail.com, leo@gmail.com", new GregorianCalendar( 2020,  8, 28,  18, 5) {}));
        dummy_reunions.add(new Reunion(2, 4, "Luigi", "paul-pogba@gmail.com, hugo-llori@gmail.com, gian-luigi-buffon@gmail.com", new GregorianCalendar( 2020,  8, 29,  18, 10) {}));
        dummy_reunions.add(new Reunion(1, 2, "Daisy", "milanAc@gmail.com, liverpool@gmail.com, arsenal@gmail.com, barcelone@gmail.com, manchester@gmail.com", new GregorianCalendar( 2020,  0, 25,  18, 10) {}));
        dummy_reunions.add(new Reunion(5, 2, "Mario", "Jean-claude-vanDamme@gmail.com, lucas-hernandez@gmail.com", new GregorianCalendar( 2020,  0, 25,  18, 25) {}));
        return dummy_reunions;
    }
}
