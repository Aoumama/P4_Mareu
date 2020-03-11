package com.example.mareu.model;

public class Meeting {
    /**
     * Model object representing a Meeting
     */

    private long id;
    private String participantMeeting;
    private String timeStartMeeting;
    private String timeEndMeeting;
    private String subjectMeeting;
    private String roomMeeting;
    private String colorMeeting;

    /**
     * Constructor
     * @param id
     * @param participantMeeting
     * @param timeStartMeeting
     * @param timeEndMeeting
     * @param subjectMeeting
     * @param roomMeeting
     * @param colorMeeting

     */

    public Meeting(long id, String participantMeeting, String timeStartMeeting, String timeEndMeeting, String subjectMeeting, String roomMeeting, String colorMeeting ){
        this.id = id;
        this.participantMeeting = participantMeeting;
        this.subjectMeeting = subjectMeeting;
        this.timeStartMeeting = timeStartMeeting;
        this.timeEndMeeting = timeEndMeeting;
        this.roomMeeting = roomMeeting;
        this.colorMeeting = colorMeeting;
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

    public String getRoomMeeting() { return roomMeeting; }
    public void setRoomMeeting(String roomMeeting) { this.roomMeeting = roomMeeting; }

    public String getColorMeeting() { return colorMeeting; }
    public void setColorMeeting(String colorMeeting) { this.colorMeeting = colorMeeting; }

}
