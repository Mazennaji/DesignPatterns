package MediatorPattern;

public class BasicUser extends User {
    
    public BasicUser(ChatMediator mediator, String name) {
        super(mediator, name);
        mediator.addUser(this);
    }
    
    @Override
    public void send(String message) {
        System.out.println(" " + name + " sends: \"" + message + "\"");
        mediator.sendMessage(message, this);
    }
    
    @Override
    public void receive(String message, User sender) {
        System.out.println("  " + name + " received from " + sender.getName() + ": \"" + message + "\"");
    }
}
