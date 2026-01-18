package CreationalPatterns.AbstractFactoryPattern;

public class Main {
    public static void main(String[] args) {
        GUIFactory macFactory = new MacFactory();
        GUIFactory windowsFactory = new WindowsFactory();

        System.out.println("Mac GUI:");
        Button macButton = macFactory.createButton();
        Checkbox macCheckbox = macFactory.createCheckbox();
        macButton.paint();
        macCheckbox.paint();

        System.out.println("\nWindows GUI:");
        Button winButton = windowsFactory.createButton();
        Checkbox winCheckbox = windowsFactory.createCheckbox();
        winButton.paint();
        winCheckbox.paint();
    }
}

