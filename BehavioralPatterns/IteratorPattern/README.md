# Iterator Design Pattern (Java)

## 📌 Overview
The **Iterator Pattern** is a behavioral design pattern that provides a way to **access elements of a collection sequentially without exposing its internal structure**.

It separates the traversal logic from the collection itself, promoting clean design and loose coupling.

---

## 🎯 Intent
- Access elements of an aggregate object sequentially
- Hide the internal representation of the collection
- Support multiple traversal strategies

---

## 🧠 Key Idea
> "Don't expose how a collection is built—just provide a way to iterate through it."

---

## ⏱️ When to Use
Use the Iterator Pattern when you need to traverse a collection without exposing its internal structure or when multiple traversal strategies are required.

---

## 🧩 Participants
| Component | Role |
|---------|------|
| Iterator | Defines traversal methods |
| ConcreteIterator | Implements traversal logic |
| Aggregate | Defines method to create iterator |
| ConcreteAggregate | Stores collection and returns iterator |
| Client | Uses iterator to access elements |

---

## 📐 UML Class Diagram (Iterator Pattern)

```text
+--------------------+
| <<interface>>      |
|     Iterator       |
+--------------------+
| +hasNext(): bool   |
| +next(): Object    |
+--------------------+

            ▲
            |
+-----------------------------+
|     ProductIterator         |
+-----------------------------+
| -products: List<Product>    |
| -index: int                 |
+-----------------------------+
| +hasNext(): boolean         |
| +next(): Object             |
+-----------------------------+

            |
            | iterates over
            ▼
+--------------------+
|      Product       |
+--------------------+
| -name: String      |
| -price: double     |
+--------------------+
| +toString():String |
+--------------------+

+------------------------------+
|     ProductCollection        |
+------------------------------+
| -products: List<Product>     |
+------------------------------+
| +add(Product): void          |
| +createIterator(): Iterator  |
+------------------------------+

+--------------------+
|        Main        |
+--------------------+
| +main(String[])    |
+--------------------+
```

## 🧠 UML Diagram Explanation

The UML diagram illustrates the structure and relationships of the **Iterator Design Pattern** implementation.

### 1️⃣ Iterator (Interface)
- Defines the standard traversal operations.
- Declares:
  - `hasNext()` to check if more elements exist
  - `next()` to return the next element
- This interface ensures that different iterators can be used interchangeably.

---

### 2️⃣ ProductIterator (Concrete Iterator)
- Implements the `Iterator` interface.
- Maintains:
  - A reference to the collection (`List<Product>`)
  - An index to track the current position
- Responsible for the actual traversal logic over the `Product` objects.

---

### 3️⃣ Product (Element)
- Represents the individual objects stored in the collection.
- Contains product-related data such as:
  - `name`
  - `price`
- Does not know anything about how it is being iterated.

---

### 4️⃣ ProductCollection (Aggregate)
- Stores a collection of `Product` objects.
- Provides the method `createIterator()` to return an `Iterator`.
- Hides the internal structure of the collection from the client.

---

### 5️⃣ Main (Client)
- Uses the `Iterator` interface to traverse the collection.
- Does **not** access the collection directly.
- This ensures loose coupling between client code and data structure.

---

## 🔗 Relationships Summary
- `ProductIterator` **implements** `Iterator`
- `ProductCollection` **creates** a `ProductIterator`
- `ProductIterator` **iterates over** `Product`
- `Main` **uses** the iterator without knowing the internal collection details

---

## ✅ Key Design Benefits
- Encapsulation of traversal logic
- Loose coupling between client and collection
- Compliance with **Single Responsibility Principle**
- Easy to add new iterator types without modifying existing code

---

## 🔄 Iterator Pattern vs. Direct Access

### ❌ **Without Iterator Pattern (Direct Access)**

```java
// Client code directly accesses the internal collection
ProductCollection catalog = new ProductCollection();
catalog.add(new Product("Laptop", 999.99));
catalog.add(new Product("Mouse", 29.99));
catalog.add(new Product("Keyboard", 79.99));

// Problem: Client knows the internal structure is a List
List<Product> products = catalog.getProducts(); // Exposes internal structure!

// Traversal logic is in the client code
for (int i = 0; i < products.size(); i++) {
    System.out.println(products.get(i));
}
```

**Problems with this approach:**
- ⚠️ **Tight Coupling**: Client depends on knowing the collection is a `List`
- ⚠️ **Broken Encapsulation**: Internal structure is exposed via `getProducts()`
- ⚠️ **Inflexible**: If you change from `List` to `Set` or `Tree`, all client code breaks
- ⚠️ **Scattered Logic**: Traversal code is duplicated everywhere it's needed

---

### ✅ **With Iterator Pattern (Encapsulated Access)**

```java
// Client code uses the iterator interface
ProductCollection catalog = new ProductCollection();
catalog.add(new Product("Laptop", 999.99));
catalog.add(new Product("Mouse", 29.99));
catalog.add(new Product("Keyboard", 79.99));

// Clean: Client doesn't know or care about internal structure
Iterator iterator = catalog.createIterator();

// Uniform traversal regardless of underlying data structure
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

**Benefits of this approach:**
- ✅ **Loose Coupling**: Client only depends on `Iterator` interface
- ✅ **Encapsulation**: Internal collection structure remains hidden
- ✅ **Flexible**: Can change from `List` to `Array`, `Set`, or custom structure without breaking clients
- ✅ **Centralized Logic**: Traversal logic lives in one place (the iterator)

---

### 📊 Side-by-Side Comparison

| Aspect | Direct Access | Iterator Pattern |
|--------|--------------|------------------|
| **Coupling** | Tight (client knows `List`) | Loose (client knows `Iterator`) |
| **Encapsulation** | Broken (exposes internals) | Maintained (hides internals) |
| **Flexibility** | Low (hard to change structure) | High (easy to swap implementations) |
| **Code Location** | Scattered in clients | Centralized in iterator |
| **Maintainability** | Difficult (many places to update) | Easy (one place to update) |

---

### 🎯 Real-World Impact

**Scenario**: You need to change `ProductCollection` from a `List` to a `HashMap` for faster lookups.

**Without Iterator Pattern:**
```java
// ALL client code breaks and needs updates!
// Before: for (int i = 0; i < products.size(); i++)
// After:  for (Product p : products.values())
```

**With Iterator Pattern:**
```java
// Client code remains UNCHANGED!
// The iterator implementation changes internally, but the interface stays the same
while (iterator.hasNext()) {
    System.out.println(iterator.next()); // Still works perfectly!
}
```

---

### 💡 Key Takeaway

> **Direct Access** = "Here's my internal list, do whatever you want with it"  
> **Iterator Pattern** = "I'll give you a controlled way to traverse, without exposing how I store things"

This comparison shows why the Iterator Pattern is worth the extra classes—it provides flexibility and maintainability that direct access simply cannot offer.

---

## 🛠️ Technologies Used
- Java
- Object-Oriented Programming
- Behavioral Design Pattern

---

## 📚 Example Explanation
In this example:
- `ProductCollection` stores products
- `ProductIterator` handles traversal logic
- The client (`Main`) does not know how the collection is implemented
- Products can be iterated without exposing the internal list

---

## ✅ Advantages
- Supports multiple traversal methods
- Improves encapsulation
- Simplifies client code
- Follows Single Responsibility Principle

---

## ❌ Disadvantages
- Can increase number of classes
- May be overkill for simple collections

---

## 🎓 Use Cases
- Collections (lists, trees, graphs)
- Menu systems
- History navigation
- Data processing pipelines

---

## ✍️ Author
**Mazen Naji**  
Software Engineer | Full Stack Developer  

---

## 📄 License
Educational use only