# 🎨 Decorator Design Pattern (Java)

## 📌 Overview

The **Decorator Pattern** is a **structural design pattern** that allows behavior to be **added dynamically to individual objects** without modifying their class.

Instead of using inheritance to extend behavior, the Decorator Pattern uses **object composition** to wrap objects and add new responsibilities at runtime.

This pattern is especially useful when subclassing would lead to a **large number of subclasses** or when behavior needs to be **combined flexibly**.

---

## 🎯 Intent

* Add responsibilities to objects dynamically
* Extend behavior without modifying existing code
* Avoid subclass explosion
* Follow the **Open/Closed Principle**
* Combine behaviors flexibly at runtime

---

## 🧠 Key Idea

> “Wrap an object inside another object that adds new behavior, while keeping the same interface.”

---

## ⏱️ When to Use

Use the Decorator Pattern when:

* You want to add functionality to objects **at runtime**
* Inheritance would result in too many subclasses
* You want different combinations of behaviors
* You must extend behavior without touching existing code
* You want to keep client code unchanged

---

## 🧩 Participants

| Component         | Role                                             |
| ----------------- | ------------------------------------------------ |
| Component         | Defines the common interface                     |
| ConcreteComponent | Original object being decorated                  |
| Decorator         | Abstract wrapper implementing the same interface |
| ConcreteDecorator | Adds specific behavior                           |
| Client            | Uses objects via the component interface         |

---

## 📐 UML Class Diagram (Decorator Pattern)

```text
            ┌───────────────────┐
            │      Client       │
            └───────────────────┘
                     │
                     │ uses
                     ▼
            ┌───────────────────┐
            │  <<interface>>    │
            │     Coffee        │
            ├───────────────────┤
            │ +getDescription() │
            │ +cost()           │
            └───────────────────┘
                     ▲
                     │
        ┌────────────┴────────────┐
        │                         │
┌──────────────────┐     ┌─────────────────────┐
│  SimpleCoffee    │     │  CoffeeDecorator    │
├──────────────────┤     ├─────────────────────┤
│ +getDescription()│     │ -coffee: Coffee     │
│ +cost()          │     │ +getDescription()   │
└──────────────────┘     │ +cost()              │
                          └──────────▲──────────┘
                                     │
                   ┌─────────────────┼─────────────────┐
                   │                 │                 │
          ┌────────┴───────┐ ┌───────┴────────┐ ┌──────┴────────┐
          │ MilkDecorator  │ │ SugarDecorator │ │ WhipDecorator │
          ├────────────────┤ ├────────────────┤ ├───────────────┤
          │ +cost()        │ │ +cost()        │ │ +cost()       │
          │ +description() │ │ +description() │ │ +description()│
          └────────────────┘ └────────────────┘ └───────────────┘
```

---

## 🧠 UML Diagram Explanation

### 1️⃣ Component (Coffee Interface)

* Defines the interface for objects that can have responsibilities added
* Declares common operations (`cost()`, `getDescription()`)
* Client code depends only on this interface

---

### 2️⃣ Concrete Component (SimpleCoffee)

* The base object to which new behavior will be added
* Implements the component interface
* Represents the simplest form of the object

---

### 3️⃣ Decorator (CoffeeDecorator)

* Implements the same interface as the component
* Holds a reference to a `Coffee` object
* Delegates calls to the wrapped object
* Acts as the base class for all decorators

---

### 4️⃣ Concrete Decorators (Milk, Sugar, Whip)

* Extend the base decorator
* Add new behavior before or after delegating to the wrapped object
* Can be stacked together dynamically
* Each decorator adds a **single responsibility**

---

### 5️⃣ Client

* Works with objects using the `Coffee` interface
* Is unaware whether it is using a decorated or undecorated object
* Benefits from behavior extension transparently

---

## 🔗 Relationships Summary

* Client **depends on** Component interface
* Decorator **implements** Component
* Decorator **has-a** Component (composition)
* Concrete Decorators **extend** Decorator
* Behavior is added by **wrapping**, not subclassing
* Multiple decorators can be **chained**

---

## ✅ Key Design Benefits

* Follows **Open/Closed Principle**
* Avoids subclass explosion
* Adds behavior at runtime
* Flexible combinations of features
* Single Responsibility per decorator
* Transparent to client code

---

## 🔄 Decorator Pattern vs. No Decorator

### ❌ Without Decorator Pattern (Subclass Explosion)

```java
class CoffeeWithMilk {}
class CoffeeWithSugar {}
class CoffeeWithMilkAndSugar {}
class CoffeeWithMilkSugarWhip {}
// Class count explodes!
```

**Problems:**

* ❌ Too many subclasses
* ❌ Hard to maintain
* ❌ Inflexible combinations
* ❌ Violates Open/Closed Principle

---

### ✅ With Decorator Pattern (Flexible Composition)

```java
Coffee coffee = new SimpleCoffee();
coffee = new MilkDecorator(coffee);
coffee = new SugarDecorator(coffee);
coffee = new WhipDecorator(coffee);
```

**Benefits:**

* ✅ No subclass explosion
* ✅ Behaviors added dynamically
* ✅ Any combination possible
* ✅ Clean, maintainable code

---

## 📊 Side-by-Side Comparison

| Aspect           | Without Decorator | With Decorator |
| ---------------- | ----------------- | -------------- |
| Inheritance      | Heavy             | Minimal        |
| Flexibility      | Low               | High           |
| Runtime Behavior | Fixed             | Dynamic        |
| Class Count      | Explodes          | Controlled     |
| Open/Closed      | Violated          | Respected      |
| Maintenance      | Hard              | Easy           |

---

## 🎯 Real-World Use Cases

### 1. Java I/O Streams 📁

```java
InputStream in =
    new BufferedInputStream(
        new FileInputStream("file.txt"));
```

Each stream **decorates** the previous one.

---

### 2. UI Components 🖼️

* Scroll bars
* Borders
* Shadows
* Tooltips

Each visual feature decorates a base component.

---

### 3. Logging Systems 📝

```java
Logger logger =
    new TimestampLogger(
        new FileLogger(
            new ConsoleLogger()));
```

---

### 4. Food Ordering Systems 🍔

* Base item
* Add cheese
* Add sauce
* Add extras

Each add-on is a decorator.

---

## ✅ Advantages

* Runtime flexibility
* Open/Closed Principle
* Clean separation of concerns
* Avoids subclass explosion
* Easy to extend

---

## ❌ Disadvantages

* Many small classes
* Harder debugging due to wrapping
* Increased object count
* Can be over-engineering for simple cases

---

## 🛠️ Best Practices

### Do's ✓

* Keep decorators focused on one responsibility
* Program to interfaces
* Use composition, not inheritance
* Keep decorators lightweight

### Don’ts ✗

* Don’t add business logic inside decorators
* Don’t create unnecessary decorators
* Don’t break the interface contract
* Don’t chain excessively without reason

---

## 🔄 Related Patterns

### Decorator vs Adapter

* **Decorator**: Adds behavior
* **Adapter**: Changes interface

### Decorator vs Proxy

* **Decorator**: Enhances behavior
* **Proxy**: Controls access

### Decorator vs Facade

* **Decorator**: Extends one object
* **Facade**: Simplifies a subsystem

---

## 📝 Summary

The **Decorator Pattern** provides a powerful and flexible alternative to inheritance by allowing behavior to be added dynamically at runtime through object composition.

### Key Takeaways

* Prefer composition over inheritance
* Use when behavior combinations grow
* Ideal for extensible, flexible systems
* Widely used in Java core libraries

> **Decorator Pattern = Behavior layering without subclass chaos**

---

## 🚀 How to Run

1. Compile all Java files
2. Run the `Main` class
3. Observe behavior added dynamically

---

## ✍️ Author

**Mazen Naji**
Software Engineer | Full Stack Developer

