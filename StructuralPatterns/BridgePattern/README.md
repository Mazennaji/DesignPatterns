# 🌉 Bridge Design Pattern (Java)

## 📌 Overview

The **Bridge Pattern** is a **structural design pattern** that **decouples abstraction from implementation**, allowing both to **vary independently**.

Instead of creating a rigid inheritance hierarchy where abstraction and implementation are tightly coupled, the Bridge Pattern uses **composition** to connect them, enabling flexibility and reducing class explosion.

This pattern is especially useful when you have **multiple dimensions of variation** and want to avoid creating a class for every possible combination.

---

## 🎯 Intent

* Decouple abstraction from implementation
* Allow both to evolve independently
* Avoid exponential class growth (m × n problem)
* Enable runtime selection of implementation
* Use composition over inheritance

---

## 🧠 Key Idea

> "Separate what you process from how you process it."

---

## ⏱️ When to Use

Use the Bridge Pattern when:

* You want to **avoid permanent binding** between abstraction and implementation
* Both abstraction and implementation need to be **extensible** independently
* Changes in implementation shouldn't **impact clients**
* You have **multiple dimensions** of variation
* You need **runtime flexibility** in choosing implementation
* You want to **share implementation** among multiple abstractions

---

## 🧩 Participants

| Component              | Role                                                |
| ---------------------- | --------------------------------------------------- |
| Abstraction            | Defines high-level control logic                    |
| RefinedAbstraction     | Extends abstraction with specialized behavior       |
| Implementor            | Defines interface for implementation classes        |
| ConcreteImplementor    | Provides specific implementation                    |

---

## 📐 UML Class Diagram (Bridge Pattern)

```text
            ┌───────────────────┐
            │      Client       │
            └───────────────────┘
                     │
                     │ uses
                     ▼
         ┌─────────────────────────┐
         │   PaymentProcessor      │
         ├─────────────────────────┤
         │ -method: PaymentMethod  │◄───────────────┐
         ├─────────────────────────┤                │
         │ +processPayment(amount) │                │ composition
         └─────────────────────────┘                │
                     ▲                              │
                     │                              │
        ┌────────────┴────────────┐                 │
        │                         │                 │
┌───────────────────┐   ┌─────────────────────┐     │
│ OnlinePayment     │   │ RecurringPayment    │     │
├───────────────────┤   ├─────────────────────┤     │
│ +processPayment() │   │ +processPayment()   │     │
│                   │   │ +setupSchedule()    │     │
└───────────────────┘   └─────────────────────┘     │
                                                    │
                        ┌───────────────────────────┘
                        │
                        ▼
            ┌─────────────────────────┐
            │   <<interface>>         │
            │   PaymentMethod         │
            ├─────────────────────────┤
            │ +pay(amount)            │
            │ +getMethodName()        │
            └─────────────────────────┘
                     ▲
                     │
        ┌────────────┼────────────┐
        │            │            │
┌───────┴────────┐ ┌─┴──────────┐ ┌─┴──────────────────┐
│ CreditCard     │ │ PayPal     │ │ Cryptocurrency     │
│ Payment        │ │ Payment    │ │ Payment            │
├────────────────┤ ├────────────┤ ├────────────────────┤
│ +pay()         │ │ +pay()     │ │ +pay()             │
│ +getMethodName │ │ +getName() │ │ +getMethodName()   │
└────────────────┘ └────────────┘ └────────────────────┘
```

---

## 🧠 UML Diagram Explanation

### 1️⃣ Abstraction (PaymentProcessor)

* Defines **high-level payment operations**
* Maintains a **reference** to PaymentMethod (bridge)
* Delegates actual payment to the implementor
* Client interacts with this layer

---

### 2️⃣ Refined Abstraction (OnlinePayment, RecurringPayment)

* **Extends** payment processor behavior
* Adds **specialized operations** (like scheduling)
* May add validation or pre-processing
* Still delegates core payment to implementor

---

### 3️⃣ Implementor (PaymentMethod Interface)

* Defines **interface** for payment methods
* Represents **how** payment is processed
* Independent of payment processor type
* Can be implemented by various payment providers

---

### 4️⃣ Concrete Implementor (CreditCard, PayPal, Crypto)

* Provides **actual payment logic**
* Implements provider-specific details
* Can be **switched at runtime**
* Reusable across different payment processors

---

## 🔗 Relationships Summary

* PaymentProcessor **has-a** PaymentMethod (composition/bridge)
* Refined processors **extend** PaymentProcessor
* Concrete methods **implement** PaymentMethod interface
* Client **depends only** on PaymentProcessor abstraction
* Implementation **switchable at runtime**

---

## ✅ Key Design Benefits

* **Independent Evolution**: Both hierarchies grow separately
* **No Class Explosion**: Linear (m+n) instead of exponential (m×n)
* **Runtime Flexibility**: Change payment method dynamically
* **Hide Implementation**: Client sees only abstraction
* **Loose Coupling**: Changes don't ripple through system

---

## 🔄 Bridge Pattern vs. No Bridge

### ❌ Without Bridge Pattern (Class Explosion)

```java
// Need a class for EVERY combination!
class OnlineCreditCardPayment {}
class OnlinePayPalPayment {}
class OnlineCryptoPayment {}
class RecurringCreditCardPayment {}
class RecurringPayPalPayment {}
class RecurringCryptoPayment {}

// 2 payment types × 3 payment methods = 6 classes!
// Add 1 more payment type? Need 3 MORE classes!
// Add 1 more payment method? Need 2 MORE classes!
```

**Problems:**

* ❌ Exponential class growth (m × n)
* ❌ Massive code duplication
* ❌ Hard to maintain and extend
* ❌ Rigid and inflexible
* ❌ Violates DRY principle

---

### ✅ With Bridge Pattern (Separation of Concerns)

```java
// Payment Processors (2 classes)
class OnlinePayment {}
class RecurringPayment {}

// Payment Methods (3 classes)
class CreditCardPayment {}
class PayPalPayment {}
class CryptocurrencyPayment {}

// Total: 2 + 3 = 5 classes
// Add 1 more payment type? Just 1 MORE class!
// Add 1 more payment method? Just 1 MORE class!
// Any combination works!
```

**Benefits:**

* ✅ Linear growth (m + n)
* ✅ No code duplication
* ✅ Easy to extend either dimension
* ✅ Flexible combinations
* ✅ Clean separation of concerns

---

## 📊 Class Count Comparison

| Payment Types | Methods | Without Bridge | With Bridge | Savings |
| ------------- | ------- | -------------- | ----------- | ------- |
| 2             | 3       | 6              | 5           | 17%     |
| 3             | 3       | 9              | 6           | 33%     |
| 3             | 4       | 12             | 7           | 42%     |
| 4             | 5       | 20             | 9           | 55%     |
| 5             | 5       | 25             | 10          | 60%     |
| 10            | 10      | 100            | 20          | 80%     |

**Formula:**
- Without Bridge: `m × n` classes (EXPONENTIAL)
- With Bridge: `m + n` classes (LINEAR)

---

## 🎯 Real-World Use Cases

### 1. Payment Processing 💳

```java
// Different payment types with different methods
PaymentProcessor payment = new OnlinePayment(new CreditCardPayment());
```

Process one-time or recurring payments via credit card, PayPal, crypto, etc.

---

### 2. Database Abstraction 🗄️

```java
// Different query types with different databases
Query query = new SelectQuery(new MySQLDatabase());
```

---

### 3. Graphics Rendering 🎨

```java
// Different shapes with different renderers
Shape circle = new Circle(new VectorRenderer());
```

---

### 4. Document Converters 📄

```java
// Different document types with different formats
Document doc = new Report(new PDFConverter());
```

---

### 5. Messaging Systems 📨

```java
// Different message types with different platforms
Message msg = new TextMessage(new SlackPlatform());
```

---

## ✅ Advantages

* **Independent Extension**: Extend abstraction OR implementation separately
* **No Class Explosion**: m+n instead of m×n classes
* **Runtime Flexibility**: Change implementation on the fly
* **Hide Implementation Details**: Client only sees abstraction
* **Improved Maintainability**: Changes isolated to one hierarchy
* **Reusability**: Implementations shared across abstractions

---

## ❌ Disadvantages

* **Increased Complexity**: More classes and indirection
* **Initial Design Effort**: Requires upfront planning
* **May Be Overkill**: For simple single-dimension variations
* **Learning Curve**: Harder to grasp than simple inheritance

---

## 🛠️ Best Practices

### Do's ✓

* Use when you have **two orthogonal dimensions** of variation
* Pass implementation through **constructor** (dependency injection)
* Keep abstraction and implementation **interfaces minimal**
* Make implementation **platform/provider-specific**
* Use when you need **runtime implementation switching**

### Don'ts ✗

* Don't use for **single-dimension** variations (use Strategy instead)
* Don't make abstraction **depend on concrete** implementations
* Don't over-engineer **simple hierarchies**
* Don't confuse with **Adapter** pattern (different intent)

---

## 🔄 Related Patterns

### Bridge vs Adapter

* **Bridge**: Designed upfront for separate evolution
* **Adapter**: Applied after to make incompatible interfaces work

### Bridge vs Strategy

* **Bridge**: Two hierarchies (abstraction + implementation)
* **Strategy**: One hierarchy (interchangeable algorithms)

### Bridge vs Abstract Factory

* **Bridge**: Separates interface from implementation
* **Abstract Factory**: Creates families of related objects

---

## 📝 Exercise Description

**Scenario**: Build a **payment processing system** that supports different payment types and payment methods.

**Requirements**:
1. Support **payment types**: Online (one-time), Recurring (subscription)
2. Support **payment methods**: Credit Card, PayPal, Cryptocurrency
3. Any payment type should work with **any payment method**
4. Easy to add **new payment types** or **new payment methods**
5. Allow **runtime switching** of payment methods

**Your Task**:
Implement the Bridge pattern to avoid creating 6 classes (2×3 combinations).

---

## 📝 Summary

The **Bridge Pattern** prevents class explosion by separating abstraction from implementation, allowing both hierarchies to vary independently through composition.

### Key Takeaways

* **Decouple** "what you do" from "how you do it"
* **Composition** over inheritance for multi-dimensional variation
* **Linear growth** (m+n) beats exponential growth (m×n)
* Perfect for **platform-independent** abstractions
* Essential when **both dimensions** evolve independently

> **Bridge Pattern = Two independent hierarchies connected by composition**

---

## 🚀 How to Run

1. Compile all Java files:
   ```bash
   javac *.java
   ```

2. Run the Main class:
   ```bash
   java Main.java
   ```

3. Observe:
   * Different payment processors with different methods
   * Runtime switching of payment methods
   * Class explosion prevention (2+3=5 vs 2×3=6)
   * Flexibility in combinations

---

## ✍️ Author
**Mazen Naji**  
Software Engineer | Full Stack Developer