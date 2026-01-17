package MementoPattern;

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

