# 🏗️ Builder Pattern (Java)

## 📌 Overview
The **Builder Pattern** is a **creational design pattern** that separates the **construction of a complex object** from its **representation**, allowing the same construction process to create different representations.

Think of it like:
> “I have a blueprint for building a product, but I can customize its parts step by step without overloading constructors.”

This pattern is especially useful when **objects have many optional or mandatory parts** and you want **readable and maintainable construction code**.

---

## 🎯 Intent
- Separate object construction from representation
- Build complex objects **step by step**
- Allow **different representations** with the same building process
- Avoid long constructors with too many parameters

---

## 🧠 Key Idea
> **“Director directs, Builder builds, Product receives.”**

---

## ⏱️ When to Use
Use the Builder Pattern when:

* The object has **many parts or optional parameters**
* You want **readable and maintainable construction**
* The construction process must be **flexible and reusable**
* You want **different variations of the object** without constructor overloads

---

## 🧩 Participants

| Component | Role |
|-----------|------|
| Product | The complex object being built |
| Builder | Interface for creating parts of Product |
| ConcreteBuilder | Implements Builder interface; builds and assembles Product |
| Director | Controls building process; delegates to Builder |
| Client | Uses Director and Builder to create Product |

---

## 📐 UML Class Diagram (Builder Pattern)
```text
        ┌───────────────┐
        │    Client     │
        └───────────────┘
                 │
                 ▼
             ┌─────────┐
             │ Director│
             └─────────┘
                 │
                 ▼
       ┌────────────────────┐
       │      Builder       │
       │ +buildPartA()      │
       │ +buildPartB()      │
       │ +buildPartC()      │
       │ +getProduct()      │
       └────────────────────┘
                 ▲
       ┌─────────┴─────────┐
       │                   │
ConcreteProductBuilder   AnotherBuilder
       │
       ▼
     Product
````

---

## 🧠 UML Diagram Explanation

### 1️⃣ Product

* Represents the complex object being constructed
* Contains multiple parts (A, B, C)
* Has a method to display its components

### 2️⃣ Builder

* Declares abstract interface for building parts
* Ensures all builders implement consistent steps

### 3️⃣ ConcreteBuilder

* Implements Builder interface
* Assembles Product step by step
* Returns the finished Product

### 4️⃣ Director

* Controls **order of construction**
* Delegates building to Builder
* Produces different representations using the same steps

### 5️⃣ Client

* Creates Builder and Director
* Initiates construction process
* Retrieves the final Product

---

## 🎮 Example Scenario

### ❌ Without Builder

```java
Product product = new Product("partA", "partB", "partC");
// complex constructor with many parameters, hard to read
```

**Problems:**

* Hard to maintain and read
* Adding optional parts requires constructor overloads
* Violates single responsibility if construction logic spreads

### ✅ With Builder

```java
ProductBuilder builder = new ConcreteProductBuilder();
Director director = new Director(builder);
Product product = director.construct();
```

**Benefits:**

* Clear, step-by-step construction
* Optional parts can be skipped easily
* Different builders create different product variations
* Readable, maintainable, and flexible

---

## 🔄 Advantages

* Separates **construction from representation**
* Avoids **complex constructors**
* Flexible for **different product variations**
* Improves **code readability** and maintainability
* Supports **step-by-step object creation**

---

## ❌ Disadvantages

* Adds **extra classes** (Director, Builder, ConcreteBuilder)
* Slightly **more complex structure**
* Overkill for **simple objects with few parameters**

---

## 🛠️ Best Practices

### Do's ✓

* Use when creating **complex objects with many parts**
* Use **Director** to control construction order
* Keep **Builder interface minimal**
* Combine with **Prototype** for reusable components

### Don'ts ✗

* Don’t use for **simple objects** with 1–2 parameters
* Avoid overengineering for small projects
* Don’t mix Builder with unrelated responsibilities

---

## 🔄 Related Patterns

* **Builder vs Factory Method:** Factory creates **one product**, Builder constructs **complex object step by step**
* **Builder vs Prototype:** Prototype clones existing objects, Builder creates from scratch
* **Builder vs Abstract Factory:** Abstract Factory creates **families of related products**, Builder assembles **one complex product**

---

## 📝 Exercise Description

**Scenario:**
Build a product with multiple configurable parts (PartA, PartB, PartC) using Builder Pattern.

**Requirements:**

1. Construct product **step by step**
2. Separate construction logic from representation
3. Demonstrate usage via **Director and Builder**
4. Support different product variations if needed

---

## 📝 Summary

The **Builder Pattern** provides a **flexible, step-by-step way to construct complex objects**, improving readability, maintainability, and flexibility.

### Key Takeaways

* Separate **construction from representation**
* Use **Director to control order**
* Use **Builder for optional/complex parts**
* Ideal for configurable or complex objects

> **Builder Pattern = Director directs, Builder builds, Product receives**

---

## 🚀 How to Run

```bash
javac *.java
java Main.java
```

Expected output:

```
Product Parts:
PartA: PartA built by ConcreteProductBuilder
PartB: PartB built by ConcreteProductBuilder
PartC: PartC built by ConcreteProductBuilder
```

---
## ✍️ Author
**Mazen Naji**  
Software Engineer | Full Stack Developer  

