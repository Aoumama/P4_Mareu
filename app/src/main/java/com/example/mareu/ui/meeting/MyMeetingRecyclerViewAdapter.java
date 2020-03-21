package com.example.mareu.ui.meeting;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    List<Meeting> meeting;
    public static boolean roomInFilterList = false;
    private static List<Meeting> filterList = new ArrayList<>();


    public MyMeetingRecyclerViewAdapter(List<Meeting> items){
        this.meeting = items;
        //On vide la liste filterList
        filterList.clear();

        //Si un filtre est déjà activé on le supprime pour les prochains filtres
        if (roomInFilterList) {
            roomInFilterList = false;
        }

        /* si un filtre est activé, on rempli la liste filterList avec les meetings correspondants */
        for (Meeting m : items) {
            if (m.isMeetingInFilterList()) {
                filterList.add(m);
                roomInFilterList = true;
            }
        }

        if (roomInFilterList) {
            meeting = filterList;
        } else meeting = items;
   }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acttivity_meeting_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Meeting mMeeting = meeting.get(position);
        holder.mMeetingParticipant.setText(mMeeting.getParticipantMeeting());
        holder.mMeetingSubject.setText(mMeeting.getSubjectMeeting() + " -  ");
        holder.mMeetingRoom.setText(mMeeting.getRoomMeeting());
        holder.mMeetingTimeStart.setText(mMeeting.getTimeStartMeeting()  + " -  ");
        holder.mMeetingCircleColor.setColorFilter(mMeeting.getColorMeeting());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new AlertDialog.Builder(v.getContext())
                        .setTitle(mMeeting.getSubjectMeeting() + " - "
                                + mMeeting.getTimeStartMeeting() +" - "
                                + mMeeting.getTimeEndMeeting()+" à "
                                + mMeeting.getRoomMeeting())
                        .setMessage(mMeeting.getParticipantMeeting()).show();
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(mMeeting));
            }
        });
    }


    @Override
    public int getItemCount() { return meeting.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.activity_meeting_subject)
        public TextView mMeetingSubject;

        @BindView(R.id.activity_meeting_room)
        public TextView mMeetingRoom;

        @BindView(R.id.activity_meeting_participant)
        public TextView mMeetingParticipant;

        @BindView(R.id.activity_meeting_timeStart)
        public TextView mMeetingTimeStart;

        @BindView(R.id.activity_meeting_delete)
        public ImageView btnDelete;

        @BindView(R.id.activity_meeting_add_circleColor)
        public ImageView mMeetingCircleColor;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



}
