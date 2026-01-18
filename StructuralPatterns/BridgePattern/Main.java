package StructuralPatterns.BridgePattern;

public class Main {
    
    public static void main(String[] args) {
        printHeader();
        
        scenario1_DifferentCombinations();
        
        pause();
        
        scenario2_RuntimeSwitching();
        
        pause();
        
        scenario3_RecurringPayments();
        
        pause();
        
        scenario4_ClassExplosionPrevention();
        
        printConclusion();
    }
    
    private static void scenario1_DifferentCombinations() {
        printScenarioHeader("SCENARIO 1: Different Payment Combinations");
        
        System.out.println("Creating various payment processor/method combinations...\n");
        
        PaymentProcessor payment1 = new OnlinePayment(new CreditCardPayment());
        payment1.processPayment(149.99);
        
        PaymentProcessor payment2 = new OnlinePayment(new PayPalPayment());
        payment2.processPayment(299.50);
        
        PaymentProcessor payment3 = new OnlinePayment(new CryptocurrencyPayment());
        payment3.processPayment(1500.00);
        
        PaymentProcessor payment4 = new RecurringPayment(new CreditCardPayment());
        payment4.processPayment(9.99);
        
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println(" Key Insight:");
        System.out.println("   . We can combine ANY payment type with ANY method!");
        System.out.println("   . Without Bridge: 2 x 3 = 6 classes needed");
        System.out.println("   . With Bridge: 2 + 3 = 5 classes needed");
        System.out.println("   . Saved 1 class (17% reduction)");
        System.out.println("═══════════════════════════════════════════════════════\n");
    }
    

    private static void scenario2_RuntimeSwitching() {
        printScenarioHeader("SCENARIO 2: Runtime Payment Method Switching");
        
        System.out.println("Creating a payment processor and switching methods at runtime...\n");
        
        PaymentProcessor payment = new OnlinePayment(new CreditCardPayment());
        System.out.println("Initial method: " + payment.getPaymentMethodName() + "\n");
        
        payment.processPayment(99.99);
        
        System.out.println("─────────────────────────────────────────────────────────\n");
        
        payment.setPaymentMethod(new PayPalPayment());
        payment.processPayment(99.99);
        
        System.out.println("─────────────────────────────────────────────────────────\n");
        
        payment.setPaymentMethod(new CryptocurrencyPayment());
        payment.processPayment(99.99);
        
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println(" Bridge Pattern Advantage:");
        System.out.println("   . Implementation can be changed at RUNTIME");
        System.out.println("   . Same payment processor, different methods");
        System.out.println("   . Impossible with simple inheritance!");
        System.out.println("═══════════════════════════════════════════════════════\n");
    }
    
    private static void scenario3_RecurringPayments() {
        printScenarioHeader("SCENARIO 3: Recurring Payments with Schedules");
        
        System.out.println("Demonstrating recurring payment extended functionality...\n");
        
        RecurringPayment subscription = new RecurringPayment(new PayPalPayment());
        
        subscription.setupSchedule("weekly");
        subscription.processPayment(19.99);
        
        System.out.println("─────────────────────────────────────────────────────────\n");
        
        subscription.setupSchedule("monthly");
        subscription.setPaymentMethod(new CryptocurrencyPayment());
        subscription.processPayment(49.99);
        
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println(" Refined Abstraction Benefits:");
        System.out.println("   . RecurringPayment adds specialized operations");
        System.out.println("   . setupSchedule() not available in OnlinePayment");
        System.out.println("   . Both can use ANY payment method");
        System.out.println("═══════════════════════════════════════════════════════\n");
    }
    
    private static void scenario4_ClassExplosionPrevention() {
        printScenarioHeader("SCENARIO 4: Class Explosion Prevention");
        
        System.out.println(" WITHOUT Bridge Pattern (Class for EVERY combination):");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("   . OnlineCreditCardPayment");
        System.out.println("   . OnlinePayPalPayment");
        System.out.println("   . OnlineCryptoPayment");
        System.out.println("   . RecurringCreditCardPayment");
        System.out.println("   . RecurringPayPalPayment");
        System.out.println("   . RecurringCryptoPayment");
        System.out.println("   ─────────────────────────────");
        System.out.println("   Total: 2 x 3 = 6 classes");
        System.out.println("\n   Add 1 more payment type (e.g., InstallmentPayment)?");
        System.out.println("   -> Need 3 MORE classes! (9 total)");
        System.out.println("\n   Add 1 more payment method (e.g., ApplePay)?");
        System.out.println("   -> Need 2 MORE classes! (8 total)");
        System.out.println("\n   Add BOTH?");
        System.out.println("   -> Need 12 classes total! (3 x 4)\n");
        
        System.out.println(" WITH Bridge Pattern (Separate hierarchies):");
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("   Payment Processors (Abstraction):");
        System.out.println("   . OnlinePayment");
        System.out.println("   . RecurringPayment");
        System.out.println("\n   Payment Methods (Implementation):");
        System.out.println("   . CreditCardPayment");
        System.out.println("   . PayPalPayment");
        System.out.println("   . CryptocurrencyPayment");
        System.out.println("   ─────────────────────────────");
        System.out.println("   Total: 2 + 3 = 5 classes");
        System.out.println("\n   Add 1 more payment type (e.g., InstallmentPayment)?");
        System.out.println("   -> Need just 1 MORE class! (6 total)");
        System.out.println("\n   Add 1 more payment method (e.g., ApplePay)?");
        System.out.println("   -> Need just 1 MORE class! (6 total)");
        System.out.println("\n   Add BOTH?");
        System.out.println("   -> Need just 7 classes total! (3 + 4)\n");
        
        System.out.println("Class Growth Comparison:");
        System.out.println("╔══════════════╦═══════════╦════════════════╦═════════════╦═════════╗");
        System.out.println("║   Payment    ║  Payment  ║ Without Bridge ║ With Bridge ║ Savings ║");
        System.out.println("║   Types      ║  Methods  ║    (m x n)     ║   (m + n)   ║   (%)   ║");
        System.out.println("╠══════════════╬═══════════╬════════════════╬═════════════╬═════════╣");
        System.out.println("║      2       ║     3     ║       6        ║      5      ║   17%   ║");
        System.out.println("║      3       ║     3     ║       9        ║      6      ║   33%   ║");
        System.out.println("║      3       ║     4     ║      12        ║      7      ║   42%   ║");
        System.out.println("║      4       ║     4     ║      16        ║      8      ║   50%   ║");
        System.out.println("║      5       ║     5     ║      25        ║     10      ║   60%   ║");
        System.out.println("║     10       ║    10     ║     100        ║     20      ║   80%   ║");
        System.out.println("╚══════════════╩═══════════╩════════════════╩═════════════╩═════════╝\n");
        
        System.out.println(" Critical Insights:");
        System.out.println("───────────────────────────────────────────────────────────");
        System.out.println("   1. WITHOUT Bridge: m x n (EXPONENTIAL)");
        System.out.println("      . Growth accelerates rapidly");
        System.out.println("      . 10 types x 10 methods = 100 classes!");
        System.out.println("\n   2. WITH Bridge: m + n (LINEAR) ");
        System.out.println("      . Growth remains manageable");
        System.out.println("      . 10 types + 10 methods = 20 classes");
        System.out.println("\n   3. Savings increase with scale:");
        System.out.println("      . Small system: 17% reduction");
        System.out.println("      . Medium system: 50% reduction");
        System.out.println("      . Large system: 80% reduction ");
        System.out.println("───────────────────────────────────────────────────────────\n");
    }
    
    private static void printHeader() {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║   BRIDGE DESIGN PATTERN - Payment Processing System    ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
    }
    
    private static void printScenarioHeader(String title) {
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("  " + title);
        System.out.println("═══════════════════════════════════════════════════════════\n");
    }
    
    private static void printConclusion() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║                   KEY BENEFITS                         ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║   Prevents Class Explosion (m+n vs mxn)             ║");
        System.out.println("║   Independent Extension (both sides grow freely)     ║");
        System.out.println("║   Runtime Implementation Switching                   ║");
        System.out.println("║   Decouples Abstraction from Implementation          ║");
        System.out.println("║   Composition Over Inheritance                       ║");
        System.out.println("║   Open/Closed Principle (open for extension)         ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
        
        System.out.println("Summary:");
        System.out.println("───────────────────────────────────────────────────────────");
        System.out.println("The Bridge Pattern achieves flexibility by:");
        System.out.println("  1. SEPARATING payment type from payment method");
        System.out.println("  2. Using COMPOSITION instead of inheritance");
        System.out.println("  3. Allowing INDEPENDENT evolution of both hierarchies");
        System.out.println("  4. Enabling RUNTIME switching of implementations");
        System.out.println("  5. Growing LINEARLY (m+n) not exponentially (mxn)");
        System.out.println("───────────────────────────────────────────────────────────");
        System.out.println("Perfect for: Payment systems, database drivers, graphics APIs,");
        System.out.println("messaging platforms, and any system with two independent");
        System.out.println("dimensions of variation! \n");
    }
    
    private static void pause() {
        System.out.println("\n" + "─".repeat(60) + "\n");
    }
}
