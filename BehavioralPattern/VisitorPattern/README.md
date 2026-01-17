# Visitor Design Pattern (Java)

## 📌 Overview
The **Visitor Pattern** is a behavioral design pattern that **separates algorithms from the objects on which they operate**, allowing you to add new operations to existing object structures without modifying those structures.

It represents an operation to be performed on elements of an object structure, letting you define new operations without changing the classes of the elements.

---

## 🎯 Intent
- Define new operations on objects without changing their classes
- Separate operations from object structure
- Add new functionality to existing class hierarchies
- Avoid polluting classes with unrelated operations

---

## 🧠 Key Idea
> "Don't modify objects to add new operations—let visitors perform operations by visiting the objects."

---

## ⏱️ When to Use
Use the Visitor Pattern when you need to perform operations on objects of different types in an object structure, and you want to keep these operations separate from the object classes.

---

## 🧩 Participants
| Component | Role |
|---------|------|
| Visitor | Declares visit operation for each element type |
| ConcreteVisitor | Implements each operation defined by Visitor |
| Element | Defines accept method that accepts a visitor |
| ConcreteElement | Implements accept method |
| ObjectStructure | Contains elements and allows visitor access |

---

## 📐 UML Class Diagram (Visitor Pattern)

```text
+-------------------------+              +-------------------------+
| <<interface>>           |              | <<interface>>           |
|  ComputerPartVisitor    |              |    ComputerPart         |
+-------------------------+              +-------------------------+
| +visit(CPU)             |              | +accept(visitor)        |
| +visit(RAM)             |              +-------------------------+
| +visit(HDD)             |                         ▲
| +visit(GPU)             |                         |
+-------------------------+              +----------+----------+----------+
            ▲                            |          |          |          |
            |                         +--+--+   +---+--+   +---+--+   +---+--+
    +-------+-------+-------+         | CPU |   | RAM  |   | HDD  |   | GPU  |
    |       |       |       |         +-----+   +------+   +------+   +------+
+---+---+ +-+----+ +--+----+          |accept()|accept() |accept() |accept()|
|Price  | |Specs | |Upgrade|          +-----+   +------+   +------+   +------+
|Calc   | |Display| |Check |
+-------+ +-------+ +-------+
|visit()| |visit()| |visit()|
+-------+ +-------+ +-------+

        +------------------+
        |    Computer      |
        +------------------+
        | -parts: List     |
        +------------------+
        | +addPart()       |
        | +accept(visitor) |
        +------------------+
                |
                | contains
                ▼
           ComputerParts
```

## 🧠 UML Diagram Explanation

The UML diagram illustrates the structure and relationships of the **Visitor Design Pattern** implementation.

### 1️⃣ ComputerPartVisitor (Visitor Interface)
- Defines the visitor interface for operations.
- Declares:
  - `visit(CPU)` to operate on CPU objects
  - `visit(RAM)` to operate on RAM objects
  - `visit(HDD)` to operate on HDD objects
  - `visit(GPU)` to operate on GPU objects
- **Key Principle**: One visit method per element type (method overloading).
- This interface ensures all visitors implement operations for each element type.

---

### 2️⃣ Concrete Visitors (PriceCalculatorVisitor, SpecsDisplayVisitor, UpgradeCheckVisitor)
- Implement the `ComputerPartVisitor` interface.
- Each visitor defines **different operations** on the same elements:
  - **PriceCalculatorVisitor**: Calculates total price of all components
  - **SpecsDisplayVisitor**: Displays detailed specifications
  - **UpgradeCheckVisitor**: Checks if components need upgrading
- **Key Benefit**: Add new operations by creating new visitor classes.
- No modification to element classes needed!

---

### 3️⃣ ComputerPart (Element Interface)
- Defines the interface for elements that can be visited.
- Declares:
  - `accept(ComputerPartVisitor)` to accept any visitor
- This is the **key method** that enables the visitor pattern.
- Uses double dispatch: element knows its type, visitor knows the operation.

---

### 4️⃣ Concrete Elements (CPU, RAM, HDD, GPU)
- Implement the `ComputerPart` interface.
- Each element represents a different computer component.
- Maintain their own data:
  - **CPU**: model, price, cores, speed
  - **RAM**: model, price, capacity, type
  - **HDD**: model, price, capacity, type (HDD/SSD)
  - **GPU**: model, price, memory, brand
- Implement `accept()` method:
  ```java
  public void accept(ComputerPartVisitor visitor) {
      visitor.visit(this); // Double dispatch!
  }
  ```
- **Key Point**: Elements don't implement operations—visitors do!

---

### 5️⃣ Computer (Object Structure)
- Contains a collection of `ComputerPart` elements.
- Provides a way to iterate through elements.
- Implements `accept()` to allow visitors to traverse all parts:
  ```java
  public void accept(ComputerPartVisitor visitor) {
      for (ComputerPart part : parts) {
          part.accept(visitor);
      }
  }
  ```
- Manages the object structure but doesn't perform operations.

---

### 6️⃣ Client (ComputerShop)
- Creates the object structure (Computer with parts).
- Creates visitors (PriceCalculator, SpecsDisplay, UpgradeCheck).
- Applies visitors to elements.
- Can easily switch between different operations by using different visitors.

---

## 🔗 Relationships Summary
- Concrete visitors **implement** `ComputerPartVisitor`
- Concrete elements **implement** `ComputerPart`
- `Computer` **contains** multiple `ComputerPart` objects
- Elements **accept** visitors through the `accept()` method
- Visitors **visit** elements through overloaded `visit()` methods
- **Double Dispatch**: `element.accept(visitor)` → `visitor.visit(element)`
- Client can apply **any visitor** to **any element structure**

---

## ✅ Key Design Benefits
- Separates operations from object structure
- Easy to add new operations (create new visitor)
- Related operations kept together in one visitor class
- Follows Single Responsibility Principle
- Follows Open/Closed Principle for operations
- Accumulates state while traversing object structure

---

## 🔄 Visitor Pattern vs. Adding Methods to Classes

### ❌ **Without Visitor Pattern (Operations in Classes)**

```java
// Every element class contains all operations - cluttered!
public class CPU {
    private String model;
    private double price;
    private int cores;
    
    // Problem: Operations mixed with data
    public double calculatePrice() {
        return price;
    }
    
    public void displaySpecs() {
        System.out.println("CPU: " + model);
        System.out.println("Cores: " + cores);
    }
    
    public void checkUpgrade() {
        if (cores < 4) {
            System.out.println("Upgrade recommended");
        }
    }
    
    // Adding new operation requires modifying ALL element classes!
    public void generateReport() {
        // New operation
    }
}

public class RAM {
    // Must duplicate similar operations here
    public double calculatePrice() { ... }
    public void displaySpecs() { ... }
    public void checkUpgrade() { ... }
    public void generateReport() { ... } // Add here too!
}

// Same for HDD, GPU, etc...
```

**Problems with this approach:**
- ⚠️ **Violates Single Responsibility**: Each class handles data AND operations
- ⚠️ **Code Duplication**: Similar operation logic scattered across classes
- ⚠️ **Violates Open/Closed**: Must modify all classes to add operations
- ⚠️ **Tight Coupling**: Operations coupled to element classes
- ⚠️ **Hard to Maintain**: Changes to operation affect all classes
- ⚠️ **Cluttered Classes**: Classes contain many unrelated operations

---

### ✅ **With Visitor Pattern (Operations in Visitors)**

```java
// Elements contain only data and accept() method - clean!
public class CPU implements ComputerPart {
    private String model;
    private double price;
    private int cores;
    
    // Only structural code - no operations!
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
    
    // Just getters for data
    public String getModel() { return model; }
    public double getPrice() { return price; }
    public int getCores() { return cores; }
}

// Operations are in visitors - organized!
public class PriceCalculatorVisitor implements ComputerPartVisitor {
    private double total = 0;
    
    public void visit(CPU cpu) {
        total += cpu.getPrice();
    }
    public void visit(RAM ram) {
        total += ram.getPrice();
    }
    // All price logic in one place!
}

public class SpecsDisplayVisitor implements ComputerPartVisitor {
    public void visit(CPU cpu) {
        System.out.println("CPU: " + cpu.getModel());
    }
    // All display logic in one place!
}

// Adding new operation? Just create new visitor!
public class ReportGeneratorVisitor implements ComputerPartVisitor {
    // New operation - NO changes to element classes!
}
```

**Benefits of this approach:**
- ✅ **Single Responsibility**: Elements handle data, visitors handle operations
- ✅ **No Duplication**: Each operation defined once in its visitor
- ✅ **Open/Closed**: Add operations without modifying elements
- ✅ **Loose Coupling**: Operations independent of element classes
- ✅ **Easy Maintenance**: Change operation logic in one place
- ✅ **Clean Classes**: Elements contain only structural code

---

### 📊 Side-by-Side Comparison

| Aspect | Operations in Classes | Visitor Pattern |
|--------|---------------------|----------------|
| **Adding Operation** | Modify all element classes | Create new visitor class |
| **Code Organization** | Scattered across classes | Centralized in visitors |
| **Single Responsibility** | Violated | Maintained |
| **Open/Closed Principle** | Violated (modify classes) | Maintained (add visitors) |
| **Related Operations** | Spread across classes | Together in one visitor |
| **Element Classes** | Cluttered with operations | Clean, data-focused |
| **Maintainability** | Difficult (changes everywhere) | Easy (changes in visitor) |

---

### 🎯 Real-World Impact

**Scenario**: You need to add a "CompatibilityChecker" operation that checks if all components are compatible with each other.

**Without Visitor Pattern:**
```java
// Must modify EVERY element class!
public class CPU {
    // ... existing code ...
    
    public void checkCompatibility(RAM ram, HDD hdd, GPU gpu) {
        // Compatibility logic here
    }
}

public class RAM {
    // ... existing code ...
    
    public void checkCompatibility(CPU cpu, HDD hdd, GPU gpu) {
        // Duplicate logic here
    }
}
// Repeat for HDD, GPU...
// BREAKS OPEN/CLOSED PRINCIPLE!
```

**With Visitor Pattern:**
```java
// Just create a new visitor - NO changes to elements!
public class CompatibilityCheckVisitor implements ComputerPartVisitor {
    private List<ComputerPart> parts = new ArrayList<>();
    
    public void visit(CPU cpu) {
        parts.add(cpu);
        checkCompatibility();
    }
    
    public void visit(RAM ram) {
        parts.add(ram);
        checkCompatibility();
    }
    
    private void checkCompatibility() {
        // Compatibility logic here
    }
}

// Element classes unchanged!
// Just use the new visitor!
computer.accept(new CompatibilityCheckVisitor());
```

---

### 💡 Key Takeaway

> **Operations in Classes** = "Every element must know how to do every operation"  
> **Visitor Pattern** = "Elements accept visitors; visitors know how to perform operations"

This comparison shows why the Visitor Pattern is essential when you need to add many operations to an object structure without cluttering the element classes.

---

## 🛠️ Technologies Used
- Java
- Object-Oriented Programming
- Behavioral Design Pattern

---

## 📚 Example Explanation
In this example:
- `ComputerPart` is the element interface with `accept()` method
- Concrete elements (`CPU`, `RAM`, `HDD`, `GPU`) represent computer components
- `ComputerPartVisitor` is the visitor interface defining operations
- Concrete visitors (`PriceCalculatorVisitor`, `SpecsDisplayVisitor`, `UpgradeCheckVisitor`) perform different operations
- `Computer` is the object structure containing all parts
- Visitors traverse the computer parts and perform operations without modifying the part classes

---

## ✅ Advantages
- Separates operations from object structure
- Easy to add new operations (create new visitor)
- Related operations grouped in visitor classes
- Follows Single Responsibility Principle
- Follows Open/Closed Principle for operations
- Can accumulate state while traversing

---

## ❌ Disadvantages
- Adding new element types is difficult (must update all visitors)
- Breaks encapsulation (visitors need access to elements' internals)
- Increased complexity (more classes)
- Elements must expose enough information for visitors
- Circular dependency between visitors and elements

---

## 🎓 Use Cases
- Compilers (AST traversal for code generation, optimization)
- File system operations (calculate size, display structure, search)
- Shopping cart (calculate price, apply discounts, generate receipt)
- Document processing (render, export, validate)
- Reporting systems (different report formats from same data)
- Tax calculation (different tax strategies for different items)
- Game engines (render, physics, AI - different operations on game objects)

---

## 🔄 Related Patterns
- **Composite**: Often used together (visitor traverses composite structure)
- **Iterator**: Both traverse object structures, but different purposes
- **Strategy**: Visitor can be seen as extending Strategy to work on object structures

---

## 🌐 Real-World Examples

### 1. Compiler Operations 💻
A compiler performs many operations on an Abstract Syntax Tree (AST): code generation, optimization, type checking, pretty printing—all without modifying AST node classes.

### 2. Document Export 📄
Export a document to different formats (PDF, HTML, XML, Markdown) using different visitors on the same document structure.

### 3. Shopping Cart Pricing 🛒
Calculate prices with different strategies: regular price, member discount, seasonal sale, bulk discount—each as a separate visitor.

### 4. File System Operations 📁
Perform operations on files/folders: calculate size, display tree, search, archive—without modifying file/folder classes.

---

## 📚 Design Principles

This pattern follows these principles:
- **Single Responsibility**: Elements handle structure, visitors handle operations
- **Open/Closed**: Open for new operations (add visitors), closed for modification
- **Separation of Concerns**: Data structure separate from algorithms
- **Double Dispatch**: Combines element type and visitor type to select operation

---

## 🎯 Best Practices

1. **Use for Stable Structures**: Best when element types rarely change
2. **Group Related Operations**: Put related operations in same visitor
3. **Provide Good Access**: Elements should expose data visitors need
4. **Consider Composite**: Often used with Composite pattern
5. **Document Visitors**: Clearly document what each visitor does
6. **Test Independently**: Test each visitor separately
7. **Handle Null**: Check for null elements in visitors

---

## 🚀 How to Run

### Compile
```bash
javac *.java
```

### Run
```bash
java Main.java
```

### Expected Output
```
╔════════════════════════════════════════════════╗
║    Visitor Pattern - Computer Shop Demo        ║
╚════════════════════════════════════════════════╝

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📊 Visitor 1: Price Calculator
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

🖥️  Analyzing computer: High-End Gaming PC
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
   💰 Calculating CPU price: $589.99
   💰 Calculating RAM price: $159.99
   💰 Calculating HDD price: $149.99
   💰 Calculating GPU price: $1199.99

   ✅ Total System Price: $2099.96

[... more visitor demonstrations ...]
```

---

## 📖 Additional Resources

- **Design Patterns** by Gang of Four
- **Head First Design Patterns** by Freeman & Freeman
- **Refactoring Guru**: https://refactoring.guru/design-patterns/visitor

---

## ✍️ Author
**Mazen Naji**  
Software Engineer | Full Stack Developer  

---

## 📄 License
Educational use only