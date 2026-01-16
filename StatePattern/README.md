# 🏦 State Design Pattern

## 📌 Overview

The **State Pattern** is a **behavioral design pattern** that allows an object to **change its behavior when its internal state changes**. It helps avoid complex conditional logic for state-dependent behavior.

---

## 🎯 Intent

> Allow an object to alter its behavior when its internal state changes. The object will appear to change its class.

---

## ❓ Why Use the State Pattern?

* Avoids large `if-else` or `switch` statements based on object state
* Encapsulates state-specific behavior in separate classes
* Simplifies maintenance and scalability
* Allows dynamic state changes at runtime

---

## 🧩 Pattern Participants

1. **Context (`BankAccount`)**

   * Holds a reference to the current state
   * Delegates behavior to the current state object

2. **State (`AccountState`)**

   * Interface or abstract class defining behavior methods for different states

3. **Concrete States (`SilverAccount`, `GoldAccount`, `OverdrawnAccount`)**

   * Implement behavior specific to each state
   * Can change the state of the context when conditions are met

---

## 📝 Example: Bank Account with States

### 🧠 Scenario

We implement a **Bank Account** that changes behavior based on its balance:

* **SilverAccount** → normal account
* **GoldAccount** → high balance account with benefits
* **OverdrawnAccount** → balance negative, withdrawal restricted

---

## 1️⃣ State Interface – `AccountState`

```java
public interface AccountState {
    void deposit(BankAccount account, double amount);
    void withdraw(BankAccount account, double amount);
    void checkBalance(BankAccount account);
}
```

---

## 2️⃣ Concrete States

```java
public class SilverAccount implements AccountState { ... }
public class GoldAccount implements AccountState { ... }
public class OverdrawnAccount implements AccountState { ... }
```

*Each concrete state handles deposits, withdrawals, and can change the account state if conditions are met.*

---

## 3️⃣ Context – `BankAccount`

```java
public class BankAccount {
    private AccountState state;
    private double balance;

    public BankAccount() {
        this.state = new SilverAccount();
        this.balance = 0;
    }

    public void deposit(double amount) { state.deposit(this, amount); }
    public void withdraw(double amount) { state.withdraw(this, amount); }
    public void checkBalance() { state.checkBalance(this); }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public void setState(AccountState state) { this.state = state; }
}
```

---

## 4️⃣ Client Code

```java
public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        account.deposit(500);  // Silver
        account.withdraw(100);
        account.deposit(600);  // Upgrade to Gold
        account.withdraw(1200); // Overdrawn
        account.deposit(300);   // Back to Silver
    }
}
```

---

## ✅ Advantages

* Eliminates conditional statements for state logic
* Encapsulates state behavior in separate classes
* Makes adding new states easier
* Dynamic behavior change at runtime

---

## ⚠️ Disadvantages

* Can increase the number of classes for each state
* Slightly more complex initial setup

---

## 📚 When to Use

* Objects with behavior that depends on internal state
* Workflows or processes with multiple stages
* Game characters with different modes
* Financial systems with account states (Silver, Gold, Overdrawn)

---

## 🏁 Conclusion

The **State Pattern** allows objects to change behavior dynamically based on their internal state. In this example, a **Bank Acco
