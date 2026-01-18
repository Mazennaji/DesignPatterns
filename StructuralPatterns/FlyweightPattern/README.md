# 🪶 Flyweight Design Pattern (Java)

## 📌 Overview

The **Flyweight Pattern** is a **structural design pattern** that lets you **minimize memory usage** by **sharing common data** among multiple objects.

Instead of storing all data in each object, the Flyweight Pattern separates data into **intrinsic** (shared) and **extrinsic** (unique) states, storing shared data once and reusing it across many objects.

This pattern is especially useful when dealing with **large numbers of similar objects** that would otherwise consume excessive memory.

---

## 🎯 Intent

* Minimize memory usage by sharing data
* Support large numbers of fine-grained objects efficiently
* Separate intrinsic (shared) state from extrinsic (unique) state
* Use object pooling to reuse instances
* Reduce object creation overhead

---

## 🧠 Key Idea

> "Share what is common, store what is unique."

---

## ⏱️ When to Use

Use the Flyweight Pattern when:

* Your application creates a **large number** of similar objects
* Object creation is **expensive** in terms of memory
* Most object state can be made **extrinsic** (external)
* Objects can be grouped by their **intrinsic state**
* The application doesn't depend on **object identity**
* You need to **optimize memory usage** at scale

---

## 🧩 Participants

| Component        | Role                                                     |
| ---------------- | -------------------------------------------------------- |
| Flyweight        | Interface for objects that can share state               |
| ConcreteFlyweight| Implements Flyweight and stores intrinsic state          |
| FlyweightFactory | Creates and manages flyweight objects (object pool)      |
| Client           | Maintains extrinsic state and uses flyweights            |

---

## 📐 UML Class Diagram (Flyweight Pattern)

```text
            ┌───────────────────┐
            │      Client       │
            │  (Forest)         │
            ├───────────────────┤
            │ -trees: Tree[]    │
            │ +plantTree(...)   │
            │ +draw()           │
            └───────────────────┘
                     │
                     │ uses
                     ▼
         ┌─────────────────────────┐
         │  FlyweightFactory       │
         │  (TreeFactory)          │
         ├─────────────────────────┤
         │ -treeTypes: Map         │
         ├─────────────────────────┤
         │ +getTreeType(...)       │
         └─────────────────────────┘
                     │
                     │ creates/manages
                     ▼
            ┌──────────────────┐
            │  <<interface>>   │
            │    TreeType      │◄───────┐
            ├──────────────────┤        │
            │ +draw(x, y)      │        │ shares
            └──────────────────┘        │
                     ▲                  │
                     │                  │
                     │ implements       │
                     │                  │
         ┌───────────┴────────────┐    │
         │   ConcreteTreeType     │    │
         ├────────────────────────┤    │
         │ -name: String          │────┘
         │ -color: String         │  Intrinsic State
         │ -texture: String       │  (Shared)
         ├────────────────────────┤
         │ +draw(x, y)            │
         └────────────────────────┘
         
         ┌────────────────────────┐
         │       Tree             │
         ├────────────────────────┤
         │ -x: int                │  Extrinsic State
         │ -y: int                │  (Unique per object)
         │ -type: TreeType        │
         ├────────────────────────┤
         │ +draw()                │
         └────────────────────────┘
```

---

## 🧠 UML Diagram Explanation

### 1️⃣ Flyweight (TreeType Interface)

* Defines interface for flyweight objects
* Operations receive **extrinsic state** as parameters
* Contains methods that work with **both** intrinsic and extrinsic state

---

### 2️⃣ Concrete Flyweight (ConcreteTreeType)

* Implements the Flyweight interface
* Stores **intrinsic state** (shared among objects)
* Must be **immutable** (state cannot change after creation)
* Example: tree name, color, texture

---

### 3️⃣ Flyweight Factory (TreeFactory)

* Creates and manages flyweight objects
* Maintains a **pool** of existing flyweights
* Returns existing flyweight if available
* Creates new flyweight only when needed
* Implements **object caching/pooling**

---

### 4️⃣ Client (Forest)

* Maintains **extrinsic state** (unique to each object)
* References flyweight objects
* Passes extrinsic state to flyweight methods
* Example: x, y coordinates for each tree

---

## 🔑 Intrinsic vs Extrinsic State

### Intrinsic State (Shared) 🔒

* **Stored inside** the flyweight
* **Independent** of context
* **Immutable** - never changes
* **Shared** among many objects
* Example: Tree type, color, texture

### Extrinsic State (Unique) 🔓

* **Stored outside** the flyweight
* **Depends on** context
* **Can change** over time
* **Unique** to each object
* Example: x, y position

---

## 🔗 Relationships Summary

* Client **uses** FlyweightFactory to get flyweights
* FlyweightFactory **creates and caches** flyweights
* Client **stores extrinsic state** and references to flyweights
* Flyweights are **immutable** and **thread-safe**
* Multiple clients **share** the same flyweight instances

---

## ✅ Key Design Benefits

* **Memory Optimization**: Dramatic reduction in memory usage
* **Performance**: Fewer objects to create and manage
* **Scalability**: Supports large numbers of objects
* **Reusability**: Shared objects used across contexts
* **Separation of Concerns**: Intrinsic vs extrinsic state clearly separated

---

## 🔄 Flyweight Pattern vs. No Flyweight

### ❌ Without Flyweight Pattern (Memory Waste)

```java
// Creating 1 million trees - each stores ALL data
class Tree {
    private String name;      // Repeated 1M times
    private String color;     // Repeated 1M times
    private String texture;   // Repeated 1M times
    private int x;
    private int y;
}

// Memory: ~1,000,000 tree objects with duplicate data
// If each tree = 100 bytes → 100 MB just for trees!
```

**Problems:**

* ❌ Massive memory consumption
* ❌ Duplicate data everywhere
* ❌ Slow object creation
* ❌ Poor performance at scale
* ❌ Unnecessary memory allocation

---

### ✅ With Flyweight Pattern (Memory Efficient)

```java
// Only 3-5 tree TYPES stored (shared)
class TreeType {
    private String name;      // Stored once per type
    private String color;     // Stored once per type
    private String texture;   // Stored once per type
}

// 1 million trees only store position
class Tree {
    private int x;            // Unique
    private int y;            // Unique
    private TreeType type;    // Reference to shared object
}

// Memory: 5 TreeType objects + 1M small Tree objects
// 5 types × 100 bytes + 1M × 12 bytes = ~12 MB
// That's 88% memory reduction! 🚀
```

**Benefits:**

* ✅ Minimal memory footprint
* ✅ Shared data reused
* ✅ Fast object creation
* ✅ Excellent scalability
* ✅ Efficient resource usage

---

## 📊 Memory Comparison

| Scenario        | Without Flyweight | With Flyweight | Savings  |
| --------------- | ----------------- | -------------- | -------- |
| 1,000 Trees     | 100 KB            | 12 KB          | 88%      |
| 10,000 Trees    | 1 MB              | 120 KB         | 88%      |
| 100,000 Trees   | 10 MB             | 1.2 MB         | 88%      |
| 1,000,000 Trees | 100 MB            | 12 MB          | 88%      |

**Formula:**
- Without: `objects × full_object_size`
- With: `types × intrinsic_size + objects × (extrinsic_size + reference_size)`

---

## 🎯 Real-World Use Cases

### 1. Text Editors 📝

```java
// Instead of storing font data per character
Character c = characterFactory.getCharacter('A', "Arial", 12);
```

Each character object shares font/style but has unique position.

---

### 2. Game Development 🎮

```java
// Thousands of particles sharing same texture
Particle p = particleFactory.getParticle("smoke");
p.draw(x, y, velocity);
```

Bullet, particle, and sprite systems benefit greatly.

---

### 3. GUI Frameworks 🖼️

```java
// Icon objects sharing image data
Icon icon = iconFactory.getIcon("save.png");
```

Same icon image used in toolbar, menu, button.

---

### 4. Database Connection Pools 🗄️

```java
// Reusing expensive database connections
Connection conn = connectionPool.getConnection();
```

---

### 5. String Interning (Java) ☕

```java
String s1 = "hello";  // Stored in pool
String s2 = "hello";  // References same object
// s1 == s2 → true
```

Java automatically applies Flyweight to strings!

---

## ✅ Advantages

* **Massive memory savings** (often 70-90%)
* **Better performance** at scale
* **Supports large object counts** efficiently
* **Clear separation** of shared/unique state
* **Thread-safe** when immutable
* **Centralized object management**

---

## ❌ Disadvantages

* **Increased complexity** in code
* **Runtime cost** of looking up flyweights
* **State externalization** can be cumbersome
* **Not suitable** for few objects
* **Harder to debug** due to shared state
* **Requires careful design** of state separation

---

## 🛠️ Best Practices

### Do's ✓

* Make flyweights **immutable**
* Use a **factory** to manage flyweights
* Store **only** intrinsic state in flyweights
* Keep extrinsic state in **client** or context
* Use when you have **many similar objects**
* Profile memory before and after

### Don'ts ✗

* Don't use for **few objects** (overhead not worth it)
* Don't make flyweights **mutable**
* Don't store extrinsic state in flyweights
* Don't over-engineer simple cases
* Don't forget thread safety considerations
* Don't use if objects have little shared state

---

## 🔄 Related Patterns

### Flyweight vs Singleton

* **Flyweight**: Multiple shared instances (pooled)
* **Singleton**: One instance per class

### Flyweight vs Prototype

* **Flyweight**: Shares existing objects
* **Prototype**: Clones objects

### Flyweight vs Factory

* **Flyweight**: Factory manages object pool
* **Factory**: Factory creates new objects each time

### Flyweight vs State

* **Flyweight**: Shares immutable state
* **State**: Changes mutable state

---

## 🎨 Implementation Variations

### Pure Flyweight

* All state is intrinsic (shared)
* No extrinsic state
* Example: Immutable value objects

### Impure Flyweight

* Mix of intrinsic and extrinsic state
* Most common in practice
* Example: Tree with shared type, unique position

---

## 📝 When NOT to Use

Avoid Flyweight when:

* You have only a **small number** of objects
* Objects have **little shared state**
* The **cost of externalization** > memory savings
* Object identity matters (comparing with `==`)
* State changes frequently (violates immutability)

---

## 🧪 Testing the Pattern

```java
// Verify flyweights are shared
TreeType oak1 = factory.getTreeType("Oak", "Green", "Bark");
TreeType oak2 = factory.getTreeType("Oak", "Green", "Bark");
assert oak1 == oak2;  // Same instance!

// Verify memory savings
System.out.println("Total tree types: " + factory.getTreeTypeCount());
System.out.println("Total trees: 1,000,000");
System.out.println("Memory saved: ~88%");
```

---

## 📝 Summary

The **Flyweight Pattern** is a powerful optimization technique that dramatically reduces memory consumption by sharing common data among large numbers of similar objects.

### Key Takeaways

* **Separate** intrinsic (shared) from extrinsic (unique) state
* Use **object pooling** via a factory
* Make flyweights **immutable** for thread safety
* Best for **large quantities** of similar objects
* Can achieve **70-90% memory savings**

> **Flyweight Pattern = Share common data, store unique data externally**

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
   * Memory usage without Flyweight
   * Memory usage with Flyweight
   * Performance comparison
   * Object count vs memory savings

---

## 🎓 Fun Fact

The Java String pool uses the Flyweight pattern! That's why:
```java
String a = "hello";
String b = "hello";
System.out.println(a == b);  // true - same object!
```

---

## ✍️ Author
**Mazen Naji**  
Software Engineer | Full Stack Developer