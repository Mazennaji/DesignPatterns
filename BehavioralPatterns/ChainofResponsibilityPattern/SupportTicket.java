package ChainofResponsibilityPattern;

public class SupportTicket {
    private String issue;
    private Priority priority;
    private String customerName;
    
    public enum Priority {
        BASIC,
        MODERATE,  
        CRITICAL,   
        EXECUTIVE   
    }
    
    public SupportTicket(String customerName, String issue, Priority priority) {
        this.customerName = customerName;
        this.issue = issue;
        this.priority = priority;
    }
    
    public String getIssue() {
        return issue;
    }
    
    public Priority getPriority() {
        return priority;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    @Override
    public String toString() {
        return "[" + priority + "] " + customerName + ": " + issue;
    }
}
