package MementoPattern;

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

