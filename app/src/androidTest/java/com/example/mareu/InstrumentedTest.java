package com.example.mareu;

import com.example.mareu.model.Reunion;
import com.example.mareu.ui.MainActivity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.RootMatchers;
import com.example.mareu.utils.DeleteViewAction;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static com.example.mareu.utils.FirstItemOnPositionAssertion.withItemPosition;
import static com.example.mareu.utils.Utils.convertIntRoomToStringRoom;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;

import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.support.test.rule.ActivityTestRule;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    private String separation = " - ";
    Reunion reunion_Room_A_Date_25_08 = new Reunion(System.currentTimeMillis(), 0, "Super Mario", "mario.com",
            new GregorianCalendar( 2020,  7, 25,  17, 05));
    Reunion reunion_Room_B_Date_24_08 = new Reunion(System.currentTimeMillis(), 1, "Luigi", "luigi.com",
            new GregorianCalendar( 2020,  7, 24,  17, 05));
    Reunion reunion_Room_C_Date_23_08 = new Reunion(System.currentTimeMillis(), 2, "Peach", "peach.com",
            new GregorianCalendar( 2020,  7, 23,  17, 05));

    Reunion reunion_Room_D_Date_22_08 = new Reunion(System.currentTimeMillis(), 3, "Peach", "peach.com",
            new GregorianCalendar( 2020,  7, 22,  17, 05));

    Reunion reunion_Room_B_Date_22_08 = new Reunion(System.currentTimeMillis(), 1, "Super Mario", "mario.com",
            new GregorianCalendar( 2020,  7, 22,  17, 05));
    Reunion reunion_to_delete = new Reunion(System.currentTimeMillis(), 6, "Super Mario", "mario.com",
            new GregorianCalendar( 2020,  6, 22,  17, 05));
   


    @Rule
    public ActivityTestRule<MainActivity> MainActivityRule =
            new ActivityTestRule(MainActivity.class);

    // TEST --
    @Test
    public void A_createReunionTest(){
        onView(withId(R.id.reunion_recycler)).check(withItemCount(0));
        addReunion(reunion_Room_A_Date_25_08);
        addReunion(reunion_Room_B_Date_24_08);
        addReunion(reunion_Room_C_Date_23_08);
        addReunion(reunion_Room_D_Date_22_08);
        addReunion(reunion_Room_B_Date_22_08);
        addReunion(reunion_to_delete);
        onView(withId(R.id.reunion_recycler)).check(withItemCount(6));
    }
    @Test
    public void B_deleteReunionTest() {

        clickMenu("Room");
        clickItemMenu("Sorter");
        onView(withId(R.id.reunion_recycler)).check(withItemCount(6));

        clickMenu("Date");
        clickItemMenu("Sorter");
        onView(withId(R.id.reunion_recycler)).check(withItemCount(6));

        //suppression
        // When perform a click on a delete icon
        onView(withId(R.id.reunion_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));

        clickMenu("Room");
        clickItemMenu("Sorter");
        onView(withId(R.id.reunion_recycler)).check(withItemCount(5));

        clickMenu("Date");
        clickItemMenu("Sorter");
        onView(withId(R.id.reunion_recycler)).check(withItemCount(5));
        
    }
    @Test
    public void C_sortTest() {
        //SORT BY ROOM (alphabetique)
        clickMenu("Room");
        clickItemMenu("Sorter");

        String labelReunion="Réunion ";
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(0, labelReunion+ convertIntRoomToStringRoom(reunion_Room_A_Date_25_08.getRoom())+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(1, labelReunion+ convertIntRoomToStringRoom(reunion_Room_B_Date_24_08.getRoom())+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(2, labelReunion+ convertIntRoomToStringRoom(reunion_Room_B_Date_22_08.getRoom())+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(3, labelReunion+ convertIntRoomToStringRoom(reunion_Room_C_Date_23_08.getRoom())+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(4, labelReunion+ convertIntRoomToStringRoom(reunion_Room_D_Date_22_08.getRoom())+separation));

        //SORT BY DATE
        clickMenu("Date");
        clickItemMenu("Sorter");

        onView(withId(R.id.reunion_recycler)).check( withItemPosition(0, reunion_Room_B_Date_22_08.getFormattedDate()+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(1, reunion_Room_D_Date_22_08.getFormattedDate()+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(2, reunion_Room_C_Date_23_08.getFormattedDate()+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(3, reunion_Room_B_Date_24_08.getFormattedDate()+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(4, reunion_Room_A_Date_25_08.getFormattedDate()+separation));
    }
    /**FilterTest
     * Creation de 5 Reunions de Room A à D, 2 reunions avec pour salle B
     *  dates du 22 au 25/08/2020
     *  2 reunions avec pour date 22/08/2020
     *  Filtre par Room B-> 2 Items
     *  Filtre par Date Intervalle 22/08/2020 au 24/08/20 -> 4 Items
     *  Filtre par Date similaire 22/08/20 -> 2Items
     * */
    @Test
    public void D_filterTest(){

        //Filter BY Room
        clickMenu("Room");
        clickItemMenu("Filter");

        setRoom(reunion_Room_B_Date_24_08.getRoom());
        onView(withText("SEARCH")).perform(click());
        onView(withId(R.id.reunion_recycler)).check(withItemCount(2));

        String labelReunion="Réunion ";
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(0, labelReunion+ convertIntRoomToStringRoom(reunion_Room_B_Date_24_08.getRoom())+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(1, labelReunion+ convertIntRoomToStringRoom(reunion_Room_B_Date_22_08.getRoom())+separation));

         //Filter BY Calendar
        clickMenu("Date");
        clickItemMenu("Filter");
        //interval
        setDatePicker(reunion_Room_D_Date_22_08.getCalendar() ,R.id.start_button);
        setDatePicker(reunion_Room_B_Date_24_08.getCalendar() ,R.id.end_button);

        onView(withText("SEARCH")).perform(click());
        //dialog fermé
        onView(withText("SEARCH")).check(doesNotExist());

        onView(withId(R.id.reunion_recycler)).check(withItemCount(4));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(0, reunion_Room_D_Date_22_08.getFormattedDate()+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(1, reunion_Room_B_Date_22_08.getFormattedDate()+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(2, reunion_Room_C_Date_23_08.getFormattedDate()+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(3, reunion_Room_B_Date_24_08.getFormattedDate()+separation));

        //MEME DATE
        clickMenu("Date");
        clickItemMenu("Filter");

        setDatePicker(reunion_Room_D_Date_22_08.getCalendar() ,R.id.start_button);
        setDatePicker(reunion_Room_D_Date_22_08.getCalendar() ,R.id.end_button);
        onView(withText("SEARCH")).perform(click());
        //dialog fermé
        onView(withText("SEARCH")).check(doesNotExist());

        onView(withId(R.id.reunion_recycler)).check(withItemCount(2));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(0, reunion_Room_B_Date_22_08.getFormattedDate()+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(1, reunion_Room_D_Date_22_08.getFormattedDate()+separation));

        //cas où start > end
        clickMenu("Date");
        clickItemMenu("Filter");
        setDatePicker(reunion_Room_D_Date_22_08.getCalendar() ,R.id.end_button);
        setDatePicker(reunion_Room_A_Date_25_08.getCalendar() ,R.id.start_button);

        //update end TextView
        onView(withId(R.id.end_textview_pick)).check(matches(withText(reunion_Room_A_Date_25_08.getFormattedDate())));
        onView(withId(R.id.error_textview)).check(matches(withText(R.string.error_dialog_calendar_change_end)));
        onView(withText("SEARCH")).perform(click());
        //dialog fermé
        onView(withText("SEARCH")).check(doesNotExist());
        onView(withId(R.id.reunion_recycler)).check(withItemCount(1));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(0, reunion_Room_A_Date_25_08.getFormattedDate()+separation));

        //cas où end < start
        clickMenu("Date");
        clickItemMenu("Filter");
        setDatePicker(reunion_Room_A_Date_25_08.getCalendar() ,R.id.start_button);
        setDatePicker(reunion_Room_D_Date_22_08.getCalendar() ,R.id.end_button);

        //start TextView update
        onView(withId(R.id.start_textview_pick)).check(matches(withText(reunion_Room_B_Date_22_08.getFormattedDate())));
        onView(withId(R.id.error_textview)).check(matches(withText(R.string.error_dialog_calendar_change_start)));
        onView(withText("SEARCH")).perform(click());

        //dialog fermé
        onView(withText("SEARCH")).check(doesNotExist());

        onView(withId(R.id.reunion_recycler)).check(withItemCount(2));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(0, reunion_Room_B_Date_22_08.getFormattedDate()+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(1, reunion_Room_D_Date_22_08.getFormattedDate()+separation));
    }


    // UTILS --
    public void clickMenu(String menu){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(menu)).perform(click());
    }
    public void clickItemMenu(String menu){
        onView(withText(menu)).perform(click());
    }

    /**
     * Go to CreateReunionActivity
     * */

    public void addReunion(Reunion reunion)
    {
        onView(withId(R.id.add_reunion)).perform(click());
        // CreateReunionActivity Should start
        setDatePicker(reunion.getCalendar(), R.id.dateButton);
        setTimePicker(reunion.getCalendar());
        onView(withId(R.id.save_create_reunion))
                .check(matches(not(isEnabled())));
        setSubjectAndMail(reunion.getSubject(), reunion.getAllParticipants());
        onView(withId(R.id.save_create_reunion))
                .check(matches(isEnabled()));
        setRoom(reunion.getRoom());
        onView(withId(R.id.save_create_reunion))
                .perform(click());
    }

    public void setDatePicker(Calendar calendar, int  idButton)
    {
        onView(withId(idButton)).perform(click());
        int month = calendar.get(Calendar.MONTH)+1;// 0 = janvier, pour PickerActions.setDate il faut 1 = janvier
        onView(withClassName(equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(calendar.get(Calendar.YEAR), month, calendar.get(Calendar.DAY_OF_MONTH)));
        onView(withText("OK")).perform(click());
    }

    public void setTimePicker(Calendar calendar) {
        onView(withId(R.id.timeButton)).perform(click());
        onView(withClassName(equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)));
        onView(withText("OK")).perform(click());
    }
    public void setSubjectAndMail(String subject, String allMails){
        onView(withId(R.id.edit_text_subject))
                .perform(typeText(subject), closeSoftKeyboard());
        onView(withId(R.id.edit_text_all_mails))
                .perform(typeText(allMails), closeSoftKeyboard());
    }
    public void setRoom(int valSpinner){
        onView(withId(R.id.spinnerRoom)).perform(click());
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(valSpinner).perform(click());
    }
}