package com.example.mareu.service;

import com.example.mareu.model.Reunion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class DummyReunionGenerator {

    public static List<Reunion> generateReunionsForDemo(){
        List<Reunion> dummy_reunions =  new ArrayList<Reunion>();
        String subject = "Mario Peach Luigi et Bower et BobBomb, Mario Peach Luigi et Bower et BobBomb, ";
         dummy_reunions.add(new Reunion(4, 1, subject, "leoMessi@gmail.com, cristianoRonaldo@gamil.com, griezmann@gmail.com, mbappe@gmail.com, paulPogba@gmail.com", new GregorianCalendar( 2020,  2, 27,  18, 00) {}));
        dummy_reunions.add(new Reunion(3, 3, "Peach", "pierre et paul", new GregorianCalendar( 2020,  0, 28,  18, 00) {}));
        dummy_reunions.add(new Reunion(2, 4, "Luigi", "pierre et paul", new GregorianCalendar( 2020,  0, 29,  18, 00) {}));
        dummy_reunions.add(new Reunion(1, 2, "Daisy", "pierre et paul", new GregorianCalendar( 2020,  0, 25,  18, 00) {}));
        return dummy_reunions;
    }



}
