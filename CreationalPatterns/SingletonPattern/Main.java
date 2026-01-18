package CreationalPatterns.SingletonPattern;

public class Main {
    public static void main(String[] args) {
        Singleton firstInstance = Singleton.getInstance();
        firstInstance.showMessage();

        Singleton secondInstance = Singleton.getInstance();
        secondInstance.showMessage();

        if (firstInstance == secondInstance) {
            System.out.println("Both references point to the same Singleton instance!");
        } else {
            System.out.println("Different instances exist (should not happen)!");
        }
    }
}

