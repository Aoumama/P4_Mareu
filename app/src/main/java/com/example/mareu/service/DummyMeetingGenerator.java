package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting>DUMMY_MEETING = Arrays.asList(
            new Meeting(0, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "10H00", "10h45", "Réunion A", "Peach", ""),
            new Meeting(0, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "10H00", "10h45", "Réunion B", "Mario", ""),
            new Meeting(0, "paul@lamzone.com", "10H00", "10h45", "Réunion C", "Luigi", ""),
            new Meeting(0, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "10H00", "10h45", "Réunion A", "Peach", ""),
            new Meeting(0, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "10H00", "10h45", "Réunion B", "Luigi", ""));
    static List<Meeting>generateMeeting(){ return new ArrayList<>(DUMMY_MEETING); }


}
