package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyMeetingApiService implements MeetingApiService  {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeeting();
    private List<String> rooms = DummyRoomMeetingGenerator.generateRooms();


    @Override
    public List<Meeting> getMeeting(){
        return meetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public List<String> generateRooms() { return rooms; }


}
