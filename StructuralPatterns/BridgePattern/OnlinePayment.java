package StructuralPatterns.BridgePattern;

public class OnlinePayment extends PaymentProcessor {
    
    public OnlinePayment(PaymentMethod method) {
        super(method);
    }
    
    @Override
    public boolean processPayment(double amount) {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║        ONE-TIME ONLINE PAYMENT                   ║");
        System.out.println("  ║        Method: " + String.format("%-32s", method.getMethodName()) + "║");
        System.out.println("╚════════════════════════════════════════════════╝");
        
        // Validate payment
        if (!validatePayment(amount)) {
            System.out.println("Payment validation failed!\n");
            return false;
        }
        
        System.out.println("Validation passed");
        System.out.println("─────────────────────────────────────────────────");
        
        // Delegate to implementation
        boolean success = method.pay(amount);
        
        if (success) {
            System.out.println("─────────────────────────────────────────────────");
            generateReceipt(amount);
        }
        
        System.out.println("╚════════════════════════════════════════════════╝\n");
        return success;
    }
    
    private void generateReceipt(double amount) {
        System.out.println("Receipt Generated:");
        System.out.println("   Payment Type: One-Time Online");
        System.out.println("   Amount: $" + String.format("%.2f", amount));
        System.out.println("   Method: " + method.getMethodName());
        System.out.println("   Date: " + java.time.LocalDateTime.now());
        System.out.println("   Status: COMPLETED");
    }
}
