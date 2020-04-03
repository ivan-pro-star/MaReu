package com.example.mareu.ui.list_reunion;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.ApiService;
import com.example.mareu.event.DeleteReunionEvent;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReunionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReunionFragment extends Fragment {

    //RECYCLER ---
    RecyclerView mRecyclerReunion;
    // SERVICE FOR GET DATA ---
    public  ApiService mService = DI.getApiService();
    //BOOLEAN CONFIG FOR SORTER ---
    boolean isSorterByCalendar ;

    //int pour type getter

    //init typeGetter a zero, simple sorter
    private int typeGetter = 0 ;

    public static  final int SORTER_SIMPLE = 0;
    public static  final int FILTER_ROOM = 1;
    public static  final int FILTER_CALENDAR = 2;

    private int mRoom;
    private Calendar mAfter;
    private Calendar mBefore;

    /**
     * Create an instance of ReunionFragment
    * */
    public static ReunionFragment newInstance(boolean isSorterByCalendar, int typeGetter) {
        ReunionFragment fragment = new ReunionFragment();
        fragment.isSorterByCalendar = isSorterByCalendar;
        fragment.typeGetter = typeGetter;
        return fragment;
    }
    public static ReunionFragment newInstance(boolean isSorterByCalendar, int typeGetter, int room) {
        ReunionFragment fragment = new ReunionFragment();
        fragment.isSorterByCalendar = isSorterByCalendar;
        fragment.typeGetter = typeGetter;
        fragment.mRoom = room;
        return fragment;
    }
    public static ReunionFragment newInstance(boolean isSorterByCalendar, int typeGetter, Calendar start, Calendar end) {
        ReunionFragment fragment = new ReunionFragment();
        fragment.isSorterByCalendar = isSorterByCalendar;
        fragment.typeGetter = typeGetter;
        fragment.mBefore = start;
        fragment.mAfter = end;
        return fragment;
    }

    // OVERRIDE ---
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reunion, container, false);
        mRecyclerReunion = (RecyclerView) view;
        mRecyclerReunion.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return  view;
    }
    @Override
    public void onResume() {
        super.onResume();
        initList();
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

    // DATA ---
    private void initList() {
        List<Reunion> reunions;
        switch(typeGetter){
            case FILTER_ROOM:
                reunions = mService.getReunions(mRoom);
                break;
            case FILTER_CALENDAR:
                reunions = mService.getReunions(mBefore, mAfter);
                break;
            default:
                reunions = mService.getReunions(isSorterByCalendar);
        }

        //SET ADAPTER
        ReunionRecyclerAdapter adapter = new ReunionRecyclerAdapter(reunions, isSorterByCalendar);
        mRecyclerReunion.setAdapter(adapter);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteReunionEvent event) {
        mService.deleteReunion(event.getReunion());
        initList();
    }
}
