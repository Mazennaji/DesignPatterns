package StatePattern;

public class GoldAccount implements AccountState {
    @Override
    public void deposit(BankAccount account, double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Gold deposit: " + amount + ", balance: " + account.getBalance());
    }

    @Override
    public void withdraw(BankAccount account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Gold withdraw: " + amount + ", balance: " + account.getBalance());
        } else {
            account.setState(new OverdrawnAccount());
            System.out.println("Insufficient funds! Account is Overdrawn.");
        }
    }

    @Override
    public void checkBalance(BankAccount account) {
        System.out.println("Gold Account balance: " + account.getBalance());
    }
}
