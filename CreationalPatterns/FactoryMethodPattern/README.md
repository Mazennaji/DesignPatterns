# 🏭 Factory Method Pattern (Java)

## 📌 Overview
The **Factory Method Pattern** is a **creational design pattern** that **defines an interface for creating objects**, but allows subclasses to **decide which class to instantiate**.  

Think of it like:
> “I have a blueprint for creating things, but the exact type can vary depending on the subclass.”

This pattern helps you **decouple object creation from usage**, making your code **more flexible, maintainable, and extensible**.

---

## 🎯 Intent
- Define an **interface for creating objects**
- Let **subclasses choose which class to instantiate**
- Decouple **client code from concrete classes**
- Allow adding **new product types** easily

---

## 🧠 Key Idea
> **“Don’t hardcode the object type — let subclasses decide.”**

---

## ⏱️ When to Use
Use the Factory Method Pattern when:

* You don’t know in advance the **exact type of object** the client needs
* You want **loose coupling** between client and product
* You plan to **add new product types** in the future
* You want **centralized creation logic** per subclass

---

## 🧩 Participants

| Component | Role |
|-----------|------|
| Product | Defines interface of objects the factory creates |
| ConcreteProduct | Implements Product |
| Creator | Declares factory method (abstract) |
| ConcreteCreator | Implements factory method to return a ConcreteProduct |
| Client | Uses Creator to get Product without knowing its type |

---

## 📐 UML Class Diagram (Factory Method Pattern)
```text
        ┌───────────────┐
        │     Client    │
        └───────────────┘
                 │
                 ▼
        ┌───────────────┐
        │    Creator    │
        ├───────────────┤
        │ +createProduct() │ (abstract)
        │ +performAction() │
        └───────────────┘
                 ▲
        ┌────────┴────────┐
        │                 │
ConcreteCreatorA      ConcreteCreatorB
  createProduct()        createProduct()
       │                    │
       ▼                    ▼
ConcreteProductA       ConcreteProductB
       use()                 use()
````

---

## 🧠 UML Diagram Explanation

### 1️⃣ Product

* Declares the interface of objects created by factory methods
* All concrete products implement this interface

### 2️⃣ ConcreteProduct

* Implements the Product interface
* Represents actual object created by the factory

### 3️⃣ Creator

* Declares **factory method** (abstract)
* Can contain **default behavior** (`performAction`) using the product

### 4️⃣ ConcreteCreator

* Overrides the factory method to instantiate a **ConcreteProduct**
* Each subclass decides which product type to create

### 5️⃣ Client

* Uses **Creator** abstraction
* Does not know or care about concrete product class
* Calls `performAction()` or factory method indirectly

---

## 🎮 Example Scenario

### ❌ Without Factory Method

```java
Product product = new ConcreteProductA(); // hardcoded
product.use();
```

**Problems:**

* Client is tightly coupled to concrete class
* Adding new products requires modifying client
* Violates Open/Closed Principle

### ✅ With Factory Method

```java
Creator creator = new ConcreteCreatorB();
creator.performAction(); // dynamically creates ConcreteProductB
```

**Benefits:**

* Client code is independent of concrete product
* Easy to add new product types
* Creation logic centralized in subclasses
* Supports polymorphic behavior

---

## 🔄 Advantages

* **Decouples creation from usage**
* **Supports Open/Closed Principle** (add new products without modifying client)
* **Centralized creation logic** in subclasses
* **Flexible and extensible** for future product types

---

## ❌ Disadvantages

* Can create **more classes** (one creator per product type)
* Slightly more **complex structure** than simple instantiation
* Overhead if only one product type exists

---

## 🛠️ Best Practices

### Do's ✓

* Use when you need **polymorphic object creation**
* Keep **Creator interface simple**
* Use subclasses to **specialize product creation**
* Combine with **Prototype** if products are costly to create

### Don'ts ✗

* Don’t use if you only have **one type of product**
* Avoid **overengineering** for small, simple projects
* Don’t hardcode object creation in client code

---

## 🔄 Related Patterns

* **Factory Method vs Simple Factory**: Factory Method is subclass-based; Simple Factory is centralized static method
* **Factory Method vs Abstract Factory**: Factory Method creates **one type of product**, Abstract Factory creates **families of related products**

---

## 📝 Exercise Description

**Scenario:**
Build a **product creation system** where different creators produce different types of products.

**Requirements:**

1. Support multiple product types (e.g., A, B)
2. Client should **not know concrete product classes**
3. Easy to add **new product types and creators**
4. Demonstrate usage with **Main.java**

---

## 📝 Summary

The **Factory Method Pattern** allows **subclasses to control object creation**, promoting flexibility, loose coupling, and maintainability.

### Key Takeaways

* Decouple client from concrete classes
* Centralize creation logic in Creator subclasses
* Supports Open/Closed Principle
* Ideal for polymorphic object creation

> **Factory Method Pattern = Let subclasses decide which objects to create**

---

## 🚀 How to Run

```bash
javac *.java
java Main.java
```

Expected output:

```
Using CreatorA:
Using ConcreteProductA
Using CreatorB:
Using ConcreteProductB
```

---

## ✍️ Author
**Mazen Naji**  
Software Engineer | Full Stack Developer  

