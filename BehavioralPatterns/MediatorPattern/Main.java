package MediatorPattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   Mediator Pattern - Chat Room Demo    ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        ChatRoom generalChat = new ChatRoom("General");
        
        System.out.println("------------------------------------------");
        System.out.println("  Adding users to chat room...");
        System.out.println("------------------------------------------\n");
        
        var alice = new BasicUser(generalChat, "Alice");
        var bob = new BasicUser(generalChat, "Bob");
        var charlie = new PremiumUser(generalChat, "Charlie");
        var diana = new BasicUser(generalChat, "Diana");
        
        System.out.println("------------------------------------------");
        System.out.println("  Users sending messages...");
        System.out.println("------------------------------------------");
        
        alice.send("Hi everyone!");
        
        System.out.println();
        bob.send("Hello Alice! How are you?");
        
        System.out.println();
        charlie.send("Hey team! Ready for the meeting?");
        
        System.out.println();
        diana.send("Good morning all!");
        
        System.out.println("\n------------------------------------------");
        System.out.println("  User leaving chat room...");
        System.out.println("------------------------------------------\n");
        
        generalChat.removeUser(bob);
        
        System.out.println("------------------------------------------");
        System.out.println("  Continuing conversation...");
        System.out.println("------------------------------------------");
        
        alice.send("Anyone want coffee?");
        
        System.out.println();
        charlie.send("I'm in!");
        
        System.out.println("\n----------------------------------------");
        System.out.println("  Demonstrating the Mediator Pattern");
        System.out.println("------------------------------------------");
        System.out.println("  Users don't communicate directly");
        System.out.println("  All communication goes through ChatRoom");
        System.out.println("  Users don't know about each other");
        System.out.println("  Easy to add/remove users dynamically");
        System.out.println("  Centralized control of communication");
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          Demo Complete!                ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
}
