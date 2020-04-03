package com.example.mareu.ui;

import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.mareu.R;
import com.example.mareu.event.FilterCalendarEvent;
import com.example.mareu.event.FilterRoom;
import com.example.mareu.ui.create_reunion.CreateReunionActivity;
import com.example.mareu.ui.list_reunion.ReunionFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // DESIGN ---
    @BindView(R.id.add_reunion)
    FloatingActionButton add_reunion;

    boolean ROOM = false;
    boolean CALENDAR = true;

    // FRAGMENT RECYCLERVIEW ---
    public ReunionFragment mFragment = ReunionFragment.newInstance(ROOM, ReunionFragment.SORTER_SIMPLE);


    // OVERRIDE ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //AU lancement, on fait un trie par Room
        replaceFragment(mFragment);

        configAddButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.itemSorterDate:
                mFragment = ReunionFragment.newInstance(CALENDAR, ReunionFragment.SORTER_SIMPLE);
               replaceFragment(mFragment);

               break;
           case R.id.itemSorterRoom:
               mFragment = ReunionFragment.newInstance(ROOM, ReunionFragment.SORTER_SIMPLE);
               replaceFragment(mFragment);

               break;
                case R.id.itemFilterDate:
                    MyDialog.setBUILDER(MyDialog.BUILDER_START_AND_END);
                    MyDialog.showAddTaskDialog(this);
               break;
            case R.id.itemFilterRoom:
                MyDialog.setBUILDER(MyDialog.BUILDER_ROOM);
                MyDialog.showAddTaskDialog(this);
               break;

       }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // CONFIG ---
    public void configAddButton(){
        add_reunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateReunionActivity.navigate(MainActivity.this);
            }
        });
    }
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.id_frame_fragment_reunion, fragment, fragment.getTag()).commit();
    }


    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onGetFilterRoomEvent(FilterRoom event){
         mFragment = ReunionFragment.newInstance(ROOM, ReunionFragment.FILTER_ROOM, event.getSearchRoom());
        replaceFragment(mFragment);
    }
     /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onGetFilterCalendarEvent(FilterCalendarEvent event){
         mFragment = ReunionFragment.newInstance(CALENDAR, ReunionFragment.FILTER_CALENDAR, event.getStart(), event.getEnd());
        replaceFragment(mFragment);
    }



}
