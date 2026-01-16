public class Main {
    public static void main(String[] args) {
        // Create shopping cart with $150
        ShoppingCart cart = new ShoppingCart(150.00);
        
        // Pay with Credit Card
        System.out.println("=== Payment Option 1: Credit Card ===");
        cart.setPaymentStrategy(new CreditCardPayment("1234567890123456", "123"));
        cart.checkout();
        
        System.out.println();
        
        // Pay with PayPal
        System.out.println("=== Payment Option 2: PayPal ===");
        cart.setPaymentStrategy(new PayPalPayment("user@example.com", "password123"));
        cart.checkout();
        
        System.out.println();
        
        // Pay with Bitcoin
        System.out.println("=== Payment Option 3: Bitcoin ===");
        cart.setPaymentStrategy(new BitcoinPayment("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"));
        cart.checkout();
    }
}