package MediatorPattern;

public class PremiumUser extends User {
    
    public PremiumUser(ChatMediator mediator, String name) {
        super(mediator, name);
        mediator.addUser(this);
    }
    
    @Override
    public void send(String message) {
        System.out.println(" " + name + " (Premium) sends: \"" + message + "\"");
        mediator.sendMessage(message, this);
    }
    
    @Override
    public void receive(String message, User sender) {
        System.out.println(" " + name + " (Premium) received from " + sender.getName() + ": \"" + message + "\"");
    }
}
