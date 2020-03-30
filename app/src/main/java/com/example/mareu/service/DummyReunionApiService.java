package com.example.mareu.service;

import com.example.mareu.model.Reunion;
import com.example.mareu.utils.SortReunion;


import java.util.ArrayList;
import java.util.List;

import static com.example.mareu.service.DummyReunionGenerator.generateReunionsForDemo;

public class DummyReunionApiService implements ApiService {
    private List<Reunion> byRoom = new ArrayList<>();
   // private List<Reunion> byRoom = generateReunionsForDemo();
    private List<Reunion> byCalendar = new ArrayList<>();
    //private List<Reunion> byCalendar = generateReunionsForDemo();

    @Override
    public List<Reunion> getReunionsByRoom() {
        return SortReunion.byRoom(byRoom);
    }
     @Override
    public List<Reunion> getReunionsByCalendar() {
        return SortReunion.byCalendar(byCalendar);
    }

    @Override
    public void createReunion(Reunion reunion) {
        byRoom.add(reunion);
        byCalendar.add(reunion);
    }

    @Override
    public void deleteReunion(Reunion reunion) {
        byRoom.remove(reunion);
        byCalendar.remove(reunion);
    }
    @Override
    public void setReunion(List<Reunion> reunions) {
        byRoom = reunions;
        byCalendar = reunions;
    }
}
