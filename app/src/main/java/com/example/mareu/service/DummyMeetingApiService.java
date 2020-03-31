package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import org.joda.time.DateTime;

import java.util.ArrayList;
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
    public List<Meeting> getMeetingsByDate(DateTime mDate) {
//        resetFilter();
//        for (Meeting m : meetings) {
//            if (m.getTimeStartMeeting().equals(mDate.toLocalDate())) {
//                m.setMeetingInFilterList(true);
//                listeR.add(m);
//            }
//        }
//        return listeR;
//    }
}
