package com.techelevator.dao;

import com.techelevator.model.Room;

import java.util.Map;

public interface RoomDao {
    public Room getRoomById(int id);
    public Map<Integer, Room> getMapOfRooms();
}
