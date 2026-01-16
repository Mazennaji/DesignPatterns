package StatePattern;

public interface AccountState {
    void deposit(BankAccount account, double amount);
    void withdraw(BankAccount account, double amount);
    void checkBalance(BankAccount account);
}

