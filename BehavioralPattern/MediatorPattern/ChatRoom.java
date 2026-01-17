package MediatorPattern;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator {
    private List<User> users;
    private String roomName;
    
    public ChatRoom(String roomName) {
        this.users = new ArrayList<>();
        this.roomName = roomName;
        System.out.println("Chat Room '" + roomName + "' created!");
    }
    
    @Override
    public void addUser(User user) {
        users.add(user);
        System.out.println(" " + user.getName() + " joined '" + roomName + "'");
        System.out.println("Total users in room: " + users.size() + "\n");
    }
    
    @Override
    public void removeUser(User user) {
        users.remove(user);
        System.out.println(" " + user.getName() + " left '" + roomName + "'");
        System.out.println(" Total users in room: " + users.size() + "\n");
    }
    
    @Override
    public void sendMessage(String message, User sender) {
        System.out.println("\n [" + roomName + "] Broadcasting message from " + sender.getName() + "...");
        
        for (User user : users) {
            if (user != sender) {
                user.receive(message, sender);
            }
        }
    }
    
    public String getRoomName() {
        return roomName;
    }
    
    public int getUserCount() {
        return users.size();
    }
}
