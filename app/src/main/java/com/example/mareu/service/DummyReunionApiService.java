package com.example.mareu.service;

import com.example.mareu.model.Reunion;
import com.example.mareu.utils.FilterReunion;
import com.example.mareu.utils.SortReunion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.mareu.service.DummyReunionGenerator.generateReunionsForDemo;

public class DummyReunionApiService implements ApiService {

    //private List<Reunion> mReunions = generateReunionsForDemo();
    private List<Reunion> mReunions = new ArrayList<>(); // for test

    public List<Reunion> getReunionsSorterByRoom() {
        return SortReunion.byRoom(mReunions);
    }
    public List<Reunion> getReunionsSorterByCalendar() {
        return SortReunion.byCalendar(mReunions);
    }
    public List<Reunion> getFilterReunionsByRoom(int room) {
        return FilterReunion.byRoom(getReunionsSorterByRoom(), room);
    }
    public List<Reunion> getFilterReunionsByCalendar(Calendar Start, Calendar end) {
        return FilterReunion.byCalendar(getReunionsSorterByCalendar(), Start, end);
    }

    //OVERRIDE --
    @Override
    public List<Reunion> getReunions(int room) {
        return getFilterReunionsByRoom(room);
    }

    @Override
    public List<Reunion> getReunions(boolean sorterByCalendar) {
        if(sorterByCalendar){
            return getReunionsSorterByCalendar();
        }
        else{
            return getReunionsSorterByRoom();
        }
    }
    @Override
    public List<Reunion> getReunions(Calendar start, Calendar end) {
        return getFilterReunionsByCalendar(start, end);
    }

    @Override
    public void createReunion(Reunion reunion) {
        mReunions.add(reunion);
    }
    @Override
    public void deleteReunion(Reunion reunion) {
        mReunions.remove(reunion);
    }
}
