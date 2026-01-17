package ChainofResponsibilityPattern;

public abstract class SupportHandler {
    protected SupportHandler nextHandler;
    protected String handlerName;
    
    public SupportHandler(String handlerName) {
        this.handlerName = handlerName;
    }
    
    public SupportHandler setNext(SupportHandler handler) {
        this.nextHandler = handler;
        return handler;
    }

    public void handleRequest(SupportTicket ticket) {
        if (canHandle(ticket)) {
            processRequest(ticket);
        } else if (nextHandler != null) {
            System.out.println("    " + handlerName + " cannot handle this. Escalating...");
            nextHandler.handleRequest(ticket);
        } else {
            System.out.println("   " + handlerName + " cannot handle this and no further escalation available!");
            System.out.println("    Ticket remains unresolved: " + ticket);
        }
    }
    
    protected abstract boolean canHandle(SupportTicket ticket);

    protected abstract void processRequest(SupportTicket ticket);
}
