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
import com.example.mareu.utils.DeleteReunionEvent;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    ApiService mService = DI.getApiService();
    //BOOLEAN CONFIG FOR SORTER ---
    boolean isSorterByCalendar ;


    /**
     * Create an instance of ReunionFragment
    * */
    public static ReunionFragment newInstance(boolean isSorterByCalendar) {
        ReunionFragment fragment = new ReunionFragment();
        fragment.isSorterByCalendar = isSorterByCalendar;
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
        //mRecyclerReunion.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
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
        if(isSorterByCalendar){
            reunions = mService.getReunionsByCalendar();
        }
        else{
            reunions = mService.getReunionsByRoom();
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
        mService.deleteReunion(event.reunion);
        initList();
    }
}
