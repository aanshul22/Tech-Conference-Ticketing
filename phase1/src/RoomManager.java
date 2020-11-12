import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RoomManager {
    private HashMap<String, Room> rooms;
    // what we need:
    // attributes: all the room name and their Room object,
    // methods: add room, remove room?, add event to room schedule, remove event from room schedule, create room,
    // check room capacity(or does this go in Room?),
    /**
     * The constructor takes rooms and assigns the variable an appropriate value.
     *
     *
     */

    public RoomManager(){ this.rooms = new HashMap<>(); }

    /**
     * Implements Getter, getRooms, for rooms.
     *
     * @return hashmap of all created rooms
     */
    public HashMap<String, Room> getRooms(){ return rooms; }

    /**
     * Implements creator, createRoom, to instantiate a Room object.
     *
     * @return a Room object with assigned attributes as specified by the parameters
     */
    public Room createRoom(String roomName, int capacity){
        return new Room(roomName, capacity);
    }

    /**
     * Implements modifier, addRoom, for rooms.
     *
     * @return a boolean indicating if room was successfully added
     */
    public boolean addRoom(String roomName, int capacity) {
        if (rooms.containsKey(roomName)){
            return false;
        }else{
            Room new_room = createRoom(roomName, capacity);
            rooms.put(roomName, new_room);
            return true;
        }
    }

    /**
     * Implements helper method, findRoom, to find Room object when given its name.
     *
     * @return a Room object in hashmap of rooms associated with the given String roomName
     */
    public Room findRoom(String roomName){
        for (String name: rooms.keySet()){
            Room r = rooms.get(name);
            if (r.getRoomName().equals(roomName)){
                return r;
            }
        }
        return null;
    }

    /**
     * Implements modifier, removeEventFromSchedule, for a scheduled event.
     *
     * @return a boolean indicating if event was successfully removed
     */
    public boolean removeEventFromSchedule(UUID eventID){
        for (String name: rooms.keySet()){
            Room r = rooms.get(name);
            HashMap<LocalDateTime, UUID> schedule = r.getSchedule();
            for (LocalDateTime time: schedule.keySet()){
                if (schedule.get(time).equals(eventID)){
                    schedule.remove(time);
                    r.setRoomSchedule(schedule);
                    return true; // since same event with diff time will have different ids, we don't need to worry about it
                }
            }
        }
        return false;
    }

    /**
     * Implements modifier, addEventToRoom, for event in a room.
     *
     * @return a boolean indicating if event was successfully added
     */
    public boolean addEventToRoom(UUID eventId, String roomName, LocalDateTime start){
        Room room = findRoom(roomName);
        if (room.getSchedule().containsValue(eventId)){
            return false;
        }
        for (LocalDateTime time: room.getSchedule().keySet()){
            if (time.isAfter(start.minusHours(1)) && time.isBefore(start.plusHours(1))){
                return false;
            }
        }
        HashMap<LocalDateTime, UUID> updated_room = room.getSchedule();
        updated_room.put(start, eventId);
        room.setRoomSchedule(updated_room);
        return true;
    }

    public boolean hasSpace(String roomName, int numOfAttendees){
        Room room = findRoom(roomName);
        if (room.getCapacity() > (numOfAttendees)){
            return false;
        }
        return true;
    }
}
