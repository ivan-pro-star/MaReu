package com.example.mareu.ui.create_reunion;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import com.example.mareu.R;
import com.example.mareu.model.Reunion;
import com.example.mareu.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PickerFragment extends Fragment {

    // DESIGN ---
    @BindView(R.id.date_textview_pick)
    TextView mTextViewDate;
    @BindView(R.id.dateButton)
    ImageButton mButtonDate;
    @BindView(R.id.time_text_view_pick)
    TextView mTextViewTime;
    @BindView(R.id.timeButton)
    ImageButton mButtonTime;

    // CALENDAR ---
    private Calendar  mCalendar = Calendar.getInstance();

    // SIMPLEDATE ---
    SimpleDateFormat mFormat_date;
    SimpleDateFormat mFormat_time;

    // OVERRIDE ---
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mCalendar.setTimeZone(TimeZone.getTimeZone("Indian/Reunion"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_picker, container, false);
        ButterKnife.bind(this, view);
        configDateView();
        configTimeView();
        setOnClickListener();
        return view;
    }

    // CONFIG ---
    public void configDateView(){
        mTextViewDate.setText(Utils.FORMAT_DATE.format(mCalendar.getTime()));
    }
     public void configTimeView(){
        mTextViewTime.setText(Utils.FORMAT_TIME.format(mCalendar.getTime()));
    }


    /**DATEPICKER CLICK ON mButtonDate*/
    private void setOnClickListener() {
        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int pickYear, int pickMonth, int pickDay) {
                        mCalendar.set(Calendar.DAY_OF_MONTH, pickDay);
                        mCalendar.set(Calendar.MONTH, pickMonth);
                        mCalendar.set(Calendar.YEAR, pickYear);
                       configDateView();
                    }
                }, mCalendar.get(Calendar.DAY_OF_MONTH), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.YEAR));
                datePicker.updateDate(mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH));
                datePicker.show();
            }//end override
        });
        /**TIMEPICKER Click on mButtonTime*/
        mButtonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                TimePickerDialog timePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int pickHour, int pickMinute) {

                        mCalendar.set(Calendar.HOUR_OF_DAY, pickHour);
                        mCalendar.set(Calendar.MINUTE, pickMinute);
                       configTimeView();
                    }
                }   , mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), true);
                timePicker.updateTime(mCalendar.get(Calendar.HOUR_OF_DAY),mCalendar.get(Calendar.MINUTE));
                timePicker.show();
            }
        });
    }

    /**Extern Get calendar*/
    public Calendar getCalendar(){
        return mCalendar;
    }
}
