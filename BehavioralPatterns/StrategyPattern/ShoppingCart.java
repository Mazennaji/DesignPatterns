public class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    private double amount;
    
    public ShoppingCart(double amount) {
        this.amount = amount;
    }
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public void checkout() {
        if (paymentStrategy == null) {
            System.out.println("Please select a payment method!");
            return;
        }
        paymentStrategy.pay(amount);
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
}