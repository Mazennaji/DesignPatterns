package StructuralPatterns.BridgePattern;

public class CreditCardPayment implements PaymentMethod {
    private static final double MAX_AMOUNT = 50000.00;
    
    @Override
    public boolean pay(double amount) {
        System.out.println("CREDIT CARD PAYMENT");
        System.out.println("   Processing credit card transaction...");
        System.out.println("   Amount: $" + String.format("%.2f", amount));
        System.out.println("   Validating card details...");
        System.out.println("   Checking available credit...");
        System.out.println("   Authorizing transaction...");
        
        simulateProcessing();
        
        System.out.println("   Credit card payment successful!");
        System.out.println("   Transaction ID: CC-" + generateTransactionId());
        
        return true;
    }
    
    @Override
    public String getMethodName() {
        return "Credit Card";
    }
    
    @Override
    public boolean validateAmount(double amount) {
        if (amount <= 0) {
            System.out.println("    Invalid amount");
            return false;
        }
        if (amount > MAX_AMOUNT) {
            System.out.println("    Amount exceeds credit card limit ($" + MAX_AMOUNT + ")");
            return false;
        }
        return true;
    }
    
    private void simulateProcessing() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private String generateTransactionId() {
        return String.valueOf(System.currentTimeMillis() % 1000000);
    }
}
