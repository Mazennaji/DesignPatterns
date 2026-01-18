package StructuralPatterns.BridgePattern;

public class RecurringPayment extends PaymentProcessor {
    private String schedule;  // daily, weekly, monthly, yearly
    
    public RecurringPayment(PaymentMethod method) {
        super(method);
        this.schedule = "monthly";  // default
    }
    
    @Override
    public boolean processPayment(double amount) {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║        RECURRING SUBSCRIPTION PAYMENT      ║");
        System.out.println("║        Method: " + String.format("%-32s", method.getMethodName()) + "║");
        System.out.println("║        Schedule: " + String.format("%-30s", schedule.toUpperCase()) + "║");
        System.out.println("╚════════════════════════════════════════════════╝");
        
        // Validate payment
        if (!validatePayment(amount)) {
            System.out.println("Payment validation failed!\n");
            return false;
        }
        
        System.out.println("Validation passed");
        System.out.println("Setting up recurring payment schedule...");
        System.out.println("─────────────────────────────────────────────────");
        
        boolean success = method.pay(amount);
        
        if (success) {
            System.out.println("─────────────────────────────────────────────────");
            setupSubscription(amount);
        }
        
        System.out.println("╚════════════════════════════════════════════════╝\n");
        return success;
    }
    
    public void setupSchedule(String schedule) {
        this.schedule = schedule;
        System.out.println("Recurring schedule set to: " + schedule.toUpperCase());
    }
    
    public String getSchedule() {
        return schedule;
    }

    private void setupSubscription(double amount) {
        System.out.println("Subscription Details:");
        System.out.println("   Type: Recurring Subscription");
        System.out.println("   Amount: $" + String.format("%.2f", amount) + " per " + schedule);
        System.out.println("   Method: " + method.getMethodName());
        System.out.println("   Start Date: " + java.time.LocalDate.now());
        System.out.println("   Next Payment: " + calculateNextPayment());
        System.out.println("   Status: ACTIVE");
        System.out.println("   Subscription activated successfully!");
    }
    
    private String calculateNextPayment() {
        java.time.LocalDate today = java.time.LocalDate.now();
        java.time.LocalDate nextPayment;
        
        switch (schedule.toLowerCase()) {
            case "daily":
                nextPayment = today.plusDays(1);
                break;
            case "weekly":
                nextPayment = today.plusWeeks(1);
                break;
            case "yearly":
                nextPayment = today.plusYears(1);
                break;
            case "monthly":
            default:
                nextPayment = today.plusMonths(1);
                break;
        }
        
        return nextPayment.toString();
    }
}
