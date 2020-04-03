package com.example.mareu.ui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AndroidException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mareu.R;
import com.example.mareu.event.FilterRoom;
import com.example.mareu.event.FilterCalendarEvent;
import com.example.mareu.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

public class MyDialog {

    @Nullable
    private static AlertDialog dialog = null;

    @Nullable
    private static Spinner dialogSpinner = null;


    @Nullable
    private static Calendar start = Calendar.getInstance();
    @Nullable
    private static Calendar end = Calendar.getInstance();

    @Nullable
    private static Button buttonStart = null;

    @Nullable
    private static Button buttonEnd = null;

    @Nullable
    private static TextView textViewStart = null;

     @Nullable
    private static TextView textViewEnd = null;
      @Nullable
    private static TextView textViewError = null;
 @Nullable
    private static  DatePickerDialog datePickerStart = null;
 @Nullable
    private static  DatePickerDialog datePickerEnd = null;
 @Nullable
    private static  int mIntRoom;


    public static final boolean BUILDER_ROOM = true;
    public static final boolean BUILDER_START_AND_END = false;
    private static boolean BUILDER =  BUILDER_ROOM;

    private static final boolean START = true;
    private static final boolean END = false;


    private static AlertDialog.Builder getBuilderDialogRoom(Context context){
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context, R.style.Dialog);
        alertBuilder.setTitle("Room");
        alertBuilder.setView(R.layout.layout_dialog_room);
        alertBuilder.setPositiveButton("Search", null);
        alertBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dialogSpinner = null;
                dialog = null;
            }
        });
        return alertBuilder;
    }

    private static AlertDialog.Builder getBuilderDialogStarAndEnd(Context context){
        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context, R.style.Dialog);
        alertBuilder.setTitle("Set Start and End.");
        alertBuilder.setView(R.layout.layout_dialog_calendar);
        alertBuilder.setPositiveButton("Search", null);
        alertBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
               // dialogSpinner = null;
                dialog = null;
            }
        });
        return alertBuilder;
    }

    @NonNull
    private static AlertDialog getDialog(Context context) {

        if(BUILDER == BUILDER_ROOM){
            dialog = getBuilderDialogRoom(context).create();
        }
        else{
            dialog = getBuilderDialogStarAndEnd(context).create();
        }

        // This instead of listener to positive button in order to avoid automatic dismiss
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(BUILDER == BUILDER_ROOM){
                            onPositiveButtonClickForRoom( dialog);
                        }
                        else{
                            onPositiveButtonClickForStartAndEnd( dialog);
                        }
                    }
                });
            }
        });
        return dialog;
    }

    /**
     *
     *
     * @param dialogInterface the current displayed dialog
     */
    private static void onPositiveButtonClickForRoom(DialogInterface dialogInterface){
        if ( dialogSpinner != null) {
            int searchRoom = Utils.convertStringRoomToInt(dialogSpinner.getSelectedItem().toString());
            EventBus.getDefault().post(new FilterRoom(searchRoom));
        }
        // If dialog is already closed
            cleanDialogRoom(dialogInterface);
    }
    private static void onPositiveButtonClickForStartAndEnd(DialogInterface dialogInterface){
        if ( start != null && end != null) {
            EventBus.getDefault().post(new FilterCalendarEvent(start, end));
        }
        // If dialog is already closed
        cleanDialogCalendar(dialogInterface);
    }
    private static void cleanDialogRoom(DialogInterface dialogInterface){
        //dialogSpinner = null;
        dialogInterface.dismiss();
    }
    private static void cleanDialogCalendar(DialogInterface dialogInterface){
       textViewError = null;
       textViewStart = null;
       textViewEnd = null;
       buttonStart = null;
       buttonEnd = null;
       dialogInterface.dismiss();
    }

    /**
     * Shows the Dialog for adding a Task
     */
    public static void showAddTaskDialog(Context context) {
        final AlertDialog dialog = getDialog(context);
        dialog.show();
        if(BUILDER == BUILDER_ROOM){
            dialogSpinner = dialog.findViewById(R.id.spinnerRoom);
            populateDialogSpinner(context);
        }
        else if(BUILDER == BUILDER_START_AND_END){
            buttonStart = dialog.findViewById(R.id.start_button);
            buttonEnd = dialog.findViewById(R.id.end_button);
            textViewError = dialog.findViewById(R.id.error_textview);
            configDateViewStart();
            configDateViewEnd();
            configButtonsStartAndEnd(context);
        }
    }

    private static void populateDialogSpinner(Context context) {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.rooms, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        if (dialogSpinner != null) {
            dialogSpinner.setAdapter(adapter);
        }
    }

    private static void configButtonsStartAndEnd(final Context context) {
      setOnclickListenerButton(context, buttonStart, START);
      setOnclickListenerButton(context, buttonEnd, END);
    }

  private static void setOnclickListenerButton(final Context context , Button button, final boolean whatDate) {

          button.setOnClickListener(new View.OnClickListener() {
            Calendar calendarPicker = Calendar.getInstance();
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int pickYear, int pickMonth, int pickDay) {
                        calendarPicker.set(Calendar.DAY_OF_MONTH, pickDay);
                        calendarPicker.set(Calendar.MONTH, pickMonth);
                        calendarPicker.set(Calendar.YEAR, pickYear);
                        if(whatDate == START){
                            start = calendarPicker;
                            textViewError.setText("");
                            //if start > end, set start to end
                            if(start.compareTo(end)>0) {
                                end = start;
                                configDateViewEnd();
                                textViewError.setText(R.string.error_dialog_calendar_change_end);
                            }
                            configDateViewStart();
                        }
                        else if(whatDate == END){
                            end = calendarPicker;
                            textViewError.setText("");
                            //if start > end, set end to start
                            if(start.compareTo(end)>0) {
                                start = end;
                                textViewError.setText(R.string.error_dialog_calendar_change_start);
                                configDateViewStart();
                            }
                            configDateViewEnd();
                        }
                    }
                }, calendarPicker.get(Calendar.DAY_OF_MONTH), calendarPicker.get(Calendar.MONTH), calendarPicker.get(Calendar.YEAR));
                if(whatDate == START){
                    datePickerDialog.updateDate(start.get(Calendar.YEAR),start.get(Calendar.MONTH), start.get(Calendar.DAY_OF_MONTH));
                }
                else if(whatDate == END){
                    datePickerDialog.updateDate(end.get(Calendar.YEAR),end.get(Calendar.MONTH), end.get(Calendar.DAY_OF_MONTH));
                }
                datePickerDialog.show();

            }//end override
        });
    }
    private static void configDateViewStart( ){
        textViewStart = dialog.findViewById(R.id.start_textview_pick);
        textViewStart.setText(Utils.FORMAT_DATE.format(start.getTime()));
    }
    private static void configDateViewEnd( ){
        textViewEnd = dialog.findViewById(R.id.end_textview_pick);
        textViewEnd.setText(Utils.FORMAT_DATE.format(end.getTime()));
    }

    public static void setBUILDER(boolean builder){
        BUILDER = builder;
    }
}
