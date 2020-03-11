package com.example.mareu.ui.meeting;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.DI.DI;
import com.example.mareu.R;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeetingActivity extends AppCompatActivity {

    private MeetingApiService mApiService;
    private RecyclerView.Adapter mMeetingAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        ButterKnife.bind(this);

        mApiService = DI.getMeetingApiService();

        mRecyclerView = findViewById(R.id.activity_meeting_recyclerview_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMeetingAdapter = new MyMeetingRecyclerViewAdapter(mApiService.getMeeting());
        mRecyclerView.setAdapter(mMeetingAdapter);
        mMeetingAdapter.notifyDataSetChanged();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

    }

    private void initList(List<Meeting> meetings){
        mMeetingAdapter = new MyMeetingRecyclerViewAdapter(meetings);
        mRecyclerView.setAdapter(mMeetingAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

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

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mApiService.deleteMeeting(event.meeting);
        mMeetingAdapter.notifyDataSetChanged();

    }

    @OnClick(R.id.activity_meeting_add)
    void addMeeting(){AddMeetingActivity.navigate(this);}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


}
