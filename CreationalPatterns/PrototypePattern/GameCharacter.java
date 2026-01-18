package CreationalPatterns.PrototypePattern;

public class GameCharacter implements Prototype {

    private String name;
    private int health;
    private int attackPower;

    public GameCharacter(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    private GameCharacter(GameCharacter character) {
        this.name = character.name;
        this.health = character.health;
        this.attackPower = character.attackPower;
    }

    @Override
    public Prototype clone() {
        return new GameCharacter(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println(
            "Character: " + name +
            " | Health: " + health +
            " | Attack: " + attackPower
        );
    }
}

