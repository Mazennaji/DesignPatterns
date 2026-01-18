package StructuralPatterns.BridgePattern;

public abstract class PaymentProcessor {

    protected PaymentMethod method;
    
    public PaymentProcessor(PaymentMethod method) {
        this.method = method;
    }
    
    public abstract boolean processPayment(double amount);
    
    public void setPaymentMethod(PaymentMethod method) {
        this.method = method;
        System.out.println("Payment method changed to: " + method.getMethodName());
    }
    
    public String getPaymentMethodName() {
        return method.getMethodName();
    }
    
    protected boolean validatePayment(double amount) {
        System.out.println("Validating payment...");
        return method.validateAmount(amount);
    }
}
