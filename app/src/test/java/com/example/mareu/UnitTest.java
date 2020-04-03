package com.example.mareu;

import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;
import com.example.mareu.ui.MainActivity;
import com.example.mareu.utils.FilterReunion;

import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnitTest {
    private ApiService service;
    private boolean ROOM = false;
    private boolean CALENDAR = true;

    Reunion reunion_Room_A_Date_25_08 = new Reunion(System.currentTimeMillis(), 0, "Super Mario", "mario.com",
            new GregorianCalendar( 2020,  7, 25,  17, 05));
    Reunion reunion_Room_B_Date_24_08 = new Reunion(System.currentTimeMillis(), 1, "Luigi", "luigi.com",
            new GregorianCalendar( 2020,  7, 24,  17, 05));
    Reunion reunion_Room_C_Date_23_08 = new Reunion(System.currentTimeMillis(), 2, "Peach", "peach.com",
            new GregorianCalendar( 2020,  7, 23,  17, 05));

    Reunion reunion_Room_D_Date_22_08 = new Reunion(System.currentTimeMillis(), 3, "Peach", "peach.com",
            new GregorianCalendar( 2020,  7, 22,  17, 05));

    Reunion reunion_Room_E_Date_22_08 = new Reunion(System.currentTimeMillis(), 4, "Peach", "peach.com",
            new GregorianCalendar( 2020,  7, 22,  17, 05));

    Reunion reunion_Room_F_Date_22_08 = new Reunion(System.currentTimeMillis(), 5, "Peach", "peach.com",
            new GregorianCalendar( 2020,  7, 22,  17, 05));
    Reunion reunion_Room_B_Date_21_08 = new Reunion(System.currentTimeMillis(), 1, "Super Mario", "mario.com",
            new GregorianCalendar( 2020,  7, 21,  17, 05));

    Reunion reunion_Room_B_Date_20_08 = new Reunion(System.currentTimeMillis(), 1, "Super Mario", "mario.com",
            new GregorianCalendar( 2020,  7, 20,  17, 05));

    @Before
    public void setUp(){
        service = DI.getNewInstanceApiService();
    }

    //un même test pour les parties Room et Calendar
    @Test
    public void sortReunion() {

        //on ajoute 8 reunions du 20 au 25/08/2020
        // de room A à F
        service.createReunion(reunion_Room_A_Date_25_08);
        service.createReunion(reunion_Room_B_Date_24_08);
        service.createReunion(reunion_Room_C_Date_23_08);
        service.createReunion(reunion_Room_D_Date_22_08);
        service.createReunion(reunion_Room_E_Date_22_08);
        service.createReunion(reunion_Room_F_Date_22_08);
        service.createReunion(reunion_Room_B_Date_21_08);
        service.createReunion(reunion_Room_B_Date_20_08);

        //ROOM
        assertTrue(service.getReunions(ROOM).get(0).getRoom() == reunion_Room_A_Date_25_08.getRoom());
        assertTrue(service.getReunions(ROOM).get(1).getRoom() == reunion_Room_B_Date_24_08.getRoom());
        assertTrue(service.getReunions(ROOM).get(2).getRoom() == reunion_Room_B_Date_21_08.getRoom());
        assertTrue(service.getReunions(ROOM).get(3).getRoom() == reunion_Room_B_Date_20_08.getRoom());
        assertTrue(service.getReunions(ROOM).get(4).getRoom() == reunion_Room_C_Date_23_08.getRoom());
        assertTrue(service.getReunions(ROOM).get(5).getRoom() == reunion_Room_D_Date_22_08.getRoom());
        assertTrue(service.getReunions(ROOM).get(6).getRoom() == reunion_Room_E_Date_22_08.getRoom());
        assertTrue(service.getReunions(ROOM).get(7).getRoom() == reunion_Room_F_Date_22_08.getRoom());

        //CALENDAR
        assertTrue(service.getReunions(CALENDAR).get(0).getCalendar() == reunion_Room_B_Date_20_08.getCalendar());
        assertTrue(service.getReunions(CALENDAR).get(1).getCalendar() == reunion_Room_B_Date_21_08.getCalendar());
        assertTrue(service.getReunions(CALENDAR).get(2).getCalendar() == reunion_Room_D_Date_22_08.getCalendar());
        assertTrue(service.getReunions(CALENDAR).get(3).getCalendar() == reunion_Room_E_Date_22_08.getCalendar());
        assertTrue(service.getReunions(CALENDAR).get(4).getCalendar() == reunion_Room_F_Date_22_08.getCalendar());
        assertTrue(service.getReunions(CALENDAR).get(5).getCalendar() == reunion_Room_C_Date_23_08.getCalendar());
        assertTrue(service.getReunions(CALENDAR).get(6).getCalendar() == reunion_Room_B_Date_24_08.getCalendar());
        assertTrue(service.getReunions(CALENDAR).get(7).getCalendar() == reunion_Room_A_Date_25_08.getCalendar());
    }
    @Test
    public void deleteReunionTest(  ) {

        service.createReunion(reunion_Room_A_Date_25_08);
        service.createReunion(reunion_Room_B_Date_24_08);
        service.createReunion(reunion_Room_C_Date_23_08);
        service.createReunion(reunion_Room_D_Date_22_08);
        service.createReunion(reunion_Room_E_Date_22_08);
        service.createReunion(reunion_Room_F_Date_22_08);
        service.createReunion(reunion_Room_B_Date_21_08);
        service.createReunion(reunion_Room_B_Date_20_08);

        Reunion reunionToDelete = service.getReunions(ROOM).get(0);
        assertTrue(service.getReunions(ROOM).contains(reunionToDelete));
        assertTrue(service.getReunions(CALENDAR).contains(reunionToDelete));

        service.deleteReunion(reunionToDelete);
        assertFalse(service.getReunions(ROOM).contains(reunionToDelete));
        assertFalse(service.getReunions(CALENDAR).contains(reunionToDelete));
    }

    /**FilterByCalendar
     *  liste de 8 reunions,
     *  filtre du 22 au 24/08/2020 pour le test 1
     * soit 5 réunions pour le test 1
     * filtre du 22 au 22/08 pour le TEST 2
     *  soit 3 reunions pour le TEST 2
     *
     *  20/08/2020
     *  21/08/2020
     *  22/08/2020 ->  test 1
     *  22/08/2020 ->  test 1 et -> test 2
     *  22/08/2020 ->  test 1 et -> test 2
     *  23/08/2020 ->  test 1 et -> test 2
     *  24/08/2020 ->  test 1
     *  25/08/2020
     *
     *
     * */
    @Test
    public void filterByCalendar(){

        service.createReunion(reunion_Room_A_Date_25_08);
        service.createReunion(reunion_Room_B_Date_24_08);
        service.createReunion(reunion_Room_C_Date_23_08);
        service.createReunion(reunion_Room_D_Date_22_08);
        service.createReunion(reunion_Room_E_Date_22_08);
        service.createReunion(reunion_Room_F_Date_22_08);
        service.createReunion(reunion_Room_B_Date_21_08);
        service.createReunion(reunion_Room_B_Date_20_08);

        //test 1, intervalle
        List<Reunion> reunionsFilter = service.getReunions(reunion_Room_D_Date_22_08.getCalendar(), reunion_Room_B_Date_24_08.getCalendar());
        assertEquals(5,reunionsFilter.size());
        assertEquals(reunion_Room_D_Date_22_08.getCalendar().getTime(),reunionsFilter.get(0).getCalendar().getTime());
        assertEquals(reunion_Room_D_Date_22_08.getCalendar().getTime(),reunionsFilter.get(1).getCalendar().getTime());
        assertEquals(reunion_Room_D_Date_22_08.getCalendar().getTime(),reunionsFilter.get(2).getCalendar().getTime());
        assertEquals(reunion_Room_C_Date_23_08.getCalendar().getTime(),reunionsFilter.get(3).getCalendar().getTime());
        assertEquals(reunion_Room_B_Date_24_08.getCalendar().getTime(),reunionsFilter.get(4).getCalendar().getTime());

        //test 2, même date
        reunionsFilter = service.getReunions( reunion_Room_F_Date_22_08.getCalendar(), reunion_Room_F_Date_22_08.getCalendar());
        assertEquals(3,reunionsFilter.size());
        assertEquals(reunion_Room_F_Date_22_08.getCalendar().getTime(),reunionsFilter.get(0).getCalendar().getTime());
        assertEquals(reunion_Room_F_Date_22_08.getCalendar().getTime(),reunionsFilter.get(1).getCalendar().getTime());
        assertEquals(reunion_Room_F_Date_22_08.getCalendar().getTime(),reunionsFilter.get(1).getCalendar().getTime());

        //test 3, date before > date after, ce cas est impossible car le controlleur l'interdit, voir test instrumenté.
        // j'ai malgré tout préparé le test
        //reunionsFilter = service.getFilterReunionsByCalendar( reunion_Room_B_Date_24_08.getCalendar().getTime(), reunion_Room_F_Date_22_08.getCalendar().getTime());
        //assertEquals(0,reunionsFilter.size());
    }
    @Test
    /** liste de 8 reunions de A a F avec 3 reunions de room = B, 1 reunions de room = D
     *  filtre sur B, puis sur D
     *  3 reunions pour le TEST 1 et 1 reunion pour le TEST 2
     *  A
     *  B -> oui test 1
     *  B -> oui test 1
     *  B -> oui test 1
     *  C ->
     *  D -> oui TEST 2
     *  E
     *  F
     *
     * */
    public void filterByRoom(){
        assertEquals(service.getReunions(ROOM).size(),0);

        service.createReunion(reunion_Room_A_Date_25_08);
        service.createReunion(reunion_Room_B_Date_24_08);
        service.createReunion(reunion_Room_C_Date_23_08);
        service.createReunion(reunion_Room_D_Date_22_08);
        service.createReunion(reunion_Room_E_Date_22_08);
        service.createReunion(reunion_Room_F_Date_22_08);
        service.createReunion(reunion_Room_B_Date_21_08);
        service.createReunion(reunion_Room_B_Date_20_08);

        List<Reunion> reunionsFilter;

        //test 1
        reunionsFilter = service.getReunions( 1);
        assertEquals(3,reunionsFilter.size());

        //Rooms A
        assertEquals(1,reunionsFilter.get(0).getRoom());
        assertEquals(1,reunionsFilter.get(1).getRoom());

       //test2
        reunionsFilter = service.getReunions (2);
        assertEquals(1,reunionsFilter.size());
        //Room C
        assertEquals(2,reunionsFilter.get(0).getRoom());
    }

}