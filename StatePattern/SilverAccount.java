package StatePattern;

public class SilverAccount implements AccountState {
    @Override
    public void deposit(BankAccount account, double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposited " + amount + ", balance: " + account.getBalance());
        if (account.getBalance() > 1000) {
            account.setState(new GoldAccount());
            System.out.println("Upgraded to Gold Account!");
        }
    }

    @Override
    public void withdraw(BankAccount account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrew " + amount + ", balance: " + account.getBalance());
        } else {
            account.setState(new OverdrawnAccount());
            System.out.println("Insufficient funds! Account is Overdrawn.");
        }
    }

    @Override
    public void checkBalance(BankAccount account) {
        System.out.println("Silver Account balance: " + account.getBalance());
    }
}

