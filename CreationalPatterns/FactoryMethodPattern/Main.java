package CreationalPatterns.FactoryMethodPattern;


public class Main {
    public static void main(String[] args) {
        Creator creatorA = new ConcreteCreatorA();
        Creator creatorB = new ConcreteCreatorB();

        System.out.println("Using CreatorA:");
        creatorA.performAction();

        System.out.println("Using CreatorB:");
        creatorB.performAction();
    }
}

