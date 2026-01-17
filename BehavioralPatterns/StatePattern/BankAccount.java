package StatePattern;

public class BankAccount {
    private AccountState state;
    private double balance;

    public BankAccount() {
        this.state = new SilverAccount(); // default state
        this.balance = 0;
    }

    public void setState(AccountState state) {
        this.state = state;
    }

    public void deposit(double amount) {
        state.deposit(this, amount);
    }

    public void withdraw(double amount) {
        state.withdraw(this, amount);
    }

    public void checkBalance() {
        state.checkBalance(this);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

