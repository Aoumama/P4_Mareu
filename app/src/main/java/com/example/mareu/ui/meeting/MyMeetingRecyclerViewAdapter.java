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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    List<Meeting> meeting;

    public MyMeetingRecyclerViewAdapter(List<Meeting> meeting){
        this.meeting = meeting;
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
        holder.mMeetingMail.setText(mMeeting.getParticipantMeeting());
        holder.mMeetingSubject.setText(mMeeting.getSubjectMeeting() + " -  ");
        holder.mMeetingPlace.setText(mMeeting.getRoomMeeting());
        holder.mMeetingTime.setText(mMeeting.getTimeStartMeeting()  + " -  ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new AlertDialog.Builder(v.getContext())
                        .setTitle(mMeeting.getSubjectMeeting() + " - "
                                + mMeeting.getTimeStartMeeting()+" - "
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
    public int getItemCount() {
        return meeting.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.activity_meeting_subject)
        public TextView mMeetingSubject;

        @BindView(R.id.activity_meeting_place)
        public TextView mMeetingPlace;

        @BindView(R.id.activity_meeting_mail)
        public TextView mMeetingMail;

        @BindView(R.id.activity_meeting_time)
        public TextView mMeetingTime;

        @BindView(R.id.activity_meeting_delete)
        public ImageView btnDelete;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }



}
