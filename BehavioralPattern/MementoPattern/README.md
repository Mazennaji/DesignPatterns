# 📝 Memento Design Pattern (Java)

## 📌 Overview

The **Memento Pattern** is a **behavioral design pattern** that allows an object to **save and restore its internal state** without exposing its internal details. It is commonly used to implement **undo/redo**, **snapshots**, and **rollback mechanisms**.

The pattern preserves encapsulation boundaries while enabling state restoration, making it essential for any application requiring history tracking or rollback capabilities.

---

## 🎯 Intent

> Capture and externalize an object's internal state so that the object can be restored to this state later, **without violating encapsulation**.

---

## 🧠 Key Idea

> "Save the past without revealing the present's secrets."

---

## ❓ Why Use the Memento Pattern?

* Implement **Undo / Redo** functionality
* Maintain a **history of states**
* Avoid exposing internal fields of an object
* Restore an object to a previous valid state
* Support checkpointing and rollback mechanisms

---

## ⏱️ When to Use

Use the Memento Pattern when you need to save and restore an object's state without breaking encapsulation, or when implementing undo/redo functionality.

---

## 🧩 Pattern Participants

| Component | Role |
|-----------|------|
| **Originator** | Creates mementos and restores state |
| **Memento** | Stores snapshot of originator's state |
| **Caretaker** | Manages mementos without inspecting them |
| **Client** | Requests saves and restores via caretaker |

---

## 📐 UML Class Diagram (Memento Pattern)

```text
+--------------------+
|    TextEditor      |
|   (Originator)     |
+--------------------+
| -content: String   |
+--------------------+
| +write(String)     |
| +getContent()      |
| +save(): Memento   |
| +restore(Memento)  |
+--------------------+
        |
        | creates
        ▼
+--------------------+
|  EditorMemento     |
|    (Memento)       |
+--------------------+
| -content: String   |
+--------------------+
| +getContent()      |
+--------------------+

+--------------------+
|     History        |
|   (Caretaker)      |
+--------------------+
| -states: Stack     |
+--------------------+
| +push(Memento)     |
| +pop(): Memento    |
| +isEmpty(): bool   |
+--------------------+
        |
        | stores
        ▼
   [EditorMemento]

+--------------------+
|       Main         |
|     (Client)       |
+--------------------+
| +main(String[])    |
+--------------------+
```

---

## 🧠 UML Diagram Explanation

The UML diagram illustrates the structure and relationships of the **Memento Design Pattern** implementation.

### 1️⃣ Originator (TextEditor)
- The object whose state needs to be saved and restored
- Contains the actual state (`content` field)
- Provides methods:
  - `save()` to create a memento snapshot
  - `restore(memento)` to revert to a previous state
- Has full access to its internal state but doesn't expose it directly

---

### 2️⃣ Memento (EditorMemento)
- Stores a snapshot of the originator's state
- Is **immutable** (state cannot be changed after creation)
- Contains no business logic, only data
- Provides limited access to its stored state (only `getContent()`)
- Acts as a protective barrier preventing external modification

---

### 3️⃣ Caretaker (History)
- Manages the collection of mementos
- Uses a `Stack` to maintain history in chronological order
- Responsible for:
  - Storing mementos (`push()`)
  - Retrieving mementos (`pop()`)
  - Tracking if history exists (`isEmpty()`)
- **Never modifies or inspects** memento contents

---

### 4️⃣ Client (Main)
- Coordinates the save/restore operations
- Requests the originator to save its state
- Asks the caretaker to store/retrieve mementos
- Triggers restoration when needed (undo operation)
- Does **not** directly access memento internals

---

## 🔗 Relationships Summary

- `TextEditor` **creates** `EditorMemento` objects
- `History` **stores** multiple `EditorMemento` objects
- `TextEditor` **restores** from `EditorMemento` objects
- `Main` **coordinates** between TextEditor and History
- The memento acts as a **bridge** preserving encapsulation

---

## ✅ Key Design Benefits

- Preserves **encapsulation** (no getters/setters for internal state)
- Implements **Single Responsibility Principle** (state management separated)
- Enables **time-travel** through object states
- Simplifies undo/redo implementation
- State snapshots are immutable and safe

---

## 🔄 Memento Pattern vs. Direct State Access

### ❌ **Without Memento Pattern (Direct State Exposure)**

```java
// Text Editor exposes internal state directly
public class TextEditor {
    public String content; // Public field - BAD!
    
    public void write(String text) {
        this.content = text;
    }
}

// Client manages state manually - messy!
public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Stack<String> history = new Stack<>();
        
        editor.write("Hello");
        history.push(editor.content); // Direct access to internal state!
        
        editor.write("Hello World");
        history.push(editor.content); // Violates encapsulation!
        
        editor.write("Hello World!!!");
        System.out.println("Current: " + editor.content);
        
        // Undo - directly manipulating internal state
        editor.content = history.pop(); // Direct modification - BAD!
        System.out.println("After Undo: " + editor.content);
    }
}
```

**Problems with this approach:**
- ⚠️ **Broken Encapsulation**: Internal state is publicly accessible
- ⚠️ **Tight Coupling**: Client code depends on knowing the exact structure of state
- ⚠️ **No Validation**: Anyone can modify state without checks
- ⚠️ **Fragile Design**: Adding new fields requires changing all client code
- ⚠️ **No Protection**: State can be corrupted by external code

---

### ✅ **With Memento Pattern (Encapsulated State Management)**

```java
// Text Editor keeps state private
public class TextEditor {
    private String content; // Private - GOOD!
    
    public void write(String text) {
        this.content = text;
    }
    
    public EditorMemento save() {
        return new EditorMemento(content); // Controlled state capture
    }
    
    public void restore(EditorMemento memento) {
        this.content = memento.getContent(); // Controlled state restoration
    }
}

// Immutable memento protects state
public class EditorMemento {
    private final String content; // Immutable
    
    public EditorMemento(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
}

// Clean separation of concerns
public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();
        
        editor.write("Hello");
        history.push(editor.save()); // Clean API
        
        editor.write("Hello World");
        history.push(editor.save()); // No internal knowledge needed
        
        editor.write("Hello World!!!");
        System.out.println("Current: " + editor.getContent());
        
        // Undo - through proper API
        editor.restore(history.pop()); // Clean and safe!
        System.out.println("After Undo: " + editor.getContent());
    }
}
```

**Benefits of this approach:**
- ✅ **Preserved Encapsulation**: State remains private
- ✅ **Loose Coupling**: Client doesn't know about internal structure
- ✅ **Controlled Access**: Only originator can create/restore mementos
- ✅ **Extensible**: Can add new fields without breaking client code
- ✅ **Thread-Safe**: Immutable mementos prevent concurrent modification issues

---

### 📊 Side-by-Side Comparison

| Aspect | Direct Access | Memento Pattern |
|--------|--------------|-----------------|
| **Encapsulation** | Broken (public fields) | Preserved (private state) |
| **State Safety** | Anyone can modify | Only originator controls |
| **Coupling** | High (client knows internals) | Low (client uses interface) |
| **Extensibility** | Difficult (breaks client code) | Easy (add fields internally) |
| **Validation** | None (direct assignment) | Possible (in save/restore) |
| **Immutability** | No guarantee | Mementos are immutable |

---

### 🎯 Real-World Impact

**Scenario**: You need to add formatting information (font, color, size) to the text editor.

**Without Memento Pattern:**
```java
// ALL client code breaks!
// Before: history.push(editor.content);
// After:  history.push(new EditorState(editor.content, editor.font, editor.color, editor.size));
// Every place that saves/restores state needs updating!
```

**With Memento Pattern:**
```java
// Client code remains UNCHANGED!
// EditorMemento internally stores all new fields
public class EditorMemento {
    private final String content;
    private final String font;    // New field
    private final String color;   // New field
    private final int size;       // New field
    
    // Constructor and getters handle everything
}

// Client still uses the same clean API:
history.push(editor.save());      // Works perfectly!
editor.restore(history.pop());    // No changes needed!
```

---

### 💡 Key Takeaway

> **Direct Access** = "Here's my state, do whatever you want with it—just don't break anything"  
> **Memento Pattern** = "I'll give you a safe snapshot you can't modify, and I'll handle restoration properly"

This comparison shows why the Memento Pattern is essential—it provides safety, encapsulation, and maintainability that direct state access cannot offer.

---

## 📝 Complete Implementation

### 1️⃣ Memento – `EditorMemento`

Stores the editor's state (content).

```java
public class EditorMemento {
    private final String content;

    public EditorMemento(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
```

---

### 2️⃣ Originator – `TextEditor`

Creates and restores mementos.

```java
public class TextEditor {
    private String content;

    public void write(String text) {
        this.content = text;
    }

    public String getContent() {
        return content;
    }

    public EditorMemento save() {
        return new EditorMemento(content);
    }

    public void restore(EditorMemento memento) {
        this.content = memento.getContent();
    }
}
```

---

### 3️⃣ Caretaker – `History`

Manages undo history.

```java
import java.util.Stack;

public class History {
    private Stack<EditorMemento> states = new Stack<>();

    public void push(EditorMemento memento) {
        states.push(memento);
    }

    public EditorMemento pop() {
        return states.pop();
    }

    public boolean isEmpty() {
        return states.isEmpty();
    }
}
```

---

### 4️⃣ Client Code – `Main`

Demonstrates undo functionality.

```java
public class Main {
    public static void main(String[] args) {

        TextEditor editor = new TextEditor();
        History history = new History();

        editor.write("Hello");
        history.push(editor.save());

        editor.write("Hello World");
        history.push(editor.save());

        editor.write("Hello World!!!");
        System.out.println("Current: " + editor.getContent());

        editor.restore(history.pop());
        System.out.println("After Undo: " + editor.getContent());

        editor.restore(history.pop());
        System.out.println("After Undo: " + editor.getContent());
    }
}
```

---

## 🔄 Step-by-Step Flow Explanation

### **Step 1: Initial Write**
```
editor.write("Hello")
Editor State: content = "Hello"
```

### **Step 2: Save State**
```
history.push(editor.save())
→ EditorMemento created with content = "Hello"
→ Memento stored in history stack

History Stack: [EditorMemento("Hello")]
```

### **Step 3: Second Write**
```
editor.write("Hello World")
Editor State: content = "Hello World"
```

### **Step 4: Save Second State**
```
history.push(editor.save())
→ EditorMemento created with content = "Hello World"
→ Memento stored in history stack

History Stack: [EditorMemento("Hello"), EditorMemento("Hello World")]
```

### **Step 5: Third Write**
```
editor.write("Hello World!!!")
Editor State: content = "Hello World!!!"

History Stack: [EditorMemento("Hello"), EditorMemento("Hello World")]
```

### **Step 6: First Undo**
```
editor.restore(history.pop())
→ Pop returns EditorMemento("Hello World")
→ Editor restores to "Hello World"

Editor State: content = "Hello World"
History Stack: [EditorMemento("Hello")]
```

### **Step 7: Second Undo**
```
editor.restore(history.pop())
→ Pop returns EditorMemento("Hello")
→ Editor restores to "Hello"

Editor State: content = "Hello"
History Stack: []
```

---

## ✅ Output

```
Current: Hello World!!!
After Undo: Hello World
After Undo: Hello
```

---

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming
- Behavioral Design Pattern
- Stack Data Structure

---

## 📚 Example Explanation

In this example:
- `TextEditor` is the **Originator** that creates and restores state
- `EditorMemento` is the **Memento** that stores immutable snapshots
- `History` is the **Caretaker** that manages the undo stack
- The client (`Main`) coordinates save/restore operations
- State is saved before each change and can be restored on demand

---

## ✅ Advantages

- Preserves **encapsulation**
- Simplifies **undo/rollback** logic
- Clean separation of responsibilities
- Easy to extend (redo support, checkpoints)
- Immutable snapshots prevent accidental corruption
- Supports complex state with multiple fields

---

## ❌ Disadvantages

- Increased memory usage if many states are stored
- History management is required to avoid memory issues
- Can become expensive with large or complex objects
- May need memory optimization strategies (compression, limits)

---

## 🎓 Use Cases

- **Text Editors**: Undo/redo functionality
- **Configuration Systems**: Rollback to previous settings
- **Game Development**: Save checkpoints and game states
- **Database Transactions**: Transaction undo/commit
- **Graphics Software**: State history for drawing operations
- **Version Control**: Snapshot management

---

## 🏁 Conclusion

The **Memento Pattern** provides a safe and clean way to capture and restore object state. In this example, it was used to implement an **Undo feature for a Text Editor** while preserving encapsulation and following solid object-oriented design principles.

By separating the concerns of state creation (Originator), state storage (Memento), and state management (Caretaker), the pattern creates a flexible and maintainable solution for any application requiring history tracking or rollback capabilities.

---

## ✍️ Author

**Mazen Naji**  
Software Engineer | Full Stack Developer

---

## 📄 License

Educational use only