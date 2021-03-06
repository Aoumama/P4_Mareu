package com.example.mareu;

import com.example.mareu.DI.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.DummyMeetingGenerator;
import com.example.mareu.service.DummyRoomMeetingGenerator;
import com.example.mareu.service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Unit test Meeting
 */

@RunWith(JUnit4.class)
public class MeetingTest {

    private MeetingApiService service;

    @Before
    public void setup(){ service = DI.getNewInstanceApiService(); }

    /**
     * Display the meetings
     * **/
    @Test
    public void getMeetingWithSucces() {
        List<Meeting> meetings = service.getMeeting();
        List<Meeting> expectedMeeting = DummyMeetingGenerator.DUMMY_MEETING;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray()));
    }

    /**
     * Display meetingRooms
     * **/
    @Test
    public void getMeetingRoomSucces() {
        List<String> rooms = service.generateRooms();
        List<String> expectedRooms = DummyRoomMeetingGenerator.generateRooms();
        assertThat(rooms, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedRooms.toArray()));
    }

    /**
     * Delete meeting
     * **/
    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeeting().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeeting().contains(meetingToDelete));
    }

    /**
     * Add meeting
     * **/
    @Test
    public void addMeeting() {
        Meeting meetingToAdded = new Meeting(2, "nom@lamzone.fr", "10h00", "12h00", "11/10/2020", "Réunion C", service.generateRooms().get(0), R.color.colorPink );
        service.createMeeting(meetingToAdded);
        assertTrue(service.getMeeting().contains(meetingToAdded));
   }

    /**
     * Change the date to string
     */

    @Test
    public void changeDateToString() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 6);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.YEAR, 2020);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateS = sdf.format(date);
        assertEquals("06/04/2020", dateS);
    }

    /**
     * View a meeting by room
     * **/
    @Test
    public void getMeetingsFilterRoom() {
        String expectedMeetings = DummyMeetingGenerator.DUMMY_MEETING.get(4).getRoomMeeting();
        assertEquals(service.getRoomFilter("Mario").get(0).getRoomMeeting(), expectedMeetings);
    }

    /**
     * View a meeting by date
     * **/
    @Test
    public void getMeetingFilterDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 5);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.YEAR, 2020);
        Date date = calendar.getTime();
        String meetingDate = DummyMeetingGenerator.DUMMY_MEETING.get(2).getRoomMeeting();
        assertEquals(service.getMeetingsByDate(date).get(0).getRoomMeeting(), meetingDate);
    }


}