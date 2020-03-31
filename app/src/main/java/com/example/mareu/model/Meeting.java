package com.example.mareu.model;

import org.joda.time.DateTime;

public class Meeting {
    /**
     * Model object representing a Meeting
     */

    private long id;
    private String participantMeeting;
    private String timeStartMeeting;
    private String timeEndMeeting;
    private String dateDay;
    private String subjectMeeting;
    private String roomMeeting;
    private int colorMeeting;

    private boolean roomInFilterList;

    private DateTime dateFilter;

    /**
     * Constructor
     * @param id
     * @param participantMeeting
     * @param timeStartMeeting
     * @param timeEndMeeting
     * @param dateDay
     * @param subjectMeeting
     * @param roomMeeting
     * @param colorMeeting

     */

    public Meeting(long id, String participantMeeting, String timeStartMeeting, String timeEndMeeting, String dateDay, String subjectMeeting, String roomMeeting, int colorMeeting ){
        this.id = id;
        this.participantMeeting = participantMeeting;
        this.subjectMeeting = subjectMeeting;
        this.timeStartMeeting = timeStartMeeting;
        this.timeEndMeeting = timeEndMeeting;
        this.roomMeeting = roomMeeting;
        this.colorMeeting = colorMeeting;
        this.dateDay = dateDay;

        this.dateFilter = dateFilter;
    }




    public long getId(){ return id; }
    public void setId(int id){ this.id = id; }

    public String getParticipantMeeting(){ return participantMeeting; }
    public String setParticipantMeeting(String participantMeeting){ return this.participantMeeting = participantMeeting; }

    public String getSubjectMeeting(){
        return subjectMeeting;
    }
    public void setSubjectMeeting(String subjectMeeting){
        this.subjectMeeting = subjectMeeting;
    }


    public String getTimeStartMeeting(){
        return timeStartMeeting;
    }
    public void setTimeStartMeeting(String timeStartMeeting){ this.timeStartMeeting = timeStartMeeting; }

    public String getTimeEndMeeting(){
        return timeEndMeeting;
    }
    public void setTimeEndMeeting(String timeEndMeeting){ this.timeEndMeeting = timeEndMeeting; }

    public String getDateDay(){
        return dateDay;
    }
    public void setDateDay(String dateDay){ this.dateDay = dateDay; }

    public String getRoomMeeting() { return roomMeeting; }
    public void setRoomMeeting(String roomMeeting) { this.roomMeeting = roomMeeting; }

    public int getColorMeeting() { return colorMeeting; }
    public void setColorMeeting(int colorMeeting) { this.colorMeeting = colorMeeting; }

    public DateTime getDateFilter(){ return dateFilter; }
    public void setDateFilter(DateTime dateFilter) { this.dateFilter = dateFilter; }
}
