package com.example.mareu.ui.meeting;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    List<Meeting> meetings;
    Context context;

    public MyMeetingRecyclerViewAdapter(List<Meeting> items, Context context){
        this.meetings = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acttivity_meeting_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Meeting mMeeting = meetings.get(position);
        holder.mMeetingParticipant.setText(mMeeting.getParticipantMeeting());
        holder.mMeetingSubject.setText(mMeeting.getSubjectMeeting() + " - ");
        holder.mMeetingRoom.setText(mMeeting.getRoomMeeting());
        holder.mMeetingTimeStart.setText(mMeeting.getTimeStartMeeting()  + " - ");
        holder.mMeetingCircleColor.setColorFilter(ContextCompat.getColor(context, mMeeting.getColorMeeting()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new AlertDialog.Builder(v.getContext())
                        .setTitle(mMeeting.getSubjectMeeting() + " le "
                                + mMeeting.getDateDay() + " de "
                                + mMeeting.getTimeStartMeeting() +" à "
                                + mMeeting.getTimeEndMeeting()+" "
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

    public void updateData(List<Meeting> meetings){
        this.meetings = meetings;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() { return meetings.size(); }

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






