package com.example.mareu;

import com.example.mareu.model.Reunion;
import com.example.mareu.ui.MainActivity;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;

import com.example.mareu.utils.DeleteViewAction;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static com.example.mareu.utils.RecyclerViewOnItemAssertion.withItemPosition;
import static com.example.mareu.utils.Utils.convertIntRoomToStringRoom;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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

import org.hamcrest.Matchers;

import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class InstrumentalisedTest {
    private static int ITEMS_COUNT = 3;
    private String separation = " - ";
    private Reunion reunionTest = new Reunion(System.currentTimeMillis(), 7, "Super Mario", "Le",
            new GregorianCalendar( 2021,  0, 25,  17, 05)
            );
    @Rule
    public ActivityTestRule<MainActivity> MainActivityRule =
            new ActivityTestRule(MainActivity.class);

    /**Test for MainActivity
     * */

    @Test
    public void allTest()
    {
        MainActivityRule.getActivity();
        sortTest();
        deleteReunionTest();
    }
    // TEST --
    public void sortTest() {

        Reunion reunion_Room_A_Date_25_28 = new Reunion(System.currentTimeMillis(), 0, "Super Mario", "mario.com",
                new GregorianCalendar( 2020,  7, 25,  17, 05));
        Reunion reunion_Room_B_Date_24_08 = new Reunion(System.currentTimeMillis(), 1, "Luigi", "luigi.com",
                new GregorianCalendar( 2020,  7, 24,  17, 05));
        Reunion reunion_Room_C_Date_23_08 = new Reunion(System.currentTimeMillis(), 2, "Peach", "peach.com",
                new GregorianCalendar( 2020,  7, 23,  17, 05));

        addReunion(reunion_Room_A_Date_25_28);
        addReunion(reunion_Room_B_Date_24_08);
        addReunion(reunion_Room_C_Date_23_08);

        //SORT BY ROOM (alphabetique)
        clickMenu("Room");

        String labelReunion="Réunion ";
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(0, labelReunion+ convertIntRoomToStringRoom(reunion_Room_A_Date_25_28.getRoom())+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(1, labelReunion+ convertIntRoomToStringRoom(reunion_Room_B_Date_24_08.getRoom())+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(2, labelReunion+ convertIntRoomToStringRoom(reunion_Room_C_Date_23_08.getRoom())+separation));

        //SORT BY DATE
        clickMenu("Date");

        onView(withId(R.id.reunion_recycler)).check( withItemPosition(0, reunion_Room_C_Date_23_08.getFormattedDate()+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(1, reunion_Room_B_Date_24_08.getFormattedDate()+separation));
        onView(withId(R.id.reunion_recycler)).check( withItemPosition(2, reunion_Room_A_Date_25_28.getFormattedDate()+separation));
    }

    public void deleteReunionTest() {

        onView(withId(R.id.reunion_recycler)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.reunion_recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.reunion_recycler)).check(withItemCount(ITEMS_COUNT - 1));
    }


    // UTILS --
    public void clickMenu(String menu){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(menu)).perform(click());
    }

    /**
     * Go to CreateReunionActivity
     * */

    public void addReunion(Reunion reunion)
    {
        onView(withId(R.id.add_reunion)).perform(click());
        // CreateReunionActivity Should start
        onView(withId(R.id.save_create_reunion))
                .check(matches(not(isEnabled())));
        setSubjectAndMail(reunion.getSubject(), reunion.getAllParticipants());
        onView(withId(R.id.save_create_reunion))
                .check(matches(isEnabled()));
        setDatePicker(reunion.getCalendar());
        setTimePicker(reunion.getCalendar());
        setRoom(convertIntRoomToStringRoom(reunion.getRoom()));
        onView(withId(R.id.save_create_reunion))
                .perform(click());
    }

    public void setDatePicker(Calendar calendar)
    {
        onView(withId(R.id.dateButton)).perform(click());
        int month = calendar.get(Calendar.MONTH)+1;// 0 = janvier, pour PickerActions.setDate il faut 1 = janvier
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(calendar.get(Calendar.YEAR), month, calendar.get(Calendar.DAY_OF_MONTH)));
        onView(withText("OK")).perform(click());
    }
    public void setTimePicker(Calendar calendar)
    {
        onView(withId(R.id.timeButton)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)));
        onView(withText("OK")).perform(click());
    }
    
    public void setSubjectAndMail(String subject, String allMails){
        onView(withId(R.id.edit_text_subject))
                .perform(typeText(subject),closeSoftKeyboard());
        // onView(withId(R.id.edit_text_subject)).perform(ViewActions.pressKey(KeyEvent.KEYCODE_TAB));
        onView(withId(R.id.edit_text_all_mails))
                .perform(typeText(allMails), closeSoftKeyboard());
    }

    public void setRoom(String valSpinner){
        onView(withId(R.id.spinnerRoom)).perform(click());
        onView(withText(valSpinner)).perform(click());
    }

}