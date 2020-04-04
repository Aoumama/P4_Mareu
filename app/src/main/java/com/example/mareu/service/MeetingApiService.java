package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.Date;
import java.util.List;

public interface MeetingApiService {

    /**
     * Get all my Meeting
     * @return {@link List}
     */
    List<Meeting> getMeeting();

    /**
     * Create a meeting
     * @param meeting
     */
    void createMeeting(Meeting meeting);

    /**
     * Deletes a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    List<String> generateRooms();

    /**
     * Get all Meetings in order of Rooms
     */
    List<Meeting> getRoomFilter(String room);

    List<Meeting> getMeetingsByDate(Date mDate);


    void resetFilter();



}
