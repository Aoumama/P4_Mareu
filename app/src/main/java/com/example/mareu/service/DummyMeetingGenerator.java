package com.example.mareu.service;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting>DUMMY_MEETING = Arrays.asList(
            new Meeting(0, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "12H30", "13H00", "31/03/2020", "Réunion B", "Peach", R.color.colorTeal),
            new Meeting(1, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "12H30", "13H00", "30/03/2020", "Réunion A", "Informatique", R.color.colorPrimaryDark),
            new Meeting(2, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "12H30", "13H00", "05/04/2020", "Réunion C", "Mario", R.color.colorRed),
            new Meeting(3, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "12H30", "13H00", "01/04/2020", "Réunion A", "Luigi", R.color.colorPink),
            new Meeting(4, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "12H30", "13H00", "01/04/2020", "Réunion D", "Mario", R.color.colorRed),
            new Meeting(5, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "12H30", "13H00", "31/03/2020", "Réunion Z", "Luigi", R.color.colorOrange),
            new Meeting(5, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "12H30", "13H00", "15/04/2020", "Réunion Z", "Luigi", R.color.colorOrange),
            new Meeting(6, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "12H30", "13H00", "01/04/2020", "Réunion S", "Luigi", R.color.colorPink));

    static List<Meeting>generateMeeting(){ return new ArrayList<>(DUMMY_MEETING); }


}
