package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting>DUMMY_MEETING = Arrays.asList(
            new Meeting(0, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "10H00", "10h45", "Réunion A", "Peach", 0),
            new Meeting(1, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "10H00", "10h45", "Réunion B", "Mario", 1),
            new Meeting(2, "paul@lamzone.com", "10H00", "10h45", "Réunion C", "Luigi", 4),
            new Meeting(3, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "10H00", "10h45", "Réunion A", "Peach", 2),
            new Meeting(4, "paul@lamzone.com, laura@lamzone.com, clara@lamzone.com ", "10H00", "10h45", "Réunion B", "Luigi", 3));
    static List<Meeting>generateMeeting(){ return new ArrayList<>(DUMMY_MEETING); }


}
