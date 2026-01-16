package StatePattern;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        account.deposit(500);  // Silver
        account.withdraw(100);
        account.deposit(600);  // Should upgrade to Gold
        account.withdraw(1200); // Should become Overdrawn
        account.deposit(300);   // Back to Silver
    }
}

