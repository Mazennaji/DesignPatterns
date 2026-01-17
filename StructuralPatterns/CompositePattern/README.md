# Composite Design Pattern (Java)

## 📌 Overview

The **Composite Pattern** is a **structural design pattern** that lets you compose objects into **tree structures** to represent **part–whole hierarchies**. It allows clients to treat **individual objects (leaves)** and **compositions of objects (composites)** uniformly.

It simplifies client code by eliminating the need to distinguish between simple and complex objects.

---

## 🎯 Intent

* Compose objects into tree structures
* Represent part–whole hierarchies
* Treat individual objects and compositions uniformly
* Simplify client code that works with hierarchical structures
* Support recursive operations over tree structures

---

## 🧠 Key Idea

> "Clients should not care whether they are working with a single object or a collection of objects."

---

## ⏱️ When to Use

Use the Composite Pattern when:

* You need to represent **hierarchical data**
* You want clients to treat **individual objects and groups the same way**
* Operations should apply **recursively** to nested structures
* You want to avoid conditional logic based on object type

---

## 🧩 Participants

| Component | Role                                               |
| --------- | -------------------------------------------------- |
| Component | Defines the common interface for all objects       |
| Leaf      | Represents individual objects with no children     |
| Composite | Represents objects that can have children          |
| Client    | Works with objects through the Component interface |

---

## 📐 UML Class Diagram (Composite Pattern)

```text
+----------------------+
| <<interface>>        |
|      Component       |
+----------------------+
| +operation()         |
| +add(Component)      |
| +remove(Component)   |
| +getChild(int)       |
| +isComposite()       |
+----------------------+
           ▲
           |
    +------+------+
    |             |
+-----------+  +-------------+
|   Leaf    |  |  Composite  |
+-----------+  +-------------+
| -name     |  | -name       |
| -price    |  | -children[] |
+-----------+  +-------------+
| +operation|  | +operation  |
+-----------+  +-------------+
```

---

## 🧠 UML Diagram Explanation

### 1️⃣ Component (Interface)

* Declares common operations for both leaf and composite objects
* May define default behavior for managing children
* Ensures uniform treatment of all elements

---

### 2️⃣ Leaf

* Represents **primitive objects** in the hierarchy
* Has **no children**
* Implements behavior directly

Examples:

* File
* Product
* Button

---

### 3️⃣ Composite

* Represents **container objects**
* Can store **leaf objects or other composites**
* Implements operations by **delegating to its children**

Examples:

* Directory
* Product bundle
* Panel

---

### 4️⃣ Client

* Interacts with objects using the `Component` interface
* Does not need to know whether it is dealing with a leaf or a composite

---

## 🔗 Relationships Summary

* `Leaf` and `Composite` **implement** `Component`
* `Composite` **contains** `Component` objects
* Operations are performed **recursively**
* Client interacts only with `Component`

---

## ✅ Key Design Benefits

* Uniform treatment of objects
* Simplified client logic
* Easy to add new component types
* Recursive behavior built-in
* Follows **Open/Closed Principle**

---

## 🔄 Composite Pattern vs. Direct Handling

### ❌ Without Composite Pattern

```java
if (item instanceof Product) {
    item.getPrice();
} else if (item instanceof Bundle) {
    for (Product p : bundle.getProducts()) {
        total += p.getPrice();
    }
}
```

**Problems:**

* Tight coupling to concrete classes
* Complex conditional logic
* Hard to extend
* Poor scalability for nested structures

---

### ✅ With Composite Pattern

```java
Component item = getItem();
item.operation(); // Works for both Leaf and Composite
```

**Benefits:**

* No type checking
* Clean and extensible code
* Supports unlimited nesting
* Client code remains unchanged

---

## 📊 Side-by-Side Comparison

| Aspect                | Without Composite | With Composite    |
| --------------------- | ----------------- | ----------------- |
| Object handling       | Conditional logic | Uniform interface |
| Extensibility         | Low               | High              |
| Client complexity     | High              | Low               |
| Tree support          | Manual            | Built-in          |
| Open/Closed Principle | Violated          | Respected         |

---

## 🎯 Real-World Examples

### 1️⃣ File System

* File → Leaf
* Directory → Composite

### 2️⃣ GUI Frameworks (Java Swing)

```java
JFrame frame = new JFrame();
JPanel panel = new JPanel();
JButton button = new JButton();

panel.add(button);
frame.add(panel);
```

### 3️⃣ Product Catalog

* Product → Leaf
* Product Bundle → Composite

### 4️⃣ Organization Structure

* Employee → Leaf
* Department → Composite

---

## ⚡ Complexity Analysis

### Time Complexity

| Operation     | Complexity |
| ------------- | ---------- |
| Add child     | O(1)       |
| Remove child  | O(n)       |
| Traverse tree | O(n)       |

### Space Complexity

* Tree storage: **O(n)**
* Recursive stack: **O(h)** (h = tree height)

---

## 🎓 Best Practices

### Do's ✓

* Use default methods for unsupported operations
* Keep interfaces minimal and meaningful
* Validate child operations
* Document which operations apply to leaves vs composites

### Don'ts ✗

* Don’t force leaf nodes to manage children
* Don’t use Composite for flat structures
* Don’t over-nest hierarchies
* Don’t ignore performance implications

---

## 🔄 Related Patterns

* **Iterator** – Traverses composite structures
* **Decorator** – Similar structure but different intent
* **Visitor** – Adds operations to composite structures

---

## 💡 Key Takeaway

> **Composite Pattern** = “Treat single objects and groups of objects exactly the same.”

It is ideal when working with **tree-like structures** and when you want **clean, extensible, and maintainable code** without type checks or complex conditionals.

---

## 🛠️ Technologies Used

* Java
* Object-Oriented Programming
* Structural Design Pattern

---

## ✍️ Author
**Mazen Naji**  
Software Engineer | Full Stack Developer 

---

## 📄 License

Educational use only
