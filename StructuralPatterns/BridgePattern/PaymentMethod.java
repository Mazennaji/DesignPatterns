package StructuralPatterns.BridgePattern;

public interface PaymentMethod {
    
    boolean pay(double amount);
    
    String getMethodName();
    
    boolean validateAmount(double amount);
}
