# 🧬 Prototype Design Pattern (Java)

## 📌 Overview

The **Prototype Pattern** is a **creational design pattern** that allows you to **create new objects by copying (cloning) existing ones**, instead of instantiating them from scratch.

Rather than relying on constructors, the Prototype Pattern delegates object creation to the object itself, making the system **more flexible**, **faster**, and **less coupled** to concrete classes.

This pattern is especially useful when object creation is **expensive**, **complex**, or **configuration-heavy**.

---

## 🎯 Intent

* Create new objects by **cloning existing ones**
* Avoid costly or complex object construction
* Reduce dependency on concrete classes
* Improve performance by reusing existing objects
* Hide creation logic from the client

---

## 🧠 Key Idea

> **“Don’t build from zero — copy a proven template.”**

---

## ⏱️ When to Use

Use the Prototype Pattern when:

* Object creation is **expensive** (time, memory, resources)
* Objects require **many configuration steps**
* You want to **avoid complex constructors**
* You need **many similar objects**
* You want to **decouple client code from concrete classes**
* Objects should be created **at runtime dynamically**

---

## 🧩 Participants

| Component         | Role                            |
| ----------------- | ------------------------------- |
| Prototype         | Declares the `clone()` method   |
| ConcretePrototype | Implements cloning logic        |
| Client            | Creates new objects via cloning |

---

## 📐 UML Class Diagram (Prototype Pattern)

```text
        ┌─────────────────────┐
        │       Client        │
        └─────────────────────┘
                   │
                   │ clones
                   ▼
        ┌─────────────────────┐
        │   <<interface>>     │
        │     Prototype      │
        ├─────────────────────┤
        │ +clone()            │
        └─────────────────────┘
                   ▲
                   │
        ┌─────────────────────┐
        │  GameCharacter      │
        ├─────────────────────┤
        │ -name               │
        │ -health             │
        │ -attackPower        │
        ├─────────────────────┤
        │ +clone()            │
        │ +display()          │
        └─────────────────────┘
```

---

## 🧠 UML Diagram Explanation

### 1️⃣ Prototype (Prototype Interface)

* Declares the `clone()` method
* Defines the contract for cloning objects
* Does **not** care about concrete class details

---

### 2️⃣ Concrete Prototype (GameCharacter)

* Implements the `clone()` method
* Contains the actual object state
* Responsible for copying itself
* Can use:

  * Copy constructor
  * Shallow copy
  * Deep copy

---

### 3️⃣ Client

* Uses `clone()` instead of `new`
* Does not depend on concrete classes
* Works with Prototype interface only

---

## 🔗 Relationships Summary

* Client **depends on Prototype**, not concrete class
* ConcretePrototype **implements** Prototype
* Object creation is **delegated to the object itself**
* Cloning replaces direct instantiation

---

## 🎮 Example Scenario (Game Characters)

### ❌ Without Prototype Pattern

```java
GameCharacter warrior1 =
    new GameCharacter("Warrior", 100, 20);

GameCharacter warrior2 =
    new GameCharacter("Warrior", 100, 20);

GameCharacter warrior3 =
    new GameCharacter("Warrior", 100, 20);
```

**Problems:**

* ❌ Repetitive object creation
* ❌ Hard to change defaults
* ❌ Expensive if construction is complex
* ❌ Client tightly coupled to constructor

---

### ✅ With Prototype Pattern

```java
GameCharacter baseWarrior =
    new GameCharacter("Warrior", 100, 20);

GameCharacter eliteWarrior =
    (GameCharacter) baseWarrior.clone();

eliteWarrior.setName("Elite Warrior");
```

**Benefits:**

* ✅ Fast object creation
* ✅ Centralized configuration
* ✅ Flexible customization
* ✅ Cleaner client code

---

## 🔄 Shallow Copy vs Deep Copy

### 🟡 Shallow Copy

* Copies primitive fields
* References point to the same objects
* Faster but riskier

### 🔵 Deep Copy

* Copies all objects recursively
* Fully independent clone
* Safer but more expensive

> **Prototype Pattern supports both — you choose based on need**

---

## 📊 Constructor vs Prototype

| Aspect          | Constructor  | Prototype      |
| --------------- | ------------ | -------------- |
| Object Creation | From scratch | Clone existing |
| Performance     | Slower       | Faster         |
| Complexity      | High         | Low            |
| Flexibility     | Limited      | High           |
| Coupling        | Tight        | Loose          |

---

## 🎯 Real-World Use Cases

### 1. Game Development 🎮

```java
Enemy clonedEnemy = baseEnemy.clone();
```

Create multiple enemies with shared base stats.

---

### 2. UI Components 🧩

```java
Button copy = defaultButton.clone();
```

Duplicate buttons with same style.

---

### 3. Document Templates 📄

```java
Document report = baseTemplate.clone();
```

Generate reports from templates.

---

### 4. Database Records 🗄️

```java
Query query = defaultQuery.clone();
```

Reuse query configurations.

---

### 5. Caching Systems ⚡

Clone cached objects instead of recreating them.

---

## ✅ Advantages

* **High Performance**: Faster than re-instantiation
* **Reduced Complexity**: Fewer constructors
* **Loose Coupling**: Client depends on interface
* **Dynamic Creation**: Create objects at runtime
* **Easy Extension**: Add new prototypes easily

---

## ❌ Disadvantages

* **Complex Cloning Logic** for deep objects
* **Harder Debugging** if clone logic is wrong
* **Deep Copy Cost** for complex graphs
* Requires careful design of `clone()`

---

## 🛠️ Best Practices

### Do's ✓

* Use when object creation is **expensive**
* Prefer **copy constructors** over `Object.clone()`
* Clearly document **shallow vs deep copy**
* Keep prototype interface **simple**
* Clone immutable objects freely

### Don'ts ✗

* Don’t use for **simple objects**
* Don’t expose internal references unintentionally
* Don’t rely blindly on Java’s default `clone()`
* Don’t forget to update clone logic when fields change

---

## 🔄 Related Patterns

### Prototype vs Factory

* **Prototype**: Copies existing objects
* **Factory**: Creates new objects from scratch

### Prototype vs Builder

* **Prototype**: Clone then customize
* **Builder**: Step-by-step construction

### Prototype vs Singleton

* **Prototype**: Multiple cloned instances
* **Singleton**: One shared instance

---

## 📝 Exercise Description

**Scenario**:
You are building a **game character system**.

**Requirements**:

1. Characters have base stats
2. Many characters share similar configurations
3. Object creation should be fast
4. Client should not depend on constructors

**Your Task**:
Implement the **Prototype Pattern** to clone characters instead of recreating them.

---

## 📝 Summary

The **Prototype Pattern** enables efficient object creation by **cloning existing objects** instead of building new ones from scratch.

### Key Takeaways

* Clone instead of create
* Delegate creation to the object itself
* Reduce constructor complexity
* Improve performance
* Promote loose coupling

> **Prototype Pattern = Fast, flexible object creation via cloning**

---

## 🚀 How to Run

```bash
javac *.java
java Main.java
```

---

## ✍️ Author

**Mazen Naji**
Software Engineer | Full Stack Developer

