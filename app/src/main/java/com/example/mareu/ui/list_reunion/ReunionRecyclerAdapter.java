package com.example.mareu.ui.list_reunion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mareu.R;
import com.example.mareu.model.Reunion;
import com.example.mareu.utils.DeleteReunionEvent;
import com.example.mareu.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReunionRecyclerAdapter extends RecyclerView.Adapter<ReunionRecyclerAdapter.ViewHolder>{

    // DATA---
    List<Reunion> mReunions;

    //CONFIG FOR SORTER ---
    boolean mIsSortByDate = false;

    //CALENDAR ---
    Calendar mToday = Calendar.getInstance();

    /**
     * Constructor
     * */
    public ReunionRecyclerAdapter(List<Reunion> reunions, boolean sortByDate) {
        mReunions = reunions;
        mIsSortByDate = sortByDate;
    }
    // OVERRIDE---
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_reunion,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.display(mReunions.get(i));
    }

    @Override
    public int getItemCount() {
        return mReunions.size();
    }

    /**
     *  Class ViewHolder
     * */
    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.id_item_first)
        TextView mFistItem;
        @BindView(R.id.id_item_hour_reunion)
        TextView mHour;
        @BindView(R.id.id_item_subject_reunion)
        TextView mSubject;
        @BindView(R.id.id_item_image_reunion)
        ImageView mImage;
        @BindView(R.id.id_item_delete_button_reunion)
        ImageView mDeleteButton;
        @BindView(R.id.id_item_all_mails)
        TextView  mAllMails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        // DISPLAY ----
        public void display(final Reunion reunion)
        {
            displayFirstItem(reunion);
            displayImage(reunion);
            displayHour(reunion);
            mSubject.setText(reunion.getSubject());
            mAllMails.setText(reunion.getAllParticipants());

            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().post(new DeleteReunionEvent(reunion));
                }
           });
        }
        public void displayHour(Reunion reunion){
            String separation = " - ";
            mHour.setText(reunion.getFormattedHour()+separation);
        }
        public void displayFirstItem(Reunion reunion){
            String first_item_string;
            if(mIsSortByDate){
                first_item_string = reunion.getFormattedDate();
            }
            else{
                String labelReunion ="Réunion ";
                first_item_string = labelReunion+(Utils.convertIntRoomToStringRoom(reunion.getRoom()));
            }
            String separation = " - ";
            mFistItem.setText(first_item_string+separation);
        }
        public void displayImage(Reunion reunion){
            if(reunion.getCalendar().getTime().compareTo(mToday.getTime())>0) {
                mImage.setImageResource(R.mipmap.not_yet);
            }
            else{
                mImage.setImageResource(R.mipmap.finish);
            }
        }


    }//end Class ViewHolder
}

