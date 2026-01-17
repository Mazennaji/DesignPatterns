package StatePattern;

public class OverdrawnAccount implements AccountState {
    @Override
    public void deposit(BankAccount account, double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposited " + amount + ", balance: " + account.getBalance());
        if (account.getBalance() > 0) {
            account.setState(new SilverAccount());
            System.out.println("Account back to Silver!");
        }
    }

    @Override
    public void withdraw(BankAccount account, double amount) {
        System.out.println("Cannot withdraw, account is overdrawn!");
    }

    @Override
    public void checkBalance(BankAccount account) {
        System.out.println("Overdrawn Account balance: " + account.getBalance());
    }
}

