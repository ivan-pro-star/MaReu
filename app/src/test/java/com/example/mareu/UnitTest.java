package com.example.mareu;

import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    private ApiService service;
    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }
    @Test
    public void unitTest(){
        createReunionTest(service.getReunionsByRoom(), service.getReunionsByCalendar());
        deleteReunionTest(service.getReunionsByRoom(), service.getReunionsByCalendar());
    }

    public void createReunionTest( List<Reunion> listesReunionsRoom ,  List<Reunion> listesReunionsCalendar ) {
        Reunion reunionToAdd =
                new Reunion(System.currentTimeMillis(),5,"sujet test","test.com", Calendar.getInstance());
        assertFalse(listesReunionsRoom.contains(reunionToAdd));
        assertFalse(listesReunionsCalendar.contains(reunionToAdd));
        service.createReunion(reunionToAdd);
        assertTrue(listesReunionsRoom.contains(reunionToAdd));
        assertTrue(listesReunionsCalendar.contains(reunionToAdd));
    }

    public void deleteReunionTest( List<Reunion> listesReunionsRoom ,  List<Reunion> listesReunionsCalendar ) {
        Reunion reunionToDelete = listesReunionsRoom.get(0);
        assertTrue(listesReunionsRoom.contains(reunionToDelete));
        assertTrue(listesReunionsCalendar.contains(reunionToDelete));
        service.deleteReunion(reunionToDelete);
        assertFalse(listesReunionsRoom.contains(reunionToDelete));
        assertFalse(listesReunionsCalendar.contains(reunionToDelete));
    }
}