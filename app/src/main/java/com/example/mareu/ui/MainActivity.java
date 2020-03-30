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
import com.example.mareu.ui.create_reunion.CreateReunionActivity;
import com.example.mareu.ui.list_reunion.ReunionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/***
 * LANCER SUR EMULATOR X-HDPI (type Nexus 6) ou plus.
 * */
public class MainActivity extends AppCompatActivity {

    // DESIGN ---
    @BindView(R.id.add_reunion)
    FloatingActionButton add_reunion;

    // FRAGMENT RECYCLERVIEW ---
    ReunionFragment mFragmentRoom = ReunionFragment.newInstance(false);
    ReunionFragment mFragmentCalendar = ReunionFragment.newInstance(true);


    // OVERRIDE ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //AU lancement, on fait un trie par Room
        replaceFragment(mFragmentRoom);
        add_reunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateReunionActivity.navigate(MainActivity.this);
            }
        });
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
           case R.id.itemDateMenu:
               replaceFragment(mFragmentCalendar);
               break;
           case R.id.itemRoomMenu:
               replaceFragment(mFragmentRoom);
               break;
       }
        return super.onOptionsItemSelected(item);
    }

    // CONFIG ---

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.id_frame_fragment_reunion, fragment, fragment.getTag()).commit();
    }

}
