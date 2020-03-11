package com.example.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyRoomMeetingGenerator {

    private static List<String> LIST_ROOM = Arrays.asList("Peach","Mario", "Luigi", "Greta", "D", "F", "K", "G", "Y", "T");
    public static List<String> generateRooms() { return new ArrayList<>(LIST_ROOM); }

}
