# 🏭 Abstract Factory Pattern (Java)

## 📌 Overview
The **Abstract Factory Pattern** is a **creational design pattern** that **provides an interface to create families of related objects** without specifying their concrete classes.  

Think of it like:
> “I want a family of products (buttons, checkboxes, etc.) for a platform, but I don’t want to care about their specific implementation.”

This pattern is especially useful when you have **multiple products that must be used together consistently** (like Mac vs Windows GUI components).

---

## 🎯 Intent
- Provide an **interface for creating families of related objects**
- Ensure products from the same family are **compatible**
- Decouple client code from concrete classes
- Facilitate adding **new families** easily

---

## 🧠 Key Idea
> **“Use a factory for a family, not for a single product.”**

---

## ⏱️ When to Use
Use Abstract Factory when:

* You need **multiple related products** (buttons, checkboxes, dialogs)
* You want to **enforce consistency** among products
* You want to **decouple client code** from concrete implementations
* You plan to **add new families of products** in the future

---

## 🧩 Participants

| Component | Role |
|-----------|------|
| AbstractFactory | Declares creation methods for each product type |
| ConcreteFactory | Implements creation methods for a product family |
| AbstractProduct | Declares interface for product (e.g., Button, Checkbox) |
| ConcreteProduct | Implements AbstractProduct (e.g., MacButton, WindowsCheckbox) |
| Client | Uses AbstractFactory to create products without knowing concrete classes |

---

## 📐 UML Class Diagram (Abstract Factory Pattern)
```text
       ┌───────────────┐
       │     Client    │
       └───────────────┘
                │
                ▼
       ┌────────────────┐
       │ AbstractFactory│
       │ +createButton()│
       │ +createCheckbox()│
       └────────────────┘
         ▲            ▲
         │            │
   MacFactory      WindowsFactory
     createButton()   createButton()
     createCheckbox() createCheckbox()
         │            │
   ┌───────────┐   ┌───────────┐
   │ MacButton │   │ WinButton │
   │ MacCheckbox│  │ WinCheckbox│
   └───────────┘   └───────────┘
````

---

## 🧠 UML Diagram Explanation

### 1️⃣ AbstractFactory

* Declares **factory methods** for each type of product
* Client depends only on this interface

### 2️⃣ ConcreteFactory

* Implements creation methods for a **specific family**
* Ensures products of the same family are compatible

### 3️⃣ AbstractProduct

* Declares **interface for product types**
* All concrete products implement this

### 4️⃣ ConcreteProduct

* Implements the abstract product interface
* Represents a **specific product in a family**

### 5️⃣ Client

* Uses **AbstractFactory** to create products
* Does **not know concrete classes**
* Maintains consistency among products of the same family

---

## 🎮 Example Scenario

### ❌ Without Abstract Factory

```java
Button button = new MacButton();
Checkbox checkbox = new WindowsCheckbox(); // mix of families
```

**Problems:**

* Client tightly coupled to concrete classes
* Products from different families may not be compatible
* Hard to switch entire family

### ✅ With Abstract Factory

```java
GUIFactory factory = new MacFactory();
Button button = factory.createButton();
Checkbox checkbox = factory.createCheckbox();
```

**Benefits:**

* Client decoupled from concrete classes
* Products belong to the same family
* Switching families requires only changing the factory
* Easy to extend new families (LinuxFactory, AndroidFactory, etc.)

---

## 🔄 Advantages

* Ensures **product consistency**
* Supports **Open/Closed Principle**
* Decouples **client code from concrete implementations**
* Easy to **add new families**
* Promotes **scalability and maintainability**

---

## ❌ Disadvantages

* Can create **more classes** (one factory per family, one product per type)
* Slightly **complex structure**
* Overkill if there is only **one family or one product type**

---

## 🛠️ Best Practices

### Do's ✓

* Use for **families of related products**
* Keep factories **consistent**
* Combine with **Builder or Prototype** for complex products
* Use **interfaces** for all product types

### Don'ts ✗

* Don’t use if there is **only one product type**
* Avoid **overengineering** for simple projects
* Don’t mix products from **different families**

---

## 🔄 Related Patterns

* **Abstract Factory vs Factory Method:** Abstract Factory creates **families of products**, Factory Method creates **single product type**
* **Abstract Factory vs Builder:** Builder constructs **complex object step by step**, Abstract Factory provides **family of products**

---

## 📝 Exercise Description

**Scenario:**
Build a GUI system supporting multiple platforms (Mac, Windows) with consistent UI elements.

**Requirements:**

1. Support multiple families of products (Mac, Windows)
2. Support multiple product types (Button, Checkbox)
3. Client should be **decoupled** from concrete classes
4. Easy to **add new families**

---

## 📝 Summary

The **Abstract Factory Pattern** provides a **flexible way to create families of related products**, decoupling client code from concrete classes while ensuring consistency.

### Key Takeaways

* Create families, not single products
* Client uses **abstract factory**, not concrete classes
* Supports Open/Closed Principle
* Ideal for GUI systems, cross-platform apps, or product families

> **Abstract Factory Pattern = One factory per family, consistent product creation**

---

## 🚀 How to Run

```bash
javac *.java
java Main.java
```

Expected output:

```
Mac GUI:
Rendering a Mac-style Button
Rendering a Mac-style Checkbox

Windows GUI:
Rendering a Windows-style Button
Rendering a Windows-style Checkbox
```

---

## ✍️ Author
**Mazen Naji**  
Software Engineer | Full Stack Developer

