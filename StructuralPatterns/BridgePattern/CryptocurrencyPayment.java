package StructuralPatterns.BridgePattern;

public class CryptocurrencyPayment implements PaymentMethod {
    private static final double MAX_AMOUNT = 1000000.00;
    
    @Override
    public boolean pay(double amount) {
        System.out.println(" CRYPTOCURRENCY PAYMENT");
        System.out.println("   Initiating blockchain transaction...");
        System.out.println("   Amount: $" + String.format("%.2f", amount) + " (≈ 0.00X BTC)");
        System.out.println("   Calculating network fees...");
        System.out.println("   Broadcasting to blockchain network...");
        System.out.println("   Waiting for confirmation (1/3)...");
        
        // Simulate processing delay
        simulateProcessing();
        
        System.out.println("   Cryptocurrency payment successful!");
        System.out.println("   Blockchain TX Hash: 0x" + generateTransactionHash());
        System.out.println("   Network: Bitcoin");
        System.out.println("   Confirmations: 3/3");
        
        return true;
    }
    
    @Override
    public String getMethodName() {
        return "Cryptocurrency";
    }
    
    @Override
    public boolean validateAmount(double amount) {
        if (amount <= 0) {
            System.out.println("   Invalid amount");
            return false;
        }
        if (amount > MAX_AMOUNT) {
            System.out.println("   Amount exceeds crypto transaction limit ($" + MAX_AMOUNT + ")");
            return false;
        }
        if (amount < 1.00) {
            System.out.println("   Warning: Amount below minimum recommended ($1.00)");
        }
        return true;
    }
    
    private void simulateProcessing() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private String generateTransactionHash() {
        return Long.toHexString(System.currentTimeMillis()).toUpperCase();
    }
}
