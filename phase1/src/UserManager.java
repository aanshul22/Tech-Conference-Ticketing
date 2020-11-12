import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UserManager {

    private HashMap<String, User> usernamesToUsers;

    public UserManager () {
        usernamesToUsers = new HashMap<>();
    }

//    public List<User> getUserList() {
//        return (List<User>) usernamesToUsers.values();
//    }

    @SuppressWarnings("unchecked")
    public List<String> getUsernameList() {
        return (List<String>) usernamesToUsers.keySet();
    }

    public void setUsernamesToUsers(HashMap<String, User> newMap) {
        usernamesToUsers = newMap;
    }

    private boolean isRegistered(String username) {
        return usernamesToUsers.containsKey(username);
    }

    public boolean registerUser(UserType userType, String name, String username, String password) {
        if (isRegistered(username)){
            return false;
        }
        usernamesToUsers.put(username, new User(userType, name, username, password));
        return true;
    }

    public boolean verifyLogin(String username, String password) { //ask about returning null
        if (isRegistered(username)) {
            return usernamesToUsers.get(username).getPassword().equals(password);
        }
        return false;
    }

    public boolean updateName(String username, String newName) {
        if (isRegistered(username)) {
            usernamesToUsers.get(username).setName(newName);
            return true;
        }
        return false;
    }

    public boolean updatePassword(String username, String newPassword) {
        if (isRegistered(username)) {
            usernamesToUsers.get(username).setPassword(newPassword);
            return true;
        }
        return false;
    }

    public void addEventAttending(String username, UUID eventId) {
        List<UUID> eventsAttending = usernamesToUsers.get(username).getEventsAttending();
        eventsAttending.add(eventId);
        usernamesToUsers.get(username).setEventsAttending(eventsAttending);
    }

    public void addFriend(String username, String friendUsername) {
        List<String> friends = usernamesToUsers.get(username).getFriends();
        friends.add(friendUsername);
        usernamesToUsers.get(username).setFriends(friends);
    }

    public String getName(String username) {
        return usernamesToUsers.get(username).getName();
    }

    public List<UUID> getEventsAttending(String username) {
        return usernamesToUsers.get(username).getEventsAttending();
    }

    public List<String> getFriends(String username) {
        return usernamesToUsers.get(username).getFriends();
    }

    public UserType getUserType(String username){
        return usernamesToUsers.get(username).getUserType();
    }

}
