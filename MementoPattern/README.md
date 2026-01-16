# 📝 Memento Design Pattern

## 📌 Overview

The **Memento Pattern** is a **behavioral design pattern** that allows an object to **save and restore its internal state** without exposing its internal details. It is commonly used to implement **undo/redo**, **snapshots**, and **rollback mechanisms**.

---

## 🎯 Intent

> Capture and externalize an object’s internal state so that the object can be restored to this state later, **without violating encapsulation**.

---

## ❓ Why Use the Memento Pattern?

* Implement **Undo / Redo** functionality
* Maintain a **history of states**
* Avoid exposing internal fields of an object
* Restore an object to a previous valid state

---

## 🧩 Pattern Participants

1. **Originator**
   The object whose state needs to be saved and restored. It creates a memento and restores its state from it.

2. **Memento**
   Stores the internal state of the originator. It should be immutable and contain no business logic.

3. **Caretaker**
   Manages mementos but never modifies or inspects their contents.

---

## 📝 Example: Text Editor with Undo Feature

### 🧠 Scenario

We implement a simple **Text Editor** that allows writing text, saving previous states, and undoing changes using the Memento Pattern.

---

## 1️⃣ Memento – `EditorMemento`

Stores the editor’s state (content).

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

## 2️⃣ Originator – `TextEditor`

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

## 3️⃣ Caretaker – `History`

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

## 4️⃣ Client Code – `Main`

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

## ✅ Output

```
Current: Hello World!!!
After Undo: Hello World
After Undo: Hello
```

---

## 🔑 Advantages

* Preserves **encapsulation**
* Simplifies **undo/rollback** logic
* Clean separation of responsibilities
* Easy to extend (redo support, checkpoints)

---

## ⚠️ Disadvantages

* Increased memory usage if many states are stored
* History management is required to avoid memory issues

---

## 📚 When to Use

* Text editors
* Configuration rollback systems
* Game save checkpoints
* Transaction undo systems

---

## 🏁 Conclusion

The **Memento Pattern** provides a safe and clean way to capture and restore object state. In this example, it was used to implement an **Undo feature for a Text Editor** while preserving encapsulation and following solid object-oriented design pr
