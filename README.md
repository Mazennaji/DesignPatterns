<div align="center">

# ◆ Design Patterns

### A complete Java reference for all 23 Gang of Four design patterns.
### Each folder contains working code, UML diagrams, and real-world use cases.

<br/>

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com)
[![Patterns](https://img.shields.io/badge/Patterns-23-blueviolet?style=for-the-badge)](.)
[![License](https://img.shields.io/badge/License-Open%20Source-green?style=for-the-badge)](.)
[![GoF](https://img.shields.io/badge/Gang%20of%20Four-1994-orange?style=for-the-badge)](.)

<br/>

</div>

---

## What Are Design Patterns?

> *"Descriptions of communicating objects and classes that are customized to solve a general design problem in a particular context."*
> — **Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides** *(Gang of Four, 1994)*

Design patterns are **reusable solutions** to commonly occurring problems in software design. They are proven templates — not finished code to copy, but blueprints to adapt for your specific context.

The 23 classic patterns were introduced in the landmark 1994 book **"Design Patterns: Elements of Reusable Object-Oriented Software"** and remain fundamental to modern software engineering.

<br/>

### Why Design Patterns Matter

| # | Benefit | Description |
|---|---------|-------------|
| 01 | **Code Reusability** | Apply tested solutions across different projects |
| 02 | **Shared Vocabulary** | Give teams a common language for architectural decisions |
| 03 | **Maintainability** | Produce organized, structured code that's easier to evolve |
| 04 | **Scalability** | Build flexible systems that adapt to changing requirements |
| 05 | **Best Practices** | Draw on the collective experience of expert developers |
| 06 | **Faster Development** | Stop reinventing the wheel for common problems |
| 07 | **Fewer Bugs** | Use solutions battle-tested in production environments |

---

## Pattern Categories

```
◆ Creational  →  How objects are created
◆ Structural  →  How objects are composed
◆ Behavioral  →  How objects communicate
```

---

## 🟠 Creational Patterns
> Control **object creation mechanisms** — promote flexibility and reuse.

| Pattern | Intent |
|---------|--------|
| **Singleton** | Ensure a class has only one instance with a global access point |
| **Factory Method** | Define an interface for creating objects; let subclasses decide which class to instantiate |
| **Abstract Factory** | Create families of related objects without specifying concrete classes |
| **Builder** | Construct complex objects step by step, separating construction from representation |
| **Prototype** | Create new objects by cloning an existing instance |

---

## 🔵 Structural Patterns
> Assemble objects and classes into **larger structures** while keeping them flexible and efficient.

| Pattern | Intent |
|---------|--------|
| **Adapter** | Convert one interface into another that clients expect — a compatibility bridge |
| **Bridge** | Decouple abstraction from implementation so the two can vary independently |
| **Composite** | Compose objects into tree structures to represent part-whole hierarchies |
| **Decorator** | Attach additional responsibilities to an object dynamically at runtime |
| **Facade** | Provide a simplified interface to a complex body of code |
| **Flyweight** | Use sharing to support large numbers of fine-grained objects efficiently |
| **Proxy** | Provide a surrogate or placeholder to control access to another object |

---

## 🟣 Behavioral Patterns
> Define **algorithms and communication** between objects — who does what and how.

| Pattern | Intent |
|---------|--------|
| **Chain of Responsibility** | Pass requests along a chain of handlers until one processes it |
| **Command** | Encapsulate a request as an object — enabling undo, queues, and logging |
| **Iterator** | Provide a way to sequentially access elements without exposing the underlying structure |
| **Mediator** | Define a central object that encapsulates how a set of objects interact |
| **Memento** | Capture and externalize an object's internal state for later restoration |
| **Observer** | Define a one-to-many dependency so all dependents are notified on state change |
| **State** | Allow an object to alter its behavior when its internal state changes |
| **Strategy** | Define a family of interchangeable algorithms and make them swappable at runtime |
| **Template Method** | Define the skeleton of an algorithm, deferring some steps to subclasses |
| **Visitor** | Define new operations on object structures without changing the classes themselves |

---

## Repository Structure

```
DesignPattern/
│
├── 🟠 CreationalPattern/
│   ├── SingletonPattern/
│   ├── FactoryPattern/
│   ├── AbstractFactoryPattern/
│   ├── BuilderPattern/
│   └── PrototypePattern/
│
├── 🔵 StructuralPattern/
│   ├── AdapterPattern/
│   ├── BridgePattern/
│   ├── CompositePattern/
│   ├── DecoratorPattern/
│   ├── FacadePattern/
│   ├── FlyweightPattern/
│   └── ProxyPattern/
│
└── 🟣 BehavioralPattern/
    ├── ChainOfResponsibility/
    ├── CommandPattern/
    ├── IteratorPattern/
    ├── MediatorPattern/
    ├── MementoPattern/
    ├── ObserverPattern/
    ├── StatePattern/
    ├── StrategyPattern/
    ├── TemplateMethodPattern/
    └── VisitorPattern/
```

---

## Getting Started

Each pattern folder contains the following:

```
PatternName/
├── README.md          ← Explanation, roles, and UML diagram
├── *.java             ← Complete working implementation
└── notes.md           ← Use case and real-world application
```

**Steps to explore a pattern:**

1. **Pick a category** — Creational, Structural, or Behavioral
2. **Navigate** to the pattern folder that fits your problem
3. **Read the README** for structure and intent
4. **Study the code** — trace through the working example
5. **Apply it** — adapt the blueprint to your own project

---

## Resources

| Resource | Description |
|----------|-------------|
| 📗 [Design Patterns — GoF](https://www.amazon.com/dp/0201633612) | The original 1994 book — the definitive reference |
| 📘 [Head First Design Patterns](https://www.amazon.com/dp/149207800X) | The most approachable introduction available |
| 🌐 [Refactoring.Guru](https://refactoring.guru/design-patterns) | Beautiful online reference with diagrams and examples in multiple languages |

---

## Contributing

Contributions are welcome! If you find a bug, have a better implementation, or want to add examples:

1. Fork the repository
2. Create a feature branch (`git checkout -b improve/pattern-name`)
3. Commit your changes (`git commit -m 'Improve Singleton example'`)
4. Push to the branch (`git push origin improve/pattern-name`)
5. Open a Pull Request

---

## License

This project is **open source** and available for educational purposes.

---

<div align="center">

*Built with care for developers who want to write better software.*

**⭐ Star this repo if it helped you!**

</div>
