# 🔒 Singleton Design Pattern (Java)

## 📌 Overview
The **Singleton Pattern** is a **creational design pattern** that ensures a class **has only one instance** and provides a **global access point** to that instance.

Think of it like:
> "No matter how many times you call me, I’ll always be the same object."

This pattern is especially useful when **exactly one object is needed** to coordinate actions across a system, like configuration managers, logging, caching, or thread pools.

---

## 🎯 Intent
- Ensure only one instance of a class exists
- Provide a **single, global access point**
- Control access to shared resources
- Prevent multiple instances and inconsistent state

---

## 🧠 Key Idea
> **“One class, one instance, accessible globally.”**

---

## ⏱️ When to Use
Use Singleton when:

* Only **one instance** should exist in the system
* You need a **global point of access**
* Object creation is **expensive** and should be reused
* Shared resources need **controlled access**
* You want to **avoid global variables** but still allow centralized access

---

## 🧩 Participants

| Component | Role |
|-----------|------|
| Singleton | Defines a static `getInstance()` method, keeps one private instance |
| Client    | Uses `getInstance()` to access the Singleton object |

---

## 📐 UML Class Diagram (Singleton Pattern)
```text
        ┌───────────────┐
        │    Client     │
        └───────────────┘
                 │
                 ▼
        ┌──────────────────┐
        │   Singleton      │
        ├──────────────────┤
        │ - instance: Singleton │
        ├──────────────────┤
        │ + getInstance(): Singleton │
        │ + showMessage(): void      │
        └──────────────────┘
````

---

## 🧠 UML Diagram Explanation

### 1️⃣ Singleton

* Maintains a **single private instance**
* Provides **global access** through `getInstance()`
* Constructor is **private** to prevent external instantiation
* Can use **lazy initialization** (instance created only when first needed)
* Thread-safe with **synchronized access** if needed

---

### 2️⃣ Client

* Calls `Singleton.getInstance()` instead of `new Singleton()`
* Cannot create a new instance directly
* All clients share the same object

---

## 🎮 Example Scenario

### ❌ Without Singleton

```java
ConfigManager config1 = new ConfigManager();
ConfigManager config2 = new ConfigManager();
```

**Problems:**

* Multiple instances may exist
* State can become inconsistent
* Hard to coordinate shared resources

---

### ✅ With Singleton

```java
ConfigManager config = ConfigManager.getInstance();
```

**Benefits:**

* Only one instance exists
* Global access point
* Controlled resource usage
* Consistent state

---

## 🔄 Eager vs Lazy Initialization

| Type  | Description                           | Pros                    | Cons                              |
| ----- | ------------------------------------- | ----------------------- | --------------------------------- |
| Eager | Instance created at class loading     | Simple, thread-safe     | Wasteful if never used            |
| Lazy  | Instance created when first requested | Efficient, saves memory | Needs synchronization for threads |

---

## 🎯 Real-World Use Cases

* Logging systems 📝
* Configuration managers ⚙️
* Database connection pools 🗄️
* Caching services ⚡
* Thread pool managers 🧵

---

## ✅ Advantages

* Guarantees **single instance**
* Global access point for all clients
* Reduces memory footprint
* Controlled access to shared resources
* Easy to extend for subclasses (carefully)

---

## ❌ Disadvantages

* **Global state** can be abused
* **Thread safety** requires careful design
* Can make testing harder (mocking singletons is tricky)
* Potential hidden dependencies (tight coupling)

---

## 🛠️ Best Practices

### Do's ✓

* Use for **shared resources** or **central managers**
* Prefer **lazy initialization** if instance creation is heavy
* Ensure **thread safety** in multithreaded environments
* Keep constructor **private**

### Don'ts ✗

* Don’t use for **every class** — avoid overusing singletons
* Don’t store mutable global state unless necessary
* Don’t confuse Singleton with **static classes** (Singleton allows inheritance)
* Don’t ignore multithreading concerns

---

## 🔄 Related Patterns

### Singleton vs Factory

* Singleton controls **instance count** (1)
* Factory **creates multiple instances** as needed

### Singleton vs Prototype

* Singleton always **reuses the same object**
* Prototype always **creates clones**

---

## 📝 Exercise Description

**Scenario:**
Build a system that requires a **single configuration manager** shared across multiple modules.

**Requirements:**

1. Only **one instance** of configuration manager exists
2. Provide **global access**
3. Prevent multiple instantiations
4. Demonstrate usage with **Main.java**

---

## 📝 Summary

The **Singleton Pattern** ensures a class **has exactly one instance** and provides a **global access point** to it.

### Key Takeaways

* One object, one reference
* Private constructor + global access
* Lazy or eager initialization
* Thread safety matters
* Ideal for shared managers, caches, and global services

> **Singleton Pattern = Single instance, global access, controlled creation**

---

## 🚀 How to Run

```bash
javac *.java
java Main.java
```

Expected output:

```
Singleton instance created!
Hello from the Singleton instance!
Hello from the Singleton instance!
Both references point to the same Singleton instance!
```

---

## ✍️ Author
**Mazen Naji**  
Software Engineer | Full Stack Developer
