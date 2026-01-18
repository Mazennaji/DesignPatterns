package StructuralPatterns.BridgePattern;

public class PayPalPayment implements PaymentMethod {
    private static final double MAX_AMOUNT = 100000.00;
    
    @Override
    public boolean pay(double amount) {
        System.out.println("PAYPAL PAYMENT");
        System.out.println("   Connecting to PayPal API...");
        System.out.println("   Amount: $" + String.format("%.2f", amount));
        System.out.println("   Authenticating PayPal account...");
        System.out.println("   Processing through PayPal gateway...");
        
        simulateProcessing();
        
        System.out.println("   PayPal payment successful!");
        System.out.println("   PayPal Transaction ID: PP-" + generateTransactionId());
        System.out.println("   Confirmation email sent to PayPal account");
        
        return true;
    }
    
    @Override
    public String getMethodName() {
        return "PayPal";
    }
    
    @Override
    public boolean validateAmount(double amount) {
        if (amount <= 0) {
            System.out.println("   Invalid amount");
            return false;
        }
        if (amount > MAX_AMOUNT) {
            System.out.println("   Amount exceeds PayPal limit ($" + MAX_AMOUNT + ")");
            return false;
        }
        return true;
    }
    
    private void simulateProcessing() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private String generateTransactionId() {
        return String.valueOf(System.currentTimeMillis() % 1000000);
    }
}
