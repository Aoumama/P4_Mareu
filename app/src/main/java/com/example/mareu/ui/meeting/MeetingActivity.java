package com.example.mareu.ui.meeting;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeetingActivity extends AppCompatActivity {

    private MeetingApiService mApiService;
    private MyMeetingRecyclerViewAdapter mMeetingAdapter;
    private RecyclerView mRecyclerView;
    private List<Meeting> mMeeting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        ButterKnife.bind(this);

        mApiService = DI.getMeetingApiService();

        mRecyclerView = findViewById(R.id.activity_meeting_recyclerview_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMeetingAdapter = new MyMeetingRecyclerViewAdapter(mApiService.getMeeting(),this);
        mRecyclerView.setAdapter(mMeetingAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

    }


    private void updateList(List<Meeting> meetings){
        mMeetingAdapter.updateData(meetings);
    }


    @Override
    public void onResume() {
        super.onResume();
        mMeetingAdapter.notifyDataSetChanged();
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_menu_dated :
                showDate();
                return true;

            case R.id.main_menu_menusalle_apple :
                filterRoom("Apple");
                return true;

            case R.id.main_menu_menusalle_cat :
                filterRoom("Cat");
                return true;

            case R.id.main_menu_menusalle_luigi :
                filterRoom("Luigi");
                return true;

            case R.id.main_menu_menusalle_mario :
                filterRoom("Mario");
                return true;

            case R.id.main_menu_menusalle_window :
                filterRoom("Window");
                return true;

            case R.id.main_menu_menusalle_sonic :
                filterRoom("Sonic");
                return true;

            case R.id.main_menu_menusalle_ball :
                filterRoom("Ball");
                return true;

            case R.id.main_menu_menusalle_greta :
                filterRoom("Greta");
                return true;

            case R.id.main_menu_menusalle_informatique :
                filterRoom("Informatique");
                return true;

            case R.id.main_menu_menusalle_peach :
                filterRoom("Peach");
                return true;

            case R.id.main_menu_allroom:
                updateList(mApiService.getMeeting());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void filterRoom(String room) {
        boolean nothing = true;
        for (Meeting m : mApiService.getMeeting()) {
            if (m.getRoomMeeting().equals(room)) {
                nothing = false;
                break;
            }
        }
        if (!nothing) {
            updateList(mApiService.getRoomFilter(room));
        } else {
            Toast.makeText(this, "Pas de réunion dans cette salle", Toast.LENGTH_LONG).show();
        }
    }

    private void showDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialogDate = new DatePickerDialog(this, generateDatePickerDialog(), year, month, day);
        dialogDate.getDatePicker().setMinDate(System.currentTimeMillis());
        dialogDate.show();
    }

    private DatePickerDialog.OnDateSetListener generateDatePickerDialog() {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Date date = calendar.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                boolean nothing = true;

                for (Meeting m : mApiService.getMeeting()) {
                    if (m.getDateDay().equals(sdf.format(date))) {
                        nothing = false;
                        break;
                    }
                }

                if (!nothing) {
                    updateList(mApiService.getMeetingsByDate(date));
                    Toast.makeText(view.getContext(), "Réunion prévue à cette date", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(view.getContext(), "Aucune réunion prévue à cette date", Toast.LENGTH_LONG).show();
                }
            }
        };
    }

}
