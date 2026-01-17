package ChainofResponsibilityPattern;

public class Level3Support extends SupportHandler {
    
    public Level3Support() {
        super("Level 3 Support");
    }
    
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getPriority() == SupportTicket.Priority.CRITICAL;
    }
    
    @Override
    protected void processRequest(SupportTicket ticket) {
        System.out.println("   " + handlerName + " handled: " + ticket);
        System.out.println("      Solution: Deployed emergency patch and system recovery");
    }
}





