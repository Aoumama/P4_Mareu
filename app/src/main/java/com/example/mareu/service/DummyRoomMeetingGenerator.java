package com.example.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyRoomMeetingGenerator {

    private static List<String> LIST_ROOM = Arrays.asList( "Peach","Mario", "Luigi", "Greta", "Informatique", "Window", "Sonic", "Ball", "Apple", "White");
    public static List<String> generateRooms() { return new ArrayList<>(LIST_ROOM); }

}
