package ChainofResponsibilityPattern;

public class Level1Support extends SupportHandler {
    
    public Level1Support() {
        super("Level 1 Support");
    }
    
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getPriority() == SupportTicket.Priority.BASIC;
    }
    
    @Override
    protected void processRequest(SupportTicket ticket) {
        System.out.println("    " + handlerName + " handled: " + ticket);
        System.out.println("      Solution: Provided standard troubleshooting steps");
    }
}
