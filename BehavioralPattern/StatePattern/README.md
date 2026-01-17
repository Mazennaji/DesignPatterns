# 🏦 State Design Pattern (Java)

## 📌 Overview

The **State Pattern** is a **behavioral design pattern** that allows an object to **change its behavior when its internal state changes**. It helps avoid complex conditional logic for state-dependent behavior by encapsulating state-specific logic in separate classes.

The pattern is particularly useful when an object must change its behavior at runtime based on its state, and when state-specific behavior needs to be added or modified without changing the core object.

---

## 🎯 Intent

> Allow an object to alter its behavior when its internal state changes. The object will appear to change its class.

---

## 🧠 Key Idea

> "Don't change what you are—change how you behave based on where you are."

---

## ❓ Why Use the State Pattern?

* Avoids large `if-else` or `switch` statements based on object state
* Encapsulates state-specific behavior in separate classes
* Simplifies maintenance and scalability
* Allows dynamic state changes at runtime
* Makes adding new states easy without modifying existing code

---

## ⏱️ When to Use

Use the State Pattern when an object's behavior depends on its state and must change behavior at runtime, or when operations have large conditional statements that depend on the object's state.

---

## 🧩 Pattern Participants

| Component | Role |
|-----------|------|
| **Context** | Maintains current state; delegates operations to state object |
| **State Interface** | Defines interface for state-specific behavior |
| **Concrete States** | Implement behavior for specific states |
| **Client** | Interacts with context; unaware of state changes |

---

## 📐 UML Class Diagram (State Pattern)

```text
+--------------------+
|   BankAccount      |
|    (Context)       |
+--------------------+
| -state: State      |
| -balance: double   |
+--------------------+
| +deposit(double)   |
| +withdraw(double)  |
| +checkBalance()    |
| +setState(State)   |
| +getBalance()      |
| +setBalance(double)|
+--------------------+
        |
        | delegates to
        ▼
+--------------------+
| <<interface>>      |
|   AccountState     |
|     (State)        |
+--------------------+
| +deposit(Account)  |
| +withdraw(Account) |
| +checkBalance()    |
+--------------------+
        ▲
        |
        | implements
        |
   +----|----+----+
   |         |    |
+------------------+  +------------------+  +---------------------+
| SilverAccount    |  | GoldAccount      |  | OverdrawnAccount    |
| (ConcreteState)  |  | (ConcreteState)  |  | (ConcreteState)     |
+------------------+  +------------------+  +---------------------+
| +deposit()       |  | +deposit()       |  | +deposit()          |
| +withdraw()      |  | +withdraw()      |  | +withdraw()         |
| +checkBalance()  |  | +checkBalance()  |  | +checkBalance()     |
+------------------+  +------------------+  +---------------------+

+--------------------+
|       Main         |
|     (Client)       |
+--------------------+
| +main(String[])    |
+--------------------+
```

---

## 🧠 UML Diagram Explanation

The UML diagram illustrates the structure and relationships of the **State Design Pattern** implementation.

### 1️⃣ Context (BankAccount)
- Maintains a reference to the current state object
- Stores the shared state data (balance in this case)
- Delegates all state-dependent operations to the current state object
- Provides methods for states to:
  - Change the current state (`setState()`)
  - Access/modify shared data (`getBalance()`, `setBalance()`)
- Does **not** contain conditional logic for state-dependent behavior

---

### 2️⃣ State Interface (AccountState)
- Defines the interface for state-specific operations
- Declares methods that all concrete states must implement:
  - `deposit()` - handles deposit logic for this state
  - `withdraw()` - handles withdrawal logic for this state
  - `checkBalance()` - displays balance information for this state
- Ensures all states can be used interchangeably

---

### 3️⃣ Concrete States (SilverAccount, GoldAccount, OverdrawnAccount)
- Implement state-specific behavior for each method
- Can access the context to:
  - Read/modify the balance
  - Transition to a different state when conditions are met
- Each state encapsulates its own rules:
  - **SilverAccount**: Standard operations, upgrades to Gold at $1000+
  - **GoldAccount**: Premium benefits, downgrades to Silver below $1000
  - **OverdrawnAccount**: Restricted withdrawals, restores to Silver when positive

---

### 4️⃣ Client (Main)
- Interacts with the context (BankAccount)
- Calls operations without knowing the current state
- Does **not** manage state transitions
- Unaware of which concrete state is handling the operation

---

## 🔗 Relationships Summary

- `BankAccount` **delegates to** `AccountState`
- `SilverAccount`, `GoldAccount`, `OverdrawnAccount` **implement** `AccountState`
- Concrete states **can change** the context's state
- `Main` **uses** `BankAccount` without knowing about states
- State transitions happen **internally** based on business rules

---

## ✅ Key Design Benefits

- Eliminates complex conditional statements
- Encapsulates state-specific behavior (Single Responsibility Principle)
- Makes states easily extensible (Open/Closed Principle)
- Allows dynamic behavior changes at runtime
- Improves code maintainability and readability

---

## 🔄 State Pattern vs. Conditional Logic

### ❌ **Without State Pattern (Conditional Chaos)**

```java
public class BankAccount {
    private double balance;
    private String accountType; // "SILVER", "GOLD", "OVERDRAWN"
    
    public BankAccount() {
        this.balance = 0;
        this.accountType = "SILVER";
    }
    
    public void deposit(double amount) {
        balance += amount;
        
        // Messy conditional logic for state transitions
        if (accountType.equals("OVERDRAWN") && balance > 0) {
            accountType = "SILVER";
            System.out.println("Account restored to Silver status");
        }
        
        if (accountType.equals("SILVER") && balance >= 1000) {
            accountType = "GOLD";
            System.out.println("Congratulations! Upgraded to Gold account!");
        }
        
        System.out.println("Deposited: $" + amount);
    }
    
    public void withdraw(double amount) {
        // Even messier nested conditionals
        if (accountType.equals("OVERDRAWN")) {
            System.out.println("Cannot withdraw. Account is overdrawn!");
            return;
        }
        
        if (accountType.equals("SILVER")) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawn: $" + amount);
            } else {
                System.out.println("Insufficient funds!");
            }
            
            if (balance < 0) {
                accountType = "OVERDRAWN";
                System.out.println("Warning: Account is now overdrawn!");
            }
        }
        
        if (accountType.equals("GOLD")) {
            balance -= amount; // Gold gets overdraft protection
            System.out.println("Withdrawn: $" + amount + " (Gold benefits applied)");
            
            if (balance < 1000 && balance >= 0) {
                accountType = "SILVER";
                System.out.println("Account downgraded to Silver");
            }
            
            if (balance < 0) {
                accountType = "OVERDRAWN";
                System.out.println("Warning: Account is now overdrawn!");
            }
        }
    }
    
    public void checkBalance() {
        // More conditionals for display logic
        if (accountType.equals("SILVER")) {
            System.out.println("Silver Account Balance: $" + balance);
        } else if (accountType.equals("GOLD")) {
            System.out.println("Gold Account Balance: $" + balance + " ⭐");
        } else if (accountType.equals("OVERDRAWN")) {
            System.out.println("Overdrawn Account Balance: $" + balance + " ⚠️");
        }
    }
}
```

**Problems with this approach:**
- ⚠️ **Complex Conditionals**: Nested if-else statements are hard to read and maintain
- ⚠️ **Duplicated Logic**: State transition code repeated in multiple methods
- ⚠️ **Hard to Extend**: Adding new states requires modifying existing methods
- ⚠️ **Violation of SRP**: One class handles all state behaviors
- ⚠️ **Error-Prone**: Easy to miss edge cases or create inconsistent behavior
- ⚠️ **Testing Nightmare**: Must test all state combinations in one class

---

### ✅ **With State Pattern (Clean Encapsulation)**

```java
// Context - Clean and simple
public class BankAccount {
    private AccountState state;
    private double balance;
    
    public BankAccount() {
        this.state = new SilverAccount();
        this.balance = 0;
    }
    
    // Simple delegation - no conditionals!
    public void deposit(double amount) { 
        state.deposit(this, amount); 
    }
    
    public void withdraw(double amount) { 
        state.withdraw(this, amount); 
    }
    
    public void checkBalance() { 
        state.checkBalance(this); 
    }
    
    // Getters and setters for state and balance
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public void setState(AccountState state) { this.state = state; }
}

// State Interface
public interface AccountState {
    void deposit(BankAccount account, double amount);
    void withdraw(BankAccount account, double amount);
    void checkBalance(BankAccount account);
}

// Each state encapsulates its own behavior
public class SilverAccount implements AccountState {
    public void deposit(BankAccount account, double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposited: $" + amount);
        
        if (account.getBalance() >= 1000) {
            account.setState(new GoldAccount());
            System.out.println("Congratulations! Upgraded to Gold account!");
        }
    }
    
    public void withdraw(BankAccount account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient funds!");
        }
        
        if (account.getBalance() < 0) {
            account.setState(new OverdrawnAccount());
            System.out.println("Warning: Account is now overdrawn!");
        }
    }
    
    public void checkBalance(BankAccount account) {
        System.out.println("Silver Account Balance: $" + account.getBalance());
    }
}
```

**Benefits of this approach:**
- ✅ **No Conditionals**: Each state handles its own logic
- ✅ **Single Responsibility**: Each class does one thing well
- ✅ **Easy to Extend**: Add new states without touching existing code
- ✅ **Clear Structure**: State behavior is isolated and obvious
- ✅ **Easy Testing**: Test each state class independently
- ✅ **Maintainable**: Changes to one state don't affect others

---

### 📊 Side-by-Side Comparison

| Aspect | Conditional Logic | State Pattern |
|--------|------------------|---------------|
| **Complexity** | High (nested if-else) | Low (clean delegation) |
| **Maintainability** | Difficult (scattered logic) | Easy (isolated states) |
| **Extensibility** | Hard (modify existing code) | Easy (add new state classes) |
| **Testability** | Complex (test all paths) | Simple (test each state) |
| **Readability** | Poor (conditional soup) | Excellent (clear structure) |
| **SRP Compliance** | Violated (one class does all) | Followed (one class per state) |

---

### 🎯 Real-World Impact

**Scenario**: You need to add a new "Platinum" account tier for balances over $10,000.

**Without State Pattern:**
```java
// Must modify EVERY method with new conditionals!
public void withdraw(double amount) {
    if (accountType.equals("OVERDRAWN")) { ... }
    else if (accountType.equals("SILVER")) { ... }
    else if (accountType.equals("GOLD")) { ... }
    else if (accountType.equals("PLATINUM")) { // New conditionals everywhere!
        // New logic here
    }
}
// Same changes needed in deposit(), checkBalance(), and any other methods!
```

**With State Pattern:**
```java
// Simply create ONE new class!
public class PlatinumAccount implements AccountState {
    public void deposit(BankAccount account, double amount) {
        // Platinum-specific logic
    }
    
    public void withdraw(BankAccount account, double amount) {
        // Platinum-specific logic
    }
    
    public void checkBalance(BankAccount account) {
        System.out.println("Platinum Account Balance: $" + 
                         account.getBalance() + " 💎");
    }
}
// No changes to existing states or context needed!
```

---

### 💡 Key Takeaway

> **Conditional Logic** = "One giant method knows everything about every state"  
> **State Pattern** = "Each state knows only about itself and transitions naturally"

This comparison shows why the State Pattern is essential—it provides clarity, extensibility, and maintainability that conditional logic simply cannot match.

---

## 📝 Complete Implementation

### 1️⃣ State Interface – `AccountState`

```java
public interface AccountState {
    void deposit(BankAccount account, double amount);
    void withdraw(BankAccount account, double amount);
    void checkBalance(BankAccount account);
}
```

---

### 2️⃣ Concrete State – `SilverAccount`

```java
public class SilverAccount implements AccountState {
    
    @Override
    public void deposit(BankAccount account, double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposited: $" + amount);
        
        // Check for upgrade to Gold
        if (account.getBalance() >= 1000) {
            account.setState(new GoldAccount());
            System.out.println("Congratulations! Upgraded to Gold account!");
        }
    }
    
    @Override
    public void withdraw(BankAccount account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient funds!");
        }
        
        // Check if overdrawn
        if (account.getBalance() < 0) {
            account.setState(new OverdrawnAccount());
            System.out.println("Warning: Account is now overdrawn!");
        }
    }
    
    @Override
    public void checkBalance(BankAccount account) {
        System.out.println("Silver Account Balance: $" + account.getBalance());
    }
}
```

---

### 3️⃣ Concrete State – `GoldAccount`

```java
public class GoldAccount implements AccountState {
    
    @Override
    public void deposit(BankAccount account, double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposited: $" + amount + " (Gold Account)");
    }
    
    @Override
    public void withdraw(BankAccount account, double amount) {
        account.setBalance(account.getBalance() - amount);
        System.out.println("Withdrawn: $" + amount + " (Gold benefits: No insufficient funds check)");
        
        // Check for downgrade
        if (account.getBalance() < 1000 && account.getBalance() >= 0) {
            account.setState(new SilverAccount());
            System.out.println("Account downgraded to Silver");
        }
        
        // Check if overdrawn
        if (account.getBalance() < 0) {
            account.setState(new OverdrawnAccount());
            System.out.println("Warning: Account is now overdrawn!");
        }
    }
    
    @Override
    public void checkBalance(BankAccount account) {
        System.out.println("Gold Account Balance: $" + account.getBalance() + " ⭐");
    }
}
```

---

### 4️⃣ Concrete State – `OverdrawnAccount`

```java
public class OverdrawnAccount implements AccountState {
    
    @Override
    public void deposit(BankAccount account, double amount) {
        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposited: $" + amount);
        
        // Check if account is restored
        if (account.getBalance() > 0) {
            account.setState(new SilverAccount());
            System.out.println("Account restored to Silver status");
        }
    }
    
    @Override
    public void withdraw(BankAccount account, double amount) {
        System.out.println("Cannot withdraw. Account is overdrawn!");
        System.out.println("Please deposit funds to restore account.");
    }
    
    @Override
    public void checkBalance(BankAccount account) {
        System.out.println("Overdrawn Account Balance: $" + account.getBalance() + " ⚠️");
        System.out.println("Warning: Your account is overdrawn!");
    }
}
```

---

### 5️⃣ Context – `BankAccount`

```java
public class BankAccount {
    private AccountState state;
    private double balance;

    public BankAccount() {
        this.state = new SilverAccount();
        this.balance = 0;
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

    // Getters and setters for state management
    public double getBalance() { 
        return balance; 
    }
    
    public void setBalance(double balance) { 
        this.balance = balance; 
    }
    
    public void setState(AccountState state) { 
        this.state = state; 
    }
}
```

---

### 6️⃣ Client Code – `Main`

```java
public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        
        System.out.println("=== Initial State ===");
        account.checkBalance();
        
        System.out.println("\n=== Deposit $500 ===");
        account.deposit(500);
        account.checkBalance();
        
        System.out.println("\n=== Withdraw $100 ===");
        account.withdraw(100);
        account.checkBalance();
        
        System.out.println("\n=== Deposit $600 (Upgrade to Gold) ===");
        account.deposit(600);
        account.checkBalance();
        
        System.out.println("\n=== Withdraw $1200 (Overdrawn) ===");
        account.withdraw(1200);
        account.checkBalance();
        
        System.out.println("\n=== Try to Withdraw $50 (Should Fail) ===");
        account.withdraw(50);
        
        System.out.println("\n=== Deposit $300 (Restore to Silver) ===");
        account.deposit(300);
        account.checkBalance();
    }
}
```

---

## 🔄 Step-by-Step Flow Explanation

### **Step 1: Account Creation**
```
Initial State: SilverAccount
Balance: $0
```

### **Step 2: Deposit $500**
```
Action: deposit(500)
Current State: SilverAccount
→ Balance becomes $500
→ Check: $500 < $1000 → Stay in Silver
Result: SilverAccount, Balance = $500
```

### **Step 3: Withdraw $100**
```
Action: withdraw(100)
Current State: SilverAccount
→ Balance becomes $400
→ Check: $400 >= 0 → Stay in Silver
Result: SilverAccount, Balance = $400
```

### **Step 4: Deposit $600 (Triggers Upgrade)**
```
Action: deposit(600)
Current State: SilverAccount
→ Balance becomes $1000
→ Check: $1000 >= $1000 → UPGRADE to Gold
→ State changed to GoldAccount
Result: GoldAccount, Balance = $1000
```

### **Step 5: Withdraw $1200 (Becomes Overdrawn)**
```
Action: withdraw(1200)
Current State: GoldAccount
→ Gold allows withdrawal even if insufficient
→ Balance becomes -$200
→ Check: -$200 < 0 → CHANGE to Overdrawn
→ State changed to OverdrawnAccount
Result: OverdrawnAccount, Balance = -$200
```

### **Step 6: Attempt Withdrawal (Blocked)**
```
Action: withdraw(50)
Current State: OverdrawnAccount
→ Overdrawn state blocks withdrawals
→ Display error message
Result: OverdrawnAccount, Balance = -$200 (unchanged)
```

### **Step 7: Deposit $300 (Restore Account)**
```
Action: deposit(300)
Current State: OverdrawnAccount
→ Balance becomes $100
→ Check: $100 > 0 → RESTORE to Silver
→ State changed to SilverAccount
Result: SilverAccount, Balance = $100
```

---

## ✅ Output

```
=== Initial State ===
Silver Account Balance: $0.0

=== Deposit $500 ===
Deposited: $500.0
Silver Account Balance: $500.0

=== Withdraw $100 ===
Withdrawn: $100.0
Silver Account Balance: $400.0

=== Deposit $600 (Upgrade to Gold) ===
Deposited: $600.0 (Gold Account)
Congratulations! Upgraded to Gold account!
Gold Account Balance: $1000.0 ⭐

=== Withdraw $1200 (Overdrawn) ===
Withdrawn: $1200.0 (Gold benefits: No insufficient funds check)
Warning: Account is now overdrawn!
Overdrawn Account Balance: $-200.0 ⚠️
Warning: Your account is overdrawn!

=== Try to Withdraw $50 (Should Fail) ===
Cannot withdraw. Account is overdrawn!
Please deposit funds to restore account.

=== Deposit $300 (Restore to Silver) ===
Deposited: $300.0
Account restored to Silver status
Silver Account Balance: $100.0
```

---

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming
- Behavioral Design Pattern
- Polymorphism and Interfaces

---

## 📚 Example Explanation

In this example:
- `BankAccount` is the **Context** that delegates operations to states
- `AccountState` is the **State Interface** defining behavior contract
- `SilverAccount`, `GoldAccount`, `OverdrawnAccount` are **Concrete States** with specific logic
- States handle their own behavior and trigger transitions
- The client (`Main`) interacts only with the context, unaware of state changes

---

## ✅ Advantages

- Eliminates conditional statements for state logic
- Encapsulates state behavior in separate classes
- Makes adding new states easier (Open/Closed Principle)
- Dynamic behavior change at runtime
- Improves code organization and readability
- Each state can be tested independently

---

## ❌ Disadvantages

- Can increase the number of classes for each state
- Slightly more complex initial setup
- State transitions logic distributed across state classes
- May be overkill for simple state machines with few states

---

## 🎓 Use Cases

- **Financial Systems**: Account states (Active, Frozen, Overdrawn, Premium)
- **E-commerce**: Order states (Pending, Paid, Shipped, Delivered, Cancelled)
- **Game Development**: Character states (Idle, Walking, Running, Jumping, Dead)
- **Document Editing**: Document states (Draft, Review, Published, Archived)
- **Network Connections**: Connection states (Connecting, Connected, Disconnected)
- **Vending Machines**: Machine states (Idle, HasMoney, Dispensing, SoldOut)
- **Traffic Lights**: Light states (Red, Yellow, Green)

---

## 🏁 Conclusion

The **State Pattern** allows objects to change behavior dynamically based on their internal state. In this example, a **Bank Account** changes its behavior based on its balance, transitioning between Silver, Gold, and Overdrawn states seamlessly.

By eliminating complex conditional logic and encapsulating state-specific behavior in separate classes, the pattern creates a clean, maintainable, and extensible solution. Each state handles its own operations and manages transitions based on business rules, resulting in code that is easier to understand, test, and modify.

The State Pattern demonstrates that good design isn't about avoiding complexity—it's about organizing it in a way that makes sense.

---

## ✍️ Author

**Mazen Naji**  
Software Engineer | Full Stack Developer

---

## 📄 License

Educational use only