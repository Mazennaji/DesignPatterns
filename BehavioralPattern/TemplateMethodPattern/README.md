# ☕ Template Method Design Pattern (Java) - Beverage Preparation System

## 📌 Overview

The **Template Method Pattern** is a **behavioral design pattern** that defines the skeleton of an algorithm in a base class, allowing subclasses to override specific steps without changing the algorithm's structure. This pattern enables code reuse while providing flexibility for customization.

This example demonstrates a **beverage preparation system** where different beverages (Tea, Coffee, Hot Chocolate) follow the same preparation steps but implement their specific behaviors differently.

---

## 🎯 Intent

> Define the skeleton of an algorithm in a method, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.

---

## 🧠 Key Idea

> "Define the recipe once, customize the ingredients as needed."

---

## ❓ Why Use the Template Method Pattern?

* Eliminates code duplication by extracting common algorithm structure
* Allows subclasses to customize specific steps without rewriting the entire algorithm
* Enforces a consistent algorithm structure across implementations
* Follows the **Hollywood Principle**: "Don't call us, we'll call you"
* Provides control over which parts of the algorithm can be overridden
* Supports hook methods for optional customization

---

## ⏱️ When to Use

Use the Template Method Pattern when you have multiple classes that implement similar algorithms with minor variations, or when you want to control the extension points in your algorithm.

---

## 🧩 Pattern Participants

| Component | Role |
|-----------|------|
| **Abstract Class** | Defines template method and algorithm structure |
| **Template Method** | Defines algorithm skeleton; calls abstract/hook methods |
| **Abstract Methods** | Must be implemented by subclasses |
| **Hook Methods** | Optional methods subclasses can override |
| **Concrete Classes** | Implement abstract methods with specific behavior |

---

## 📐 UML Class Diagram (Template Method Pattern)

```text
+---------------------------+
|      <<abstract>>         |
|        Beverage           |
|    (Abstract Class)       |
+---------------------------+
| +prepareBeverage() FINAL  |  ← Template Method
+---------------------------+
| #boilWater()              |  ← Concrete method
| #pourInCup()              |  ← Concrete method
| #serve()                  |  ← Concrete method
| #brew() ABSTRACT          |  ← Abstract method
| #addCondiments() ABSTRACT |  ← Abstract method
| #addExtras()              |  ← Hook method
| #customerWantsExtras()    |  ← Hook method
+---------------------------+
            ▲
            |
            | extends
            |
   +--------|--------+--------+
   |                 |        |
+------------+  +------------+  +------------------+
|    Tea     |  |   Coffee   |  | HotChocolate     |
| (Concrete) |  | (Concrete) |  | (Concrete)       |
+------------+  +------------+  +------------------+
| +brew()    |  | +brew()    |  | +brew()          |
| +addCondi  |  | +addCondi  |  | +addCondiments() |
|  ments()   |  |  ments()   |  | +addExtras()     |
| +addExtras |  | +customer  |  | +customerWants   |
| +customer  |  |  Wants     |  |  Extras()        |
|  Wants     |  |  Extras()  |  +------------------+
|  Extras()  |  +------------+
+------------+

+--------------------+
|       Main         |
|     (Client)       |
+--------------------+
| +main(String[])    |
+--------------------+
```

---

## 🧠 UML Diagram Explanation

The UML diagram illustrates the structure and relationships of the **Template Method Design Pattern** implementation.

### 1️⃣ Abstract Class (Beverage)
- Contains the **template method** (`prepareBeverage()`)
- The template method is declared `final` to prevent overriding
- Defines the algorithm structure by calling:
  - Concrete methods (implemented in abstract class)
  - Abstract methods (must be implemented by subclasses)
  - Hook methods (optional overrides for subclasses)
- Implements common steps that are the same for all beverages
- Controls the flow of the algorithm

---

### 2️⃣ Template Method (prepareBeverage)
- Declared as `public final` - cannot be overridden
- Defines the complete algorithm structure:
  1. `boilWater()` - concrete method
  2. `brew()` - abstract method (varies by beverage)
  3. `pourInCup()` - concrete method
  4. `addCondiments()` - abstract method (varies by beverage)
  5. `addExtras()` - hook method (optional)
  6. `serve()` - concrete method
- Ensures all beverages follow the same preparation sequence

---

### 3️⃣ Abstract Methods (brew, addCondiments)
- Declared in the abstract class but not implemented
- **Must** be implemented by all concrete subclasses
- Represent the steps that vary between different beverages
- Provide the customization points in the algorithm
- Allow each beverage type to define its specific behavior

---

### 4️⃣ Hook Methods (addExtras, customerWantsExtras)
- Provide default implementations in the abstract class
- **Can** be overridden by subclasses (but not required)
- Offer optional extension points in the algorithm
- Allow subclasses to "hook into" the algorithm at specific points
- Enable fine-grained control over algorithm behavior

---

### 5️⃣ Concrete Methods (boilWater, pourInCup, serve)
- Implemented directly in the abstract class
- Shared across all subclasses
- Cannot be overridden (private methods)
- Represent the invariant parts of the algorithm
- Ensure consistency across all implementations

---

### 6️⃣ Concrete Classes (Tea, Coffee, HotChocolate)
- Extend the abstract `Beverage` class
- Must implement all abstract methods
- Can override hook methods if needed
- Provide beverage-specific implementations
- Inherit the template method and algorithm structure

---

### 7️⃣ Client (Main)
- Creates instances of concrete beverage classes
- Calls the template method on each instance
- Does **not** need to know the algorithm details
- Works with beverages through the abstract class interface

---

## 🔗 Relationships Summary

- Concrete classes (Tea, Coffee, HotChocolate) **extend** abstract class (Beverage)
- Template method **calls** abstract methods, hook methods, and concrete methods
- Subclasses **must implement** abstract methods
- Subclasses **may override** hook methods
- Subclasses **inherit** the template method without changes
- Algorithm structure is defined once and reused by all subclasses

---

## ✅ Key Design Benefits

- Eliminates code duplication (algorithm defined once)
- Provides consistent structure across implementations
- Allows customization without changing core algorithm
- Follows Hollywood Principle (inversion of control)
- Easy to add new variations (Open/Closed Principle)
- Clear separation between invariant and variant parts

---

## 🔄 Template Method Pattern vs. Code Duplication

### ❌ **Without Template Method Pattern (Code Duplication)**

```java
// Each beverage class duplicates the entire algorithm
public class Tea {
    public void prepareTea() {
        // Duplicated: boil water
        System.out.println("Boiling water...");
        
        // Tea-specific
        System.out.println("Steeping the tea bag...");
        
        // Duplicated: pour in cup
        System.out.println("Pouring into cup...");
        
        // Tea-specific
        System.out.println("Adding lemon...");
        
        // Tea-specific
        if (true) { // Hardcoded logic
            System.out.println("Adding honey");
        }
        
        // Duplicated: serve
        System.out.println("Your beverage is ready! Enjoy!");
    }
}

public class Coffee {
    public void prepareCoffee() {
        // Duplicated: boil water (COPY-PASTE!)
        System.out.println("Boiling water...");
        
        // Coffee-specific
        System.out.println("Dripping coffee through filter...");
        
        // Duplicated: pour in cup (COPY-PASTE!)
        System.out.println("Pouring into cup...");
        
        // Coffee-specific
        System.out.println("Adding sugar and milk...");
        
        // Duplicated: serve (COPY-PASTE!)
        System.out.println("Your beverage is ready! Enjoy!");
    }
}

public class HotChocolate {
    public void prepareHotChocolate() {
        // Duplicated: boil water (COPY-PASTE!)
        System.out.println("Boiling water...");
        
        // HotChocolate-specific
        System.out.println("Mixing cocoa powder with hot water...");
        
        // Duplicated: pour in cup (COPY-PASTE!)
        System.out.println("Pouring into cup...");
        
        // HotChocolate-specific
        System.out.println("Adding milk and vanilla...");
        
        // HotChocolate-specific
        if (true) { // Hardcoded logic
            System.out.println("Adding marshmallows and whipped cream");
        }
        
        // Duplicated: serve (COPY-PASTE!)
        System.out.println("Your beverage is ready! Enjoy!");
    }
}

// Client code - inconsistent method names
public class Main {
    public static void main(String[] args) {
        Tea tea = new Tea();
        tea.prepareTea(); // Different method name
        
        Coffee coffee = new Coffee();
        coffee.prepareCoffee(); // Different method name
        
        HotChocolate hotChocolate = new HotChocolate();
        hotChocolate.prepareHotChocolate(); // Different method name
    }
}
```

**Problems with this approach:**
- ⚠️ **Massive Code Duplication**: Same steps (boil, pour, serve) copied across all classes
- ⚠️ **Inconsistent Naming**: Each class has different method name (prepareTea, prepareCoffee, etc.)
- ⚠️ **Hard to Maintain**: Bug fixes need to be applied to every class
- ⚠️ **No Polymorphism**: Can't treat different beverages uniformly
- ⚠️ **Rigid Structure**: Changing algorithm order requires modifying all classes
- ⚠️ **No Extension Points**: Can't easily add optional steps
- ⚠️ **Error-Prone**: Easy to forget steps or get order wrong

---

### ✅ **With Template Method Pattern (Clean Structure)**

```java
// Abstract class - defines algorithm structure ONCE
public abstract class Beverage {
    
    // Template method - algorithm skeleton
    public final void prepareBeverage() {
        boilWater();           // Concrete (shared)
        brew();                // Abstract (customizable)
        pourInCup();           // Concrete (shared)
        addCondiments();       // Abstract (customizable)
        
        if (customerWantsExtras()) {  // Hook (optional)
            addExtras();              // Hook (optional)
        }
        
        serve();               // Concrete (shared)
    }
    
    // Shared implementations
    private void boilWater() {
        System.out.println("Boiling water...");
    }
    
    private void pourInCup() {
        System.out.println("Pouring into cup...");
    }
    
    private void serve() {
        System.out.println("Your beverage is ready! Enjoy!");
    }
    
    // Customization points
    protected abstract void brew();
    protected abstract void addCondiments();
    
    // Optional hooks
    protected void addExtras() { }
    protected boolean customerWantsExtras() { return false; }
}

// Concrete classes - only define what's different
public class Tea extends Beverage {
    protected void brew() {
        System.out.println("Steeping the tea bag...");
    }
    
    protected void addCondiments() {
        System.out.println("Adding lemon...");
    }
    
    protected void addExtras() {
        System.out.println("Adding honey");
    }
    
    protected boolean customerWantsExtras() {
        return true;
    }
}

public class Coffee extends Beverage {
    protected void brew() {
        System.out.println("Dripping coffee through filter...");
    }
    
    protected void addCondiments() {
        System.out.println("Adding sugar and milk...");
    }
}

public class HotChocolate extends Beverage {
    protected void brew() {
        System.out.println("Mixing cocoa powder with hot water...");
    }
    
    protected void addCondiments() {
        System.out.println("Adding milk and vanilla...");
    }
    
    protected void addExtras() {
        System.out.println("Adding marshmallows and whipped cream");
    }
    
    protected boolean customerWantsExtras() {
        return true;
    }
}

// Client code - uniform interface
public class Main {
    public static void main(String[] args) {
        var tea = new Tea();
        tea.prepareBeverage(); // Same method for all!
        
        var coffee = new Coffee();
        coffee.prepareBeverage(); // Polymorphism!
        
        var hotChocolate = new HotChocolate();
        hotChocolate.prepareBeverage(); // Consistency!
    }
}
```

**Benefits of this approach:**
- ✅ **No Duplication**: Common steps defined once in abstract class
- ✅ **Consistent Interface**: All beverages use `prepareBeverage()`
- ✅ **Easy Maintenance**: Fix bugs in one place
- ✅ **Polymorphism**: Treat all beverages uniformly
- ✅ **Controlled Extension**: Hook methods provide optional customization
- ✅ **Protected Algorithm**: Template method is `final` - can't be broken
- ✅ **Clear Structure**: Easy to understand what varies vs. what stays the same

---

### 📊 Side-by-Side Comparison

| Aspect | Code Duplication | Template Method Pattern |
|--------|-----------------|------------------------|
| **Code Reuse** | None (everything duplicated) | High (shared code in base class) |
| **Maintainability** | Difficult (changes in many places) | Easy (change once) |
| **Consistency** | Low (easy to diverge) | High (enforced structure) |
| **Extensibility** | Hard (copy-paste new class) | Easy (extend and override) |
| **Method Names** | Inconsistent (prepareTea, etc.) | Uniform (prepareBeverage) |
| **Polymorphism** | Not possible | Fully supported |
| **Bug Risk** | High (miss updates) | Low (single source of truth) |

---

### 🎯 Real-World Impact

**Scenario**: You discover that water should be boiled at different temperatures for different beverages.

**Without Template Method Pattern:**
```java
// Must update EVERY class!
public class Tea {
    public void prepareTea() {
        System.out.println("Boiling water to 85°C..."); // Update here
        // ... rest of method
    }
}

public class Coffee {
    public void prepareCoffee() {
        System.out.println("Boiling water to 96°C..."); // Update here
        // ... rest of method
    }
}

public class HotChocolate {
    public void prepareHotChocolate() {
        System.out.println("Boiling water to 75°C..."); // Update here
        // ... rest of method
    }
}
// Must remember to update ALL classes - easy to miss one!
```

**With Template Method Pattern:**
```java
// Make boilWater() abstract instead of concrete
public abstract class Beverage {
    public final void prepareBeverage() {
        boilWater();  // Now calls subclass implementation
        // ... rest remains the same
    }
    
    protected abstract void boilWater(); // Now customizable!
}

// Each subclass specifies its temperature
public class Tea extends Beverage {
    protected void boilWater() {
        System.out.println("Boiling water to 85°C...");
    }
}

public class Coffee extends Beverage {
    protected void boilWater() {
        System.out.println("Boiling water to 96°C...");
    }
}
// Clean, localized changes!
```

---

### 💡 Key Takeaway

> **Code Duplication** = "Copy-paste the same algorithm, customize it, repeat for every new type"  
> **Template Method** = "Define the algorithm once, customize only what differs"

This comparison shows why the Template Method Pattern is essential—it provides consistency, maintainability, and extensibility that duplicated code cannot match.

---

## 📝 Complete Implementation

### 1️⃣ Abstract Class – `Beverage`

```java
/**
 * Abstract class defining the template for beverage preparation.
 * The template method defines the algorithm structure, while subclasses
 * implement the specific steps.
 */
public abstract class Beverage {
    
    /**
     * Template Method - Defines the algorithm structure.
     * This method is final to prevent subclasses from changing the algorithm.
     */
    public final void prepareBeverage() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
        
        // Hook method - optional step
        if (customerWantsExtras()) {
            addExtras();
        }
        
        serve();
    }
    
    // Concrete method - same for all beverages
    private void boilWater() {
        System.out.println("Boiling water...");
    }
    
    // Concrete method - same for all beverages
    private void pourInCup() {
        System.out.println("Pouring into cup...");
    }
    
    // Concrete method - same for all beverages
    private void serve() {
        System.out.println("Your beverage is ready! Enjoy!\n");
    }
    
    // Abstract methods - must be implemented by subclasses
    protected abstract void brew();
    protected abstract void addCondiments();
    
    // Hook method - subclasses can override if needed
    protected void addExtras() {
        // Default: do nothing
    }
    
    // Hook method - subclasses can override to control behavior
    protected boolean customerWantsExtras() {
        return false; // Default: no extras
    }
}
```

---

### 2️⃣ Concrete Class – `Tea`

```java
/**
 * Concrete class implementing Tea-specific preparation steps.
 */
public class Tea extends Beverage {
    
    @Override
    protected void brew() {
        System.out.println("Steeping the tea bag...");
    }
    
    @Override
    protected void addCondiments() {
        System.out.println("Adding lemon...");
    }
    
    @Override
    protected void addExtras() {
        System.out.println("Adding honey");
    }
    
    @Override
    protected boolean customerWantsExtras() {
        return true; // Tea typically comes with honey option
    }
}
```

---

### 3️⃣ Concrete Class – `Coffee`

```java
/**
 * Concrete class implementing Coffee-specific preparation steps.
 */
public class Coffee extends Beverage {
    
    @Override
    protected void brew() {
        System.out.println("Dripping coffee through filter...");
    }
    
    @Override
    protected void addCondiments() {
        System.out.println("Adding sugar and milk...");
    }
    
    @Override
    protected boolean customerWantsExtras() {
        return false; // Coffee doesn't come with extras by default
    }
}
```

---

### 4️⃣ Concrete Class – `HotChocolate`

```java
/**
 * Concrete class implementing HotChocolate-specific preparation steps.
 */
public class HotChocolate extends Beverage {
    
    @Override
    protected void brew() {
        System.out.println("Mixing cocoa powder with hot water...");
    }
    
    @Override
    protected void addCondiments() {
        System.out.println("Adding milk and vanilla...");
    }
    
    @Override
    protected void addExtras() {
        System.out.println("Adding marshmallows and whipped cream");
    }
    
    @Override
    protected boolean customerWantsExtras() {
        return true; // Hot chocolate often comes with marshmallows
    }
}
```

---

### 5️⃣ Client Code – `Main`

```java
/**
 * Client code demonstrating the Template Method Pattern.
 * Shows how different beverages follow the same preparation algorithm
 * but with different implementations of specific steps.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Preparing Tea ===");
        var tea = new Tea();
        tea.prepareBeverage();
        
        System.out.println("=== Preparing Coffee ===");
        var coffee = new Coffee();
        coffee.prepareBeverage();
        
        System.out.println("=== Preparing Hot Chocolate ===");
        var hotChocolate = new HotChocolate();
        hotChocolate.prepareBeverage();
    }
}
```

---

## 🔄 Step-by-Step Flow Explanation

### **Preparing Tea**

```
Step 1: tea.prepareBeverage() called
↓
Step 2: Template method executes
↓
Step 3: boilWater() [Concrete - from Beverage]
Output: "Boiling water..."
↓
Step 4: brew() [Abstract - implemented in Tea]
Output: "Steeping the tea bag..."
↓
Step 5: pourInCup() [Concrete - from Beverage]
Output: "Pouring into cup..."
↓
Step 6: addCondiments() [Abstract - implemented in Tea]
Output: "Adding lemon..."
↓
Step 7: customerWantsExtras() [Hook - overridden in Tea]
Returns: true
↓
Step 8: addExtras() [Hook - overridden in Tea]
Output: "Adding honey"
↓
Step 9: serve() [Concrete - from Beverage]
Output: "Your beverage is ready! Enjoy!"
```

---

### **Preparing Coffee**

```
Step 1: coffee.prepareBeverage() called
↓
Step 2: Template method executes
↓
Step 3: boilWater() [Concrete - from Beverage]
Output: "Boiling water..."
↓
Step 4: brew() [Abstract - implemented in Coffee]
Output: "Dripping coffee through filter..."
↓
Step 5: pourInCup() [Concrete - from Beverage]
Output: "Pouring into cup..."
↓
Step 6: addCondiments() [Abstract - implemented in Coffee]
Output: "Adding sugar and milk..."
↓
Step 7: customerWantsExtras() [Hook - overridden in Coffee]
Returns: false
↓
Step 8: addExtras() [Hook - SKIPPED because false]
(Not executed)
↓
Step 9: serve() [Concrete - from Beverage]
Output: "Your beverage is ready! Enjoy!"
```

---

### **Preparing Hot Chocolate**

```
Step 1: hotChocolate.prepareBeverage() called
↓
Step 2: Template method executes
↓
Step 3: boilWater() [Concrete - from Beverage]
Output: "Boiling water..."
↓
Step 4: brew() [Abstract - implemented in HotChocolate]
Output: "Mixing cocoa powder with hot water..."
↓
Step 5: pourInCup() [Concrete - from Beverage]
Output: "Pouring into cup..."
↓
Step 6: addCondiments() [Abstract - implemented in HotChocolate]
Output: "Adding milk and vanilla..."
↓
Step 7: customerWantsExtras() [Hook - overridden in HotChocolate]
Returns: true
↓
Step 8: addExtras() [Hook - overridden in HotChocolate]
Output: "Adding marshmallows and whipped cream"
↓
Step 9: serve() [Concrete - from Beverage]
Output: "Your beverage is ready! Enjoy!"
```

---

## ✅ Output

```
=== Preparing Tea ===
Boiling water...
Steeping the tea bag...
Pouring into cup...
Adding lemon...
Adding honey
Your beverage is ready! Enjoy!

=== Preparing Coffee ===
Boiling water...
Dripping coffee through filter...
Pouring into cup...
Adding sugar and milk...
Your beverage is ready! Enjoy!

=== Preparing Hot Chocolate ===
Boiling water...
Mixing cocoa powder with hot water...
Pouring into cup...
Adding milk and vanilla...
Adding marshmallows and whipped cream
Your beverage is ready! Enjoy!
```

---

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming
- Behavioral Design Pattern
- Abstract Classes and Inheritance
- Method Overriding

---

## 📚 Example Explanation

In this example:
- `Beverage` is the **abstract class** defining the template method
- `prepareBeverage()` is the **template method** (final, cannot be overridden)
- `brew()` and `addCondiments()` are **abstract methods** (must be implemented)
- `addExtras()` and `customerWantsExtras()` are **hook methods** (optional overrides)
- `boilWater()`, `pourInCup()`, `serve()` are **concrete methods** (shared implementations)
- `Tea`, `Coffee`, `HotChocolate` are **concrete classes** implementing specific behaviors
- All beverages follow the same preparation algorithm but customize specific steps

---

## ✅ Advantages

- Eliminates code duplication (DRY principle)
- Provides consistent algorithm structure
- Easy to add new variations (just extend the class)
- Controls which parts of algorithm can be customized
- Follows Hollywood Principle (framework calls you)
- Hook methods provide optional extension points
- Template method is protected from modification (final keyword)

---

## ❌ Disadvantages

- Can lead to rigid class hierarchies
- Liskov Substitution Principle violations if not careful
- Debugging can be harder (algorithm split across classes)
- Not suitable when algorithm steps vary significantly
- May be overkill for simple cases

---

## 🎓 Use Cases

- **Frameworks and Libraries**: Providing extension points for users
- **Data Processing**: ETL pipelines (Extract, Transform, Load)
- **Game Development**: Game initialization sequences
- **Document Generation**: Different document formats with same structure
- **Testing Frameworks**: Test setup, execution, teardown
- **UI Rendering**: Rendering components with common structure
- **Report Generation**: Reports with different data but same format
- **Build Systems**: Build processes with customizable steps
- **Authentication**: Login flows with different authentication methods

---

## 🔑 Key Concepts

### **Template Method**
- Defines algorithm skeleton
- Declared as `final` to prevent overriding
- Calls abstract methods, hook methods, and concrete methods

### **Abstract Methods**
- Must be implemented by all subclasses
- Represent the variable parts of the algorithm
- Declared with `protected abstract`

### **Hook Methods**
- Provide default implementation (often empty or return false)
- Can be overridden by subclasses
- Offer optional customization points
- Declared with `protected` (not abstract)

### **Concrete Methods**
- Implemented in abstract class
- Shared by all subclasses
- Represent the invariant parts of the algorithm
- Often declared as `private` to prevent overriding

---

## 🏁 Conclusion

The **Template Method Pattern** provides an elegant solution to code duplication while maintaining flexibility. In this example, different **beverage preparation processes** share a common structure but allow customization of specific steps.

By defining the algorithm skeleton once in the abstract class and letting subclasses customize only what differs, the pattern achieves the perfect balance between reuse and flexibility. The template method ensures consistency across all implementations, while abstract and hook methods provide controlled extension points.

The Template Method Pattern demonstrates a fundamental principle of good design: identify what stays the same and separate it from what changes. This separation makes code easier to understand, maintain, and extend.

---

## ✍️ Author

**Mazen Naji**  
Software Engineer | Full Stack Developer

---

## 📄 License

Educational use only