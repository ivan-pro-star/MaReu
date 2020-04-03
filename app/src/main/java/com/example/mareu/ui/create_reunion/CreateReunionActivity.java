package com.example.mareu.ui.create_reunion;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;
import com.example.mareu.utils.Utils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateReunionActivity extends AppCompatActivity {

    // SERVICE---
    ApiService mService = DI.getApiService();

    // DESIGN ---
    @BindView(R.id.edit_text_all_mails)
    TextInputEditText  edit_text_all_mails;
    @BindView(R.id.edit_text_subject)
    TextInputEditText  edit_text_subject ;
    @BindView(R.id.save_create_reunion)
    Button save_create_reunion ;

    // ROOM CONVERTED IN INT
    int mIntRoom = 0;

    // PICKERFRAGMENT ---
    PickerFragment mPickerFragment;

    // OVERRIDE ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reunion);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        configSpinnerAdapter();
        configPicker();
        
        configEnableSaveButton();
        configListenerSave_button();
    }

    // CONFIG ---
    public void configPicker() {
        mPickerFragment = new PickerFragment();
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_picker,mPickerFragment,mPickerFragment.getTag()).commit();
    }

    public void configSpinnerAdapter(){
        Spinner spinnerRoom = findViewById(R.id.spinnerRoom);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rooms, R.layout.my_spinner);
        adapter.setDropDownViewResource(R.layout.my_spinner);
        spinnerRoom.setAdapter(adapter);

        spinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mIntRoom = Utils.convertStringRoomToInt(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mIntRoom = 0;
            }
        });
    }

    public void configListenerSave_button(){
        save_create_reunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Calendar calendar = mPickerFragment.getCalendar();
            Reunion reunion = new Reunion(System.currentTimeMillis(), mIntRoom, edit_text_subject.getText().toString(),
                    edit_text_all_mails.getText().toString(), calendar);
            mService.createReunion(reunion);
            onBackPressed();
            }
        });
    }

    public void configEnableSaveButton(){
        TextWatcher reunionTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String subject = edit_text_subject.getText().toString();
                String all_mails = edit_text_all_mails.getText().toString();
                save_create_reunion.setEnabled(!subject.isEmpty() && !all_mails.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        edit_text_all_mails.addTextChangedListener(reunionTextWatcher);
        edit_text_subject.addTextChangedListener(reunionTextWatcher);
    }

    /**EXTERN*/
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, CreateReunionActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}