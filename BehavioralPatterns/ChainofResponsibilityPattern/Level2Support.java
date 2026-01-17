package ChainofResponsibilityPattern;

public class Level2Support extends SupportHandler {
    
    public Level2Support() {
        super("Level 2 Support");
    }
    
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getPriority() == SupportTicket.Priority.MODERATE;
    }
    
    @Override
    protected void processRequest(SupportTicket ticket) {
        System.out.println("   " + handlerName + " handled: " + ticket);
        System.out.println("      Solution: Performed advanced diagnostics and configuration");
    }
}
