package CreationalPatterns.PrototypePattern;

public class Main {
    public static void main(String[] args) {
        GameCharacter warrior = new GameCharacter("Warrior", 100, 20);

        GameCharacter clonedWarrior = (GameCharacter) warrior.clone();

        clonedWarrior.setName("Elite Warrior");

        warrior.display();
        clonedWarrior.display();
    }
}

