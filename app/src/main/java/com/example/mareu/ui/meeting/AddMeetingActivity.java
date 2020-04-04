package com.example.mareu.ui.meeting;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.mareu.DI.DI;
import com.example.mareu.R;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMeetingActivity extends AppCompatActivity {

    @BindView(R.id.activity_meeting_add_lytparticipant)
    TextInputLayout participantMeeting;

    @BindView(R.id.activity_meeting_add_lytsubject)
    TextInputLayout subjectMeeting;

    @BindView(R.id.activity_meeting_add_circleColor)
    ImageView colorMeeting;

    @BindView(R.id.activity_meeting_add_spinnerromm)
    Spinner roomMeeting;

    @BindView(R.id.activity_meeting_add_btntimeend)
    EditText timeendMeeting;

    @BindView(R.id.activity_meeting_add_btntimestart)
    EditText timestartMeeting;

    @BindView(R.id.activity_meeting_add_day)
    EditText dateMeeting;

    @BindView(R.id.activity_meeting_add_btnvalidate)
    Button validateMeeting;

    private MeetingApiService mApiService;
    private List<String> mApiServiceRoom;
    private int colorSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_add);
        ButterKnife.bind(this);

        mApiService = DI.getMeetingApiService();
        init();

        ImageView btnReturn = findViewById(R.id.activity_meeting_add_return);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMeetingActivity.this.finish();
            }
        });

        // SPINNER
        mApiServiceRoom = DI.getNewInstanceApiService().generateRooms();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mApiServiceRoom);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomMeeting.setAdapter(arrayAdapter);
        roomMeeting.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roomMeeting.setSelection(position);
                switch (position){
                    case 0 : colorSelected = R.color.colorTeal; break;
                    case 1 : colorSelected = R.color.colorRed; break;
                    case 2 : colorSelected = R.color.colorPink; break;
                    case 3 : colorSelected = R.color.colorPurple; break;
                    case 4 : colorSelected = R.color.colorPrimaryDark; break;
                    case 5 : colorSelected = R.color.colorOrange; break;
                    case 6 : colorSelected = R.color.colorIndigo; break;
                    case 7 : colorSelected = R.color.colorGrey; break;
                    case 8 : colorSelected = R.color.colorBrown; break;
                    case 9 : colorSelected = R.color.colorAmber; break;
                    default: onItemSelected(parent, view, position, id);
                }
                colorMeeting.setColorFilter(ContextCompat.getColor(view.getContext(), colorSelected));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        // FIN SPINNER

        timestartMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeZone fuseauH = TimeZone.getTimeZone("Europe/Paris");
                Calendar mcurrentTime = Calendar.getInstance(fuseauH);
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timestartMeeting.setText(String.format("%02d", selectedHour) + "h" + String.format("%02d", selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Séléctionner l'heure");
                mTimePicker.show();
            }
        });
        timeendMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeZone fuseauH = TimeZone.getTimeZone("Europe/Paris");
                Calendar mcurrentTime = Calendar.getInstance(fuseauH);
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeendMeeting.setText(String.format("%02d", selectedHour) + "h" + String.format("%02d", selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Séléctionner l'heure");
                mTimePicker.show();
            }
        });
        dateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialogDate = new DatePickerDialog(AddMeetingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        dateMeeting.setText(String.format("%02d", dayOfMonth)
                                 + "/" + String.format("%02d", month) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                dialogDate.getDatePicker().setMinDate(System.currentTimeMillis());
                dialogDate.setTitle("Séléctionner le jour de la réunion");
                dialogDate.show();

            }
        });

        validateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(participantMeeting.getEditText().getText())) {
                    Toast.makeText(v.getContext(), "Veuillez renseigner les participants", Toast.LENGTH_SHORT).show(); return;
                }

                if(TextUtils.isEmpty(subjectMeeting.getEditText().getText())) {
                    Toast.makeText(v.getContext(), "Veuillez renseigner le sujet de la réunion", Toast.LENGTH_SHORT).show(); return;
                }

                if(TextUtils.isEmpty(roomMeeting.getSelectedItem().toString())) {
                    Toast.makeText(v.getContext(), "Veuillez séléctionner la salle de réunion", Toast.LENGTH_SHORT).show(); return;
                }

                if(TextUtils.isEmpty(timestartMeeting.getText())) {
                    Toast.makeText(v.getContext(), "Veuillez renseigner l'heure de début", Toast.LENGTH_SHORT).show(); return;
                }

                if(TextUtils.isEmpty(timeendMeeting.getText())) {
                    Toast.makeText(v.getContext(), "Veillez renseigner l'heure de fin", Toast.LENGTH_SHORT).show(); return;
                }

                if(TextUtils.isEmpty(timestartMeeting.getText()) == TextUtils.isEmpty(timeendMeeting.getText())){
                    Toast.makeText(v.getContext(), "L'heure de fin de la réunion ne peut être égale à l'heure du début", Toast.LENGTH_SHORT).show(); return;
                }

                addMeeting();
            }
        });

        init();
    }

    private void init(){
        participantMeeting.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                validateMeeting.setEnabled(s.length() > 0);
            }
        });
    }

    private void addMeeting() {
        Meeting meeting = new Meeting(
            System.currentTimeMillis(),
            participantMeeting.getEditText().getText().toString(),
            timestartMeeting.getText().toString(),
            timeendMeeting.getText().toString(),
            dateMeeting.getText().toString(),
            subjectMeeting.getEditText().getText().toString(),
            roomMeeting.getSelectedItem().toString(),
            colorSelected
        );
        mApiService.createMeeting(meeting);
        finish();
    }

    public static void navigate (FragmentActivity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

}
