# 🔒 Proxy Design Pattern (Java)

## 📌 Overview

The **Proxy Pattern** is a **structural design pattern** that provides a **placeholder or surrogate** for another object to **control access** to it.

Instead of accessing an object directly, the Proxy Pattern creates a **proxy object** that acts as an intermediary, providing the same interface while adding additional functionality like **lazy loading**, **access control**, **caching**, or **logging**.

This pattern is especially useful when you want to add an **extra layer of control** over how and when an object is accessed, without changing the object itself.

---

## 🎯 Intent

* Control access to an object
* Add functionality without changing the object
* Provide a surrogate or placeholder for another object
* Implement lazy initialization (virtual proxy)
* Add access control (protection proxy)
* Enable remote access (remote proxy)
* Implement caching (caching proxy)

---

## 🧠 Key Idea

> "Provide a surrogate that controls access to the real object."

---

## ⏱️ When to Use

Use the Proxy Pattern when:

* You need **lazy initialization** (virtual proxy) - delay expensive object creation
* You need **access control** (protection proxy) - restrict access based on permissions
* You need **remote access** (remote proxy) - represent objects in different address spaces
* You need **logging/auditing** - track object access
* You need **caching** - store expensive operation results
* You need **reference counting** - track number of references to an object

---

## 🧩 Participants

| Component    | Role                                                |
| ------------ | --------------------------------------------------- |
| Subject      | Common interface for RealSubject and Proxy          |
| RealSubject  | The actual object that does the real work           |
| Proxy        | Maintains reference to RealSubject and controls it  |
| Client       | Works with objects through Subject interface        |

---

## 📐 UML Class Diagram (Proxy Pattern)

```text
            ┌───────────────────┐
            │      Client       │
            └───────────────────┘
                     │
                     │ uses
                     ▼
            ┌──────────────────┐
            │  <<interface>>   │
            │      Image       │
            ├──────────────────┤
            │ +display()       │
            │ +getFileName()   │
            └──────────────────┘
                     ▲
                     │
        ┌────────────┴────────────┐
        │                         │
┌───────┴──────────┐   ┌──────────┴─────────┐
│   RealImage      │   │   ProxyImage       │
├──────────────────┤   ├────────────────────┤
│ -fileName        │   │ -realImage         │
│ -imageData       │   │ -fileName          │
│ -loadTime        │   │ -accessCount       │
├──────────────────┤   ├────────────────────┤
│ +loadFromDisk()  │   │ +display()         │
│ +display()       │◄──│ +getFileName()     │
│ +getFileName()   │   │ -checkAccess()     │
└──────────────────┘   │ -logAccess()       │
                       └────────────────────┘
```

---

## 🧠 UML Diagram Explanation

### 1️⃣ Subject (Image Interface)

* Defines the **common interface** for both RealSubject and Proxy
* Declares operations that can be performed
* Client depends **only** on this interface
* Enables **substitutability** of Proxy for RealSubject

---

### 2️⃣ RealSubject (RealImage)

* The **actual object** that does the real work
* Contains the **core functionality**
* May be **expensive** to create or access
* Often has **heavy operations** (loading from disk, network calls)

---

### 3️⃣ Proxy (ProxyImage)

* Maintains a **reference** to RealSubject
* Implements the **same interface** as RealSubject
* **Controls access** to RealSubject
* Can add **additional functionality**:
  - Lazy initialization
  - Access control
  - Caching
  - Logging
  - Reference counting

---

### 4️⃣ Client

* Works with objects through the **Subject interface**
* **Unaware** whether it's using Proxy or RealSubject
* Benefits from proxy features **transparently**

---

## 🔗 Relationships Summary

* Proxy and RealSubject **implement** same interface
* Proxy **has-a** RealSubject (composition)
* Client **depends only on** Subject interface
* Proxy **delegates** calls to RealSubject
* Proxy can **delay creation** of RealSubject

---

## ✅ Key Design Benefits

* **Lazy Initialization**: Create expensive objects only when needed
* **Access Control**: Restrict access based on permissions
* **Additional Functionality**: Add logging, caching without changing object
* **Transparency**: Client doesn't know it's using a proxy
* **Performance**: Avoid unnecessary expensive operations
* **Security**: Control access to sensitive objects

---

## 🎨 Types of Proxies

### 1️⃣ Virtual Proxy (Lazy Loading)

Delays creation of expensive objects until needed.

```java
// Image loaded only when display() is called
Image image = new ProxyImage("huge_image.jpg");
// Image NOT loaded yet
image.display();  // NOW it loads
```

**Use Case**: Large images, heavy database connections

---

### 2️⃣ Protection Proxy (Access Control)

Controls access based on permissions.

```java
// Check permissions before allowing access
Image image = new ProtectionProxyImage("sensitive.jpg", user);
image.display();  // Only if user has permission
```

**Use Case**: User permissions, restricted resources

---

### 3️⃣ Remote Proxy

Represents object in different address space.

```java
// Object actually exists on remote server
RemoteImage image = new RemoteImageProxy("http://server/image.jpg");
image.display();  // Network call handled by proxy
```

**Use Case**: Distributed systems, web services

---

### 4️⃣ Caching Proxy

Stores results of expensive operations.

```java
// Result cached after first call
Image image = new CachingProxyImage("data.jpg");
image.display();  // Loads from disk
image.display();  // Returns cached result
```

**Use Case**: Database queries, API calls

---

## 🔄 Proxy Pattern vs. No Proxy

### ❌ Without Proxy Pattern

```java
// Image loaded immediately, even if never displayed
RealImage image1 = new RealImage("large1.jpg");  // LOADS
RealImage image2 = new RealImage("large2.jpg");  // LOADS
RealImage image3 = new RealImage("large3.jpg");  // LOADS

// All 3 images loaded in memory
// Even if we only display one!

image1.display();  // Only this one needed
```

**Problems:**

* ❌ Wastes memory loading all images
* ❌ Slow startup time
* ❌ No access control
* ❌ No caching
* ❌ No logging

---

### ✅ With Proxy Pattern

```java
// Images NOT loaded yet
Image image1 = new ProxyImage("large1.jpg");  // Fast
Image image2 = new ProxyImage("large2.jpg");  // Fast
Image image3 = new ProxyImage("large3.jpg");  // Fast

// Only load what we need
image1.display();  // Loads only this one

// Others remain unloaded, saving memory!
```

**Benefits:**

* ✅ Lazy loading - load only when needed
* ✅ Fast startup
* ✅ Memory efficient
* ✅ Can add access control
* ✅ Can add caching
* ✅ Can add logging

---

## 📊 Performance Comparison

| Scenario                | Without Proxy | With Proxy | Savings     |
| ----------------------- | ------------- | ---------- | ----------- |
| Load 100 images         | 100% loaded   | 0% loaded  | 100% faster |
| Display 1 image         | All loaded    | 1 loaded   | 99% memory  |
| Display same image 2x   | Load 2x       | Load 1x    | 50% faster  |
| Startup time (10 imgs)  | 10 seconds    | Instant    | 100x faster |

---

## 🎯 Real-World Use Cases

### 1. Image Viewers / Photo Galleries 🖼️

```java
// Load large images only when scrolled into view
Image thumbnail = new ProxyImage("photo.jpg");
```

---

### 2. Database Connections 🗄️

```java
// Connect to database only when query executed
Database db = new ProxyDatabase();
db.query("SELECT...");  // Connects now
```

---

### 3. Web Service Clients 🌐

```java
// Remote service accessed through proxy
API service = new RemoteAPIProxy("https://api.com");
```

---

### 4. Document Editors 📄

```java
// Load document content only when opened
Document doc = new ProxyDocument("large_file.pdf");
```

---

### 5. Security Systems 🔐

```java
// Access control through protection proxy
SecureFile file = new ProtectionProxy("secret.txt", user);
```

---

## ✅ Advantages

* **Lazy Initialization**: Defer expensive operations
* **Access Control**: Add security layer
* **Performance**: Caching and optimization
* **Transparency**: Client doesn't know about proxy
* **Flexibility**: Add functionality without changing original
* **Separation of Concerns**: Control logic separate from business logic

---

## ❌ Disadvantages

* **Added Complexity**: Extra layer of indirection
* **Response Delay**: First access may be slower (loading)
* **Code Duplication**: Proxy must implement same interface
* **Maintenance**: Changes to Subject affect both Real and Proxy

---

## 🛠️ Best Practices

### Do's ✓

* Keep Proxy and RealSubject **interface identical**
* Use for **expensive operations** (loading, network)
* Implement **lazy initialization** when appropriate
* Add **caching** for repeated operations
* **Log access** for auditing
* Use for **access control** when needed

### Don'ts ✗

* Don't use for **simple objects** (unnecessary overhead)
* Don't add too much **business logic** to proxy
* Don't make proxy **more complex** than real object
* Don't break **interface contract**
* Don't use when **direct access** is always needed

---

## 🔄 Related Patterns

### Proxy vs Decorator

* **Proxy**: Controls access, manages lifecycle
* **Decorator**: Adds behavior, wraps functionality

### Proxy vs Adapter

* **Proxy**: Same interface as subject
* **Adapter**: Different interface adaptation

### Proxy vs Facade

* **Proxy**: One-to-one relationship, controls single object
* **Facade**: One-to-many, simplifies subsystem

---

## 📝 Summary

The **Proxy Pattern** provides a surrogate to control access to an object, enabling lazy loading, access control, caching, and other optimizations without changing the original object.

### Key Takeaways

* **Control access** to expensive or sensitive objects
* **Lazy loading** - create objects only when needed
* **Transparent** to client - same interface
* **Add functionality** - caching, logging, access control
* Essential for **performance optimization**

> **Proxy Pattern = Smart placeholder that controls object access**

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
   * Lazy loading - images loaded only when displayed
   * Caching - same image loaded once
   * Access control - track usage
   * Performance improvements

---

## ✍️ Author
**Mazen Naji**  
Software Engineer | Full Stack Developer