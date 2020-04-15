package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyMeetingApiService implements MeetingApiService  {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeeting();
    private List<String> rooms = DummyRoomMeetingGenerator.generateRooms();
    private List<Meeting> listeR = new ArrayList<>();

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

    public List<Meeting> getRoomFilter(String room) {
        resetFilter();

        for (Meeting m : meetings) {
            if (m.getRoomMeeting().equals(room)) {
                listeR.add(m);
            }
        }
        return listeR;
    }

    @Override
    public void resetFilter() {
        listeR.clear();
    }

    @Override
    public List<Meeting> getMeetingsByDate(Date mDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        resetFilter();
        for (Meeting m : meetings) {
            if (m.getDateDay().equals(sdf.format(mDate))) {
               listeR.add(m);
            }
        }
        return listeR;
    }
}
