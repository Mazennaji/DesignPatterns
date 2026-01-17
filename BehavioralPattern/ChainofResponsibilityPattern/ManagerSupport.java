package ChainofResponsibilityPattern;

public class ManagerSupport extends SupportHandler {
    
    public ManagerSupport() {
        super("Manager Support");
    }
    
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getPriority() == SupportTicket.Priority.EXECUTIVE;
    }
    
    @Override
    protected void processRequest(SupportTicket ticket) {
        System.out.println("   " + handlerName + " handled: " + ticket);
        System.out.println("      Solution: Personally addressed with priority escalation to engineering team");
    }
}
